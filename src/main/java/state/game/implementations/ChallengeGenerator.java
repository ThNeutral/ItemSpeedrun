package state.game.implementations;

import constants.Items;
import org.bukkit.Material;
import state.game.ChallengeDifficulty;
import state.game.IChallengeGenerator;

import java.util.Optional;

public class ChallengeGenerator implements IChallengeGenerator {
    private Optional<Material> _currentItem;
    private final Items _items;
    
    public ChallengeGenerator(Items items) {
        _currentItem = Optional.empty();
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
