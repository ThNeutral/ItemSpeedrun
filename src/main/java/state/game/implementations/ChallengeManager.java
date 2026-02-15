package state.game.implementations;

import constants.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import state.game.ChallengeDifficulty;
import state.game.GameStates;
import state.game.IChallengeManager;
import state.game.IGameStateManager;
import state.time.ITimer;

import java.util.Optional;

public class ChallengeManager implements IChallengeManager {
    private Optional<Material> _currentItem;
    private Optional<Player> _victor;

    private final Items _items;
    private final ITimer _timer;
    private final IGameStateManager _gameStateManager;
    
    public ChallengeManager(
            Items items,
            ITimer timer,
            IGameStateManager gameStateManager
    ) {
        _victor = Optional.empty();
        _currentItem = Optional.empty();

        _items = items;
        _timer = timer;
        _gameStateManager = gameStateManager;
    }
    
    @Override
    public Material rollItem(ChallengeDifficulty difficulty) {
        _victor = Optional.empty();

        var item = _items.getRandomItem(difficulty);
        _currentItem = Optional.of(item);
        return item;
    }

    @Override
    public Optional<Material> getCurrentItem() {
        return _currentItem;
    }

    @Override
    public void itemFound(Player player) {
        if (_currentItem.isEmpty()) {
            throw new IllegalStateException("No item to be found.");
        }

        _victor = Optional.of(player);
        _timer.stop();

        _gameStateManager.setCurrentState(GameStates.GAME_OVER);
    }

    @Override
    public Optional<Player> getVictor() {
        return _victor;
    }
}
