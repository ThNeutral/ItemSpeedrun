package state.players.implementation;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import state.players.IInventoryManager;

public class InventoryManager implements IInventoryManager {
    @Override
    public void setDefaultInventory(Player player) {
        var steaks = new ItemStack(Material.COOKED_BEEF, 64);

        player.getInventory().clear();
        player.getInventory().addItem(steaks);
        player.performCommand("trackingcompass");
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setGameMode(GameMode.SURVIVAL);
        player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_7, 1, 1);
    }

    @Override
    public void clearInventory(Player player) {
        player.getInventory().clear();
    }
}
