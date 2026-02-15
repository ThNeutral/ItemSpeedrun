package state.game.implementations;

import constants.Items;

import org.bukkit.Material;

import state.game.ChallengeDifficulty;
import state.game.IChallengeManager;

import java.util.Optional;

public class ChallengeManager implements IChallengeManager {
    private Optional<Material> _currentItem = Optional.empty();

    private final Items _items;

    public ChallengeManager(Items items) {
        _items = items;
    }

    @Override
    public Material rollItem(ChallengeDifficulty difficulty) {
        var item = _items.getRandomItem(difficulty);
        _currentItem = Optional.of(item);
        return item;
    }

    @Override
    public Optional<Material> getCurrentItem() {
        return _currentItem;
    }
}
