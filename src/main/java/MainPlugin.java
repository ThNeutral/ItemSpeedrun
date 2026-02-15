import commands.*;
import constants.Constants;
import constants.Items;
import listeners.ItemAcquireEventListener;
import listeners.PlayerEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import ui.implementations.BannerManager;
import ui.implementations.ScoreboardManager;
import state.game.implementations.ChallengeManager;
import state.game.implementations.GameStateManager;
import state.game.implementations.WorldHandler;
import state.players.implementation.PlayerList;
import state.time.implementations.Timer;


public class MainPlugin extends JavaPlugin {
    private BukkitTask scoreboardTask;
    private BukkitTask bannerTask;

    @Override
    public void onEnable() {
        getLogger().info(Constants.PLUGIN_NAME + " " + Constants.PLUGIN_VERSION + "started!");

        var scheduler = getServer().getScheduler();
        var pluginManager = getServer().getPluginManager();

        var timer = new Timer();

        var gameStateManager = new GameStateManager();

        var items = new Items();
        var challengeManager = new ChallengeManager(items, timer, gameStateManager);

        var playerList = new PlayerList();
        var worldHandler = new WorldHandler(playerList);

        var scoreboardManager = new ScoreboardManager(challengeManager, playerList, timer, gameStateManager);
        var bannerManager = new BannerManager(gameStateManager, challengeManager, playerList, timer);

        pluginManager.registerEvents(new ItemAcquireEventListener(challengeManager), this);
        pluginManager.registerEvents(new PlayerEventListener(playerList), this);

        getCommand(RollCommand.COMMAND_NAME).setExecutor(new RollCommand(getLogger(), challengeManager));
        getCommand(ReadyCommand.COMMAND_NAME).setExecutor(
                new ReadyCommand(getLogger(), playerList, worldHandler, timer, gameStateManager)
        );
        getCommand(JoinCommand.COMMAND_NAME).setExecutor(new JoinCommand(getLogger()));
        getCommand(SkipCommand.COMMAND_NAME).setExecutor(new SkipCommand(getLogger()));
        getCommand(SurrenderCommand.COMMAND_NAME).setExecutor(new SurrenderCommand(getLogger()));

        scoreboardTask = scheduler.runTaskTimer(this, scoreboardManager::update, 0L, 2L);
        bannerTask = scheduler.runTaskTimer(this, bannerManager::update, 0L, 2L);
    }

    @Override
    public void onDisable() {
        if (scoreboardTask != null && !scoreboardTask.isCancelled()) {
            scoreboardTask.cancel();
        }

        if (bannerTask != null && !bannerTask.isCancelled()) {
            bannerTask.cancel();
        }
    }
}
