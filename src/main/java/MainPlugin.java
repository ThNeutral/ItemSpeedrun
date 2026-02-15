import commands.*;
import constants.Constants;
import constants.Items;
import listeners.ItemAcquireEventListener;
import listeners.PlayerEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import state.game.IWorldHandler;
import state.game.implementations.ChallengeManager;
import state.game.implementations.GameStateManager;
import state.game.implementations.optimized.PolledWorldHandler;
import state.game.implementations.optimized.WorldPool;
import state.players.implementation.InventoryManager;
import state.players.implementation.PlayerList;
import state.time.implementations.Timer;
import ui.implementations.BannerManager;
import ui.implementations.ScoreboardManager;

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
        
        var inventoryManager = new InventoryManager();

        var playerList = new PlayerList();
        var worldPoll = new WorldPool(
                5,
                "speedrun_world_",
                getServer().getWorldContainer());
        IWorldHandler worldHandler = new PolledWorldHandler(worldPoll, playerList);

        var scoreboardManager = new ScoreboardManager(challengeManager, playerList, timer, gameStateManager);
        var bannerManager = new BannerManager(gameStateManager, challengeManager, playerList, timer);

        pluginManager.registerEvents(new ItemAcquireEventListener(challengeManager), this);
        pluginManager.registerEvents(new PlayerEventListener(playerList, inventoryManager), this);

        getCommand(RollCommand.COMMAND_NAME).setExecutor(new RollCommand(getLogger(), challengeManager));
        getCommand(ReadyCommand.COMMAND_NAME).setExecutor(
                new ReadyCommand(getLogger(), playerList, playerList, inventoryManager, worldHandler, timer, gameStateManager)
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
