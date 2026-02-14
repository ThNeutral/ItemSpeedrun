import commands.*;
import constants.Constants;
import constants.Items;
import listeners.PlayerEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import scoreboard.implementations.ScoreboardManager;
import state.game.implementations.ChallengeManager;
import state.game.implementations.WorldHandler;
import state.players.implementation.PlayerList;
import state.time.implementations.Timer;


public class MainPlugin extends JavaPlugin {
    private BukkitTask scoreboardTask;

    @Override
    public void onEnable() {
        getLogger().info(Constants.PLUGIN_NAME + " " + Constants.PLUGIN_VERSION + "started!");
        // Initialize your plugin here (e.g., register commands, events)

        var scheduler = getServer().getScheduler();

        var timer = new Timer();
        timer.start();

        var items = new Items();
        var challengeGenerator = new ChallengeManager(items);

        var playerList = new PlayerList();
        var worldHandler = new WorldHandler();

        var scoreboardManager = new ScoreboardManager(challengeGenerator, playerList, timer);

        getServer().getPluginManager().registerEvents(new PlayerEventListener(playerList), this);

        getCommand(RollCommand.COMMAND_NAME).setExecutor(new RollCommand(getLogger(), challengeGenerator));
        getCommand(ReadyCommand.COMMAND_NAME).setExecutor(new ReadyCommand(getLogger(), playerList, worldHandler));
        getCommand(JoinCommand.COMMAND_NAME).setExecutor(new JoinCommand(getLogger()));
        getCommand(SkipCommand.COMMAND_NAME).setExecutor(new SkipCommand(getLogger()));
        getCommand(SurrenderCommand.COMMAND_NAME).setExecutor(new SurrenderCommand(getLogger()));

        scoreboardTask = scheduler.runTaskTimer(this, scoreboardManager::update, 0L, 2L);
    }

    @Override
    public void onDisable() {
        if (scoreboardTask != null && !scoreboardTask.isCancelled()) {
            scoreboardTask.cancel();
        }
    }
}
