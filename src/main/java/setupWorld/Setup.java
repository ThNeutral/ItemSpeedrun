package setupWorld;

import items.Items;
import main.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;

import static scoreboard.Timer.TimerListener.min;
import static scoreboard.Timer.TimerListener.sec;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

public class Setup implements CommandExecutor, Listener {

    private Main plugin;
    private static long lastPlayCommandTime = 0;
    public String lastWorldName = null;

    private final long cooldown = 30000;
    private Map<Player, String> gameLengths = new HashMap<>();
    public static Material itemID = Material.DIAMOND;
    
    public Setup(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (lastWorldName != null) {
            World respawnWorld = Bukkit.getWorld(lastWorldName);
            if (respawnWorld != null) {
                Location respawnLocation = respawnWorld.getSpawnLocation();
                event.setRespawnLocation(respawnLocation);
            }
        }
        ItemStack steaks = new ItemStack(Material.COOKED_BEEF, 64);
        event.getPlayer().getInventory().addItem(steaks);
        event.getPlayer().performCommand("trackingcompass");
    }

    public void setupPlayerInSpawn(Player player) {
        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
    }

    public World generateWorld(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This comamnd can only be done by a player.");
            return null;
        }
        Player player = (Player) sender;
        if (lastWorldName != null && Bukkit.getWorld(lastWorldName) != null)
            Bukkit.getServer().unloadWorld(Bukkit.getWorld(lastWorldName), true);

        for (Player p : Bukkit.getOnlinePlayers())
            p.sendTitle(ChatColor.YELLOW + "Loading map...", "", 10, 300, 10);

        // Create one world with random seed
        long seed = new Random().nextLong();
        WorldCreator worldCreator = new WorldCreator("world_" + seed);
        worldCreator.seed(seed);
        World newWorld = player.getServer().createWorld(worldCreator);

        // Sample different chunks to find acceptable biome
        Random random = new Random();
        boolean acceptableBiome = false;
        int spawnX = 0;
        int spawnZ = 0;
        int maxAttempts = 100;

        for (int attempt = 0; attempt < maxAttempts && !acceptableBiome; attempt++) {
            // Sample random chunk coordinates (within reasonable range, e.g., -1000 to 1000
            // blocks)
            spawnX = (random.nextInt(125) - 62) * 16; // -992 to 992 blocks (chunk-aligned)
            spawnZ = (random.nextInt(125) - 62) * 16;

            Biome biomeAtSpawn = newWorld.getBiome(spawnX, spawnZ);

            if (biomeAtSpawn != Biome.OCEAN && biomeAtSpawn != Biome.RIVER && biomeAtSpawn != Biome.DEEP_OCEAN
                    && biomeAtSpawn != Biome.COLD_OCEAN
                    && biomeAtSpawn != Biome.FROZEN_OCEAN && biomeAtSpawn != Biome.LUKEWARM_OCEAN
                    && biomeAtSpawn != Biome.WARM_OCEAN
                    && biomeAtSpawn != Biome.DEEP_LUKEWARM_OCEAN && biomeAtSpawn != Biome.DEEP_COLD_OCEAN
                    && biomeAtSpawn != Biome.DEEP_FROZEN_OCEAN) {
                acceptableBiome = true;
            }
        }

        player.sendTitle("", "");
        newWorld.setSpawnLocation(spawnX, newWorld.getHighestBlockYAt(spawnX, spawnZ) + 1, spawnZ);
        lastWorldName = newWorld.getName();

        return newWorld;
    }

    public void setDifficulty(Difficulty difficulty) {
        for (World world : Bukkit.getWorlds())
            world.setDifficulty(difficulty);
    }

    public void buildSpawn(Location spawnLocation) {
        World world = spawnLocation.getWorld();
        int radius = 10;
        int height = 5;
        Random random = new Random();

        Material[] glassTypes = {
                Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS,
                Material.LIGHT_BLUE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS,
                Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS,
                Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS, Material.BLUE_STAINED_GLASS,
                Material.BROWN_STAINED_GLASS, Material.GREEN_STAINED_GLASS, Material.RED_STAINED_GLASS,
                Material.BLACK_STAINED_GLASS
        };

        for (int x = -radius; x <= radius; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = -radius; z <= radius; z++) {
                    boolean isEdge = x == -radius || x == radius || z == -radius || z == radius || y == 0;
                    Location blockLocation = spawnLocation.clone().add(x, y, z);
                    Block block = world.getBlockAt(blockLocation);

                    if (isEdge) {
                        Material selectedGlass = glassTypes[random.nextInt(glassTypes.length)];
                        BlockData glassBlockData = selectedGlass.createBlockData();
                        block.setBlockData(glassBlockData);
                    }
                }
            }
        }
    }

    public void openGameLengthInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Choose Game Length");

        ItemStack paper1 = new ItemStack(Material.PAPER);
        ItemMeta meta1 = paper1.getItemMeta();
        meta1.setDisplayName("Short Game");
        paper1.setItemMeta(meta1);

        ItemStack paper2 = new ItemStack(Material.PAPER);
        ItemMeta meta2 = paper2.getItemMeta();
        meta2.setDisplayName("Medium Game");
        paper2.setItemMeta(meta2);

        ItemStack paper3 = new ItemStack(Material.PAPER);
        ItemMeta meta3 = paper3.getItemMeta();
        meta3.setDisplayName("Long Game");
        paper3.setItemMeta(meta3);

        inventory.setItem(3, paper1);
        inventory.setItem(4, paper2);
        inventory.setItem(5, paper3);

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Random random = new Random();

        if (event.getView().getTitle().equals("Choose Game Length")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.PAPER) {
                Player player = (Player) event.getWhoClicked();
                String gameLength = event.getCurrentItem().getItemMeta().getDisplayName();
                switch (gameLength) {
                    case "Short Game":
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            itemID = Items.shortitem[random.nextInt(shortitem.length)];

                        }
                        break;
                    case "Medium Game":
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            itemID = Items.miditem[random.nextInt(miditem.length)];
                        }
                        break;
                    case "Long Game":
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            itemID = Items.longitem[random.nextInt(longitem.length)];
                        }
                        break;
                }
                player.closeInventory();
            }
            sec = 0;
            min = 0;
            plugin.resetGame();
            Player player = (Player) event.getWhoClicked();
            World newWorld = generateWorld(player);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("§c§l" + "Find a(n) " + itemID.toString() + " to win!");
                org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();
                board.getTeam("item").setSuffix(" " + Setup.itemID.name());
            }
            Location spawnLocation = newWorld.getHighestBlockAt(newWorld.getSpawnLocation()).getLocation().add(0, 50,
                    0);
            Location spawnPlayer = newWorld.getHighestBlockAt(newWorld.getSpawnLocation()).getLocation().add(0, 51, 0);
            newWorld.setPVP(false);
            buildSpawn(spawnLocation);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.teleport(spawnPlayer);
                Location playerLocation = player.getLocation();
                setupPlayerInSpawn(p);
            }
            newWorld.setDifficulty(Difficulty.PEACEFUL);
            plugin.setGameStarted(true);
            for (Player p : Bukkit.getOnlinePlayers())
                p.sendMessage(ChatColor.GREEN + "The game starts, do /ready when you want to start !");
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This comamnd can only be done by a player.");
            return false;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPlayCommandTime < cooldown) {
            sender.sendMessage(ChatColor.RED + "Wait before using this command again.");
            return true;
        }
        lastPlayCommandTime = currentTime;
        Player player = (Player) sender;
        if (!gameLengths.containsKey(player)) {
            openGameLengthInventory(player);
            return true;
        }
        return false;
    }
}
