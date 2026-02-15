package state.players;

import org.bukkit.entity.Player;

public interface IInventoryManager {
    void setDefaultInventory(Player player);
    void clearInventory(Player player);
}
