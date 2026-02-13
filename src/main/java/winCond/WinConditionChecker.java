package winCond;

import game.Game;
import main.Main;
import setupWorld.Setup;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class WinConditionChecker implements Listener {
    private Main plugin;
    private Game game;

    public WinConditionChecker(
        Main plugin,
        Game game
    ) {
        this.plugin = plugin;
        this.game = game;
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        if (!game.containsPlayer(player)) {
            System.err.println("Received EntityPickupItemEvent callback for user that is not part of game");
            return;
        }

        if (event.getItem().getItemStack().getType() == Setup.itemID) {
            handleWinCondition(player);
        }
    }

    private void handleWinCondition(Player winner) {
        for (Player p : game.getPlayers()) {
            p.setGameMode(GameMode.SPECTATOR);
            p.getInventory().clear();
            Location spawnLocation = p.getWorld().getHighestBlockAt(0, 0).getLocation();
            p.teleport(spawnLocation);
            p.sendTitle(ChatColor.YELLOW + "The item has been found", "", 10, 60, 10);
            p.sendMessage("§c§l" + winner.getName() + " has won!");
            for (int i = 0; i < 10; i++) {
                p.playSound(p.getLocation(), Sound.ENTITY_GOAT_DEATH, 1, 1);
            }
        }
        plugin.resetGame();
    }
}
