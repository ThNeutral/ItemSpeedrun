package state.game;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Optional;

public interface IChallengeManager {
    Material rollItem(ChallengeDifficulty difficulty);
    Optional<Material> getCurrentItem();
    void itemFound(Player player);
    Optional<Player> getVictor();
}
