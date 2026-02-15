package ui.implementations;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import events.GameEndedEvent;
import state.time.ITimer;
import state.game.IChallengeManager;
import state.players.IPlayerList;

public class BannerManager implements Listener {
    private final IChallengeManager _challengeManager;
    private final IPlayerList _playerList;
    private final ITimer _timer;

    public BannerManager(
            IChallengeManager challengeManager,
            IPlayerList playerList,
            ITimer timer) {
        _challengeManager = challengeManager;
        _playerList = playerList;
        _timer = timer;
    }

    @EventHandler
    public void onGameEnded(GameEndedEvent event) {
        showGameEndedBanner();
    }

    private void showGameEndedBanner() {
        var challengeItem = _challengeManager.getCurrentItem().get();
        var victor = _challengeManager.getVictor().get();
        for (var player : _playerList.getPlayers()) {
            player.sendTitle(
                    ChatColor.RED + victor.getName() + ChatColor.DARK_AQUA + " wins!",
                    ChatColor.DARK_AQUA + "The item " + ChatColor.RED + challengeItem + "" +
                            ChatColor.DARK_AQUA + " was found in " + ChatColor.RED + _timer.getFormattedTime(),
                    10, 70, 20);

            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
        }
    }
}