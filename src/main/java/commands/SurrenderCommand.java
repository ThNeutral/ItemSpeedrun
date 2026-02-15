package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class SurrenderCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "surrender";

    private final Logger logger;

    public SurrenderCommand(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command _command, String _s, String[] _strings) {
        if (!(commandSender instanceof Player)) {
            logger.warning(ChatColor.RED + "/" + COMMAND_NAME + " command can only be done by a player.");
            return true;
        }

        Player player = (Player) commandSender;

        // TODO: Add command logic here

        return true;
    }
}
