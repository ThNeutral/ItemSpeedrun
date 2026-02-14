package scoreboard.implementations;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import scoreboard.IScoreboardManager;
import state.game.IChallengeGenerator;
import state.players.IPlayerList;
import state.time.ITimer;

public class ScoreboardManager implements IScoreboardManager {
    private final static String TIMER_TEAM_NAME = "timer";
    private final static String ITEM_TEAM_NAME = "item";

    private final IChallengeGenerator _challengeGenerator;
    private final IPlayerList _playerList;
    private final ITimer _timer;

    public ScoreboardManager(
            IChallengeGenerator challengeGenerator,
            IPlayerList playerList,
            ITimer timer
    ) {
        _challengeGenerator = challengeGenerator;
        _playerList = playerList;
        _timer = timer;
    }

    public void update() {
        var players = _playerList.getPlayers();

        for (var player : players) {
            var scoreboard = getNewScoreboard();
            player.setScoreboard(scoreboard);
        }
    }

    // Copied from previous implementation KEKW
    // TODO make it prettier
    private Scoreboard getNewScoreboard() {
        var scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        var objective = scoreboard.registerNewObjective("test", "dummy");

        objective.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Item " + ChatColor.GRAY + ChatColor.BOLD + "SpeedRun");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        var space = objective.getScore(" ");
        space.setScore(6);

        var space4 = objective.getScore(ChatColor.RED + "");
        space4.setScore(5);

        var timer = scoreboard.registerNewTeam(TIMER_TEAM_NAME);
        timer.addEntry(ChatColor.LIGHT_PURPLE + "Timer " + ChatColor.GRAY + "-" + ChatColor.RED + " ");
        timer.setSuffix(_timer.getFormattedTime());
        objective.getScore(ChatColor.LIGHT_PURPLE + "Timer " + ChatColor.GRAY + "-" + ChatColor.RED + " ").setScore(4);

        var item = scoreboard.registerNewTeam(ITEM_TEAM_NAME);
        item.addEntry(ChatColor.LIGHT_PURPLE + "Item " + ChatColor.GRAY + "-" + ChatColor.RED + " ");
        var currentItem = _challengeGenerator.getCurrentItem();
        if (currentItem.isPresent()) {
            item.setSuffix(currentItem.get() + "");
        } else {
            item.setSuffix("None");
        }
        objective.getScore(ChatColor.LIGHT_PURPLE + "Item " + ChatColor.GRAY + "-" + ChatColor.RED + " ").setScore(3);

        var space2 = objective.getScore("  ");
        space2.setScore(2);
        var space3 = objective.getScore("   ");
        space3.setScore(1);

        var play = objective.getScore(ChatColor.DARK_AQUA + "Start " + ChatColor.GRAY + "- " + ChatColor.RED + "/play");
        play.setScore(0);

        return scoreboard;
    }
}
