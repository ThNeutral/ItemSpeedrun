package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import state.game.IChallengeManager;

public class PickupEvenListener implements Listener {
    private final IChallengeManager challengeGenerator;

    public PickupEvenListener(IChallengeManager challengeGenerator) {
        this.challengeGenerator = challengeGenerator;
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        var optionalChallengeItem = challengeGenerator.getCurrentItem();
        if (optionalChallengeItem.isEmpty()) {
            return;
        }

        if (event.getEntity() instanceof Player player) {
            var challengeItem = optionalChallengeItem.get();
            var pickedUpItem = event.getItem().getItemStack();
            if (pickedUpItem.getType() != challengeItem) {
                return;
            }


        }
    }
}
