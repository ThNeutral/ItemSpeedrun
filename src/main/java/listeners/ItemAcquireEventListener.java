package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import state.game.IChallengeManager;

// Item can be acquired by picking it up, crafting it or tacking it from another inventory (like chest)
public class ItemAcquireEventListener implements Listener {
    private final IChallengeManager challengeManager;

    public ItemAcquireEventListener(
            IChallengeManager challengeManager
    ) {
        this.challengeManager = challengeManager;
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            var pickedUpItem = event.getItem().getItemStack();
            handleItemAcquisition(player, pickedUpItem);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            var craftedItem = event.getRecipe().getResult();
            handleItemAcquisition(player, craftedItem);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            var currentItem = event.getCurrentItem();
            handleItemAcquisition(player, currentItem);
        }
    }

    private void handleItemAcquisition(Player player, ItemStack acquiredItem) {
        var optionalChallengeItem = challengeManager.getCurrentItem();
        if (optionalChallengeItem.isEmpty()) {
            return;
        }

        var challengeItem = optionalChallengeItem.get();
        if (acquiredItem != null && acquiredItem.getType() == challengeItem) {
            challengeManager.itemFound(player);
        }
    }
}
