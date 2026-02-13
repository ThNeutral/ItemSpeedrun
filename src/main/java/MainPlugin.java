import commands.ReadyCommand;
import commands.RerollCommand;
import commands.JoinCommand;
import commands.SkipCommand;
import commands.SurrenderCommand;
import constants.Constants;
import listeners.PlayerEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import players.implementation.PlayerList;


public class MainPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info(Constants.PLUGIN_NAME + " " + Constants.PLUGIN_VERSION + " started!");
        // Initialize your plugin here (e.g., register commands, events)

        var playerList = new PlayerList();

        getServer().getPluginManager().registerEvents(new PlayerEventListener(playerList), this);

        getCommand(RerollCommand.COMMAND_NAME).setExecutor(new RerollCommand(getLogger()));
        getCommand(ReadyCommand.COMMAND_NAME).setExecutor(new ReadyCommand(getLogger(), playerList));
        getCommand(JoinCommand.COMMAND_NAME).setExecutor(new JoinCommand(getLogger()));
        getCommand(SkipCommand.COMMAND_NAME).setExecutor(new SkipCommand(getLogger()));
        getCommand(SurrenderCommand.COMMAND_NAME).setExecutor(new SurrenderCommand(getLogger()));



        //
    }

    @Override
    public void onDisable() {
        // TODO: Implement any necessary cleanup logic here (e.g., save data, unregister listeners)
        getLogger().warning("Plugin disabled! No cleanup is implemented.");
    }
}
