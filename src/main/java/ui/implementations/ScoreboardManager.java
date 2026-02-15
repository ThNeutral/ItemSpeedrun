package ui.implementations;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import ui.IScoreboardManager;
import state.game.ChallengeDifficulty;
import state.game.GameStates;
import state.game.IChallengeManager;
import state.game.IGameStateManager;
import state.players.IPlayerList;
import state.time.ITimer;
import java.util.Arrays;

public class ScoreboardManager implements IScoreboardManager {
    private final static String TIMER_TEAM_NAME = "timer";
    private final static String ITEM_TEAM_NAME = "item";

    private final IChallengeManager _challengeGenerator;
    private final IPlayerList _playerList;
    private final ITimer _timer;
    private final IGameStateManager _gameStateManager;

    public ScoreboardManager(
            IChallengeManager challengeGenerator,
            IPlayerList playerList,
            ITimer timer,
            IGameStateManager gameStateManager
    ) {
        _challengeGenerator = challengeGenerator;
        _playerList = playerList;
        _timer = timer;
        _gameStateManager = gameStateManager;
    }

    public void update() {
        var players = _playerList.getPlayers();

        for (var player : players) {
            var scoreboard = getNewScoreboard();
            player.setScoreboard(scoreboard);
        }
    }

    private Scoreboard getNewScoreboard() {
        var builder = new ScoreboardBuilder(7,
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Item " + ChatColor.GRAY + ChatColor.BOLD + "SpeedRun",
                DisplaySlot.SIDEBAR
        );

        // Spacing
        builder.addLine(" ");

        // Spacing
        builder.addLine(ChatColor.RED + "");

        // Timer
        builder.addTeam(TIMER_TEAM_NAME, ChatColor.LIGHT_PURPLE + "Timer " + ChatColor.GRAY + "-" + ChatColor.RED + " ", _timer.getFormattedTime());

        // Item
        var currentItem = _challengeGenerator.getCurrentItem();
        if (currentItem.isPresent()) {
            builder.addTeam(ITEM_TEAM_NAME, ChatColor.LIGHT_PURPLE + "Item " + ChatColor.GRAY + "-" + ChatColor.RED + " ", currentItem.get().toString());
        } else {
            builder.addTeam(ITEM_TEAM_NAME, ChatColor.LIGHT_PURPLE + "Item " + ChatColor.GRAY + "-" + ChatColor.RED + " ", "None");
        }

        // Spacing
        builder.addLine("  ");

        // Spacing
        builder.addLine("   ");

        // State
        switch (_gameStateManager.getCurrentState()) {
            case GameStates.ROLLING:
                var difficulties = String.join(
                        "|",
                        Arrays.stream(
                                ChallengeDifficulty.values()
                        ).map(
                                ChallengeDifficulty::toString
                        ).map(
                                String::toLowerCase
                        ).toArray(
                                String[]::new
                        )
                );
                builder.addLine(ChatColor.DARK_AQUA + "Roll for item using " + ChatColor.RED + "/roll [" + difficulties + "]");
                builder.addLine(ChatColor.DARK_AQUA + "Game will start when everyone is " + ChatColor.RED + "/ready");
                break;
            case GameStates.PLAYING:
                builder.addLine(ChatColor.DARK_AQUA + "The game is on!");
                break;
            case GameStates.GAME_OVER:
                builder.addLine(ChatColor.DARK_AQUA + "Game over! " + ChatColor.RED + _challengeGenerator.getVictor().get().getName() + ChatColor.DARK_AQUA + " has won!");
                break;
        }

        return builder.build();
    }
}
