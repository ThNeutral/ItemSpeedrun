package state.game;

import org.bukkit.Material;

import java.util.Optional;

public interface IChallengeGenerator {
    Material rollItem(ChallengeDifficulty difficulty);
    Optional<Material> getCurrentItem();
}
