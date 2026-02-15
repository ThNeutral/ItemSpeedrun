package ui.implementations;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import state.time.ITimer;
import ui.IBannerManager;
import state.game.GameStates;
import state.game.IChallengeManager;
import state.game.IGameStateManager;
import state.players.IPlayerList;

// Not the best implementation, but will do for now.
// I don't want to spend too much time on this, as it's not a core part of the game.
public class BannerManager implements IBannerManager {
    private final IGameStateManager _gameStateManager;
    private final IChallengeManager _challengeManager;
    private final IPlayerList _playerList;
    private final ITimer _timer;

    private GameStates _previousState;

    public BannerManager(
            IGameStateManager gameStateManager,
            IChallengeManager challengeManager,
            IPlayerList playerList,
            ITimer timer
    ) {
        _gameStateManager = gameStateManager;
        _challengeManager = challengeManager;
        _playerList = playerList;
        _timer = timer;

        _previousState = _gameStateManager.getCurrentState();
    }

    public void update() {
        var state = _gameStateManager.getCurrentState();
        if (state == _previousState) {
            return;
        }

        if (state != GameStates.GAME_OVER) {
            return;
        }

        _previousState = state;

        var challengeItem = _challengeManager.getCurrentItem().get();
        var victor = _challengeManager.getVictor().get();
        for (var player : _playerList.getPlayers()) {
            player.sendTitle(
                    ChatColor.RED + victor.getName() + ChatColor.DARK_AQUA + " wins!",
                    ChatColor.DARK_AQUA + "The item " + ChatColor.RED + challengeItem + "" +
                            ChatColor.DARK_AQUA + " was found in " + ChatColor.RED + _timer.getFormattedTime(),
                    10, 70, 20
            );

            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
        }
    }
}