package WinCond;

import main.main;
import SetupWorld.Setup;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scheduler.BukkitRunnable;

public class WinConditionChecker extends BukkitRunnable {
    private main plugin;

    public WinConditionChecker(
        main plugin,
        Game game
    ) {
        this.plugin = plugin;
        this.game = game;
    }

    public void checkPlayersForDiamondSword() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getInventory().contains(Setup.itemID)) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.getInventory().clear();
                    Location spawnLocation = p.getWorld().getHighestBlockAt(0, 0).getLocation();
                    p.teleport(spawnLocation);
                    p.sendTitle(ChatColor.YELLOW + "The item has been found", "");
                    p.sendMessage("§c§l" + player.getName() + " has won in " + min + "m " + sec + "s");
                    for (int i = 0; i < 10; i++)
                        p.playSound(p.getLocation(), Sound.ENTITY_GOAT_DEATH, 1, 1);
                }
                plugin.resetGame();
                break;
            }
        }
    }

    @Override
    public void run() {
        checkForWinCondition();
    }

    private void checkForWinCondition() {
        
    }
}
