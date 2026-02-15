package state.game.implementations;

import org.bukkit.entity.Player;

import state.game.ChallengeDifficulty;
import state.game.GameStates;
import state.game.IChallengeManager;
import state.game.IGameStateManager;
import state.game.IWorldHandler;
import state.players.IInventoryManager;
import state.players.IPlayerList;
import state.players.IVoteList;
import state.time.ITimer;
import ui.IBannerManager;

public class GameStateManager implements IGameStateManager {
    private GameStates _currentState = GameStates.ROLLING;

    private final IPlayerList _playerList;
    private final IInventoryManager _inventoryManager;
    private final IWorldHandler _worldHandler;
    private final ITimer _timer;
    private final IChallengeManager _challengeManager;
    private final IBannerManager _bannerManager;

    public GameStateManager(
            IVoteList playersReadyList,
            IPlayerList playerList,
            IInventoryManager inventoryManager,
            IWorldHandler worldHandler,
            ITimer timer,
            IChallengeManager challengeManager,
            IBannerManager bannerManager) {
        this._playerList = playerList;
        this._inventoryManager = inventoryManager;
        this._worldHandler = worldHandler;
        this._challengeManager = challengeManager;
        this._timer = timer;
        this._bannerManager = bannerManager;
    }

    @Override
    public void startGame() throws IllegalStateException {
        if (_currentState == GameStates.PLAYING) {
            throw new IllegalStateException("Can not start game while not in rolling state.");
        }

        if (_challengeManager.getCurrentItem().isEmpty()) {
            throw new IllegalStateException("Can not start game without a target.");
        }

        _currentState = GameStates.PLAYING;

        _worldHandler.unloadWorldPreviousAndGenerateNewWorld();
        _worldHandler.moveAllPlayersToMinigameWorld();

        _timer.start();

        for (Player player : _playerList.getPlayers()) {
            _inventoryManager.setDefaultInventory(player);
        }
    }

    @Override
    public void endGame(boolean skipped) {
        if (_currentState != GameStates.PLAYING) {
            throw new IllegalStateException("Can not end not started game.");
        }

        _timer.stop();
        for (Player player : _playerList.getPlayers()) {
            _inventoryManager.clearInventory(player);
        }
        _challengeManager.rollItem(ChallengeDifficulty.Medium);
        _worldHandler.moveAllPlayersToMainWorld();
        _currentState = GameStates.GAME_OVER;
        
        if (!skipped) {
            _bannerManager.show();
        }
    }

    @Override
    public void roll(ChallengeDifficulty difficulty) throws IllegalStateException {
        if (_currentState == GameStates.PLAYING) {
            throw new IllegalStateException("Can not roll during game.");
        }
        _challengeManager.rollItem(difficulty);
    }

    @Override
    public GameStates getCurrentState() {
        return _currentState;
    }
}
