package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import players.IPlayersReadyList;

import java.util.logging.Logger;

public class ReadyCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "ready";
    public static final String FORCE_SUBCOMMAND = "force";

    private final Logger logger;
    private IPlayersReadyList playersReadyList;

    public ReadyCommand(
            Logger logger,
            IPlayersReadyList playersReadyList
    ) {
        this.logger = logger;
        this.playersReadyList = playersReadyList;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command _command, String _s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            logger.warning(ChatColor.RED + "/" + COMMAND_NAME + " command can only be done by a player.");
            return true;
        }

        if (strings.length > 0 && strings[0].equalsIgnoreCase(FORCE_SUBCOMMAND)) {
            playersReadyList.forceAllReady();
            return true;
        }

        Player player = (Player) commandSender;

        playersReadyList.setReady(player);
        if (playersReadyList.allReady()) {

        }

        return true;
    }
}
