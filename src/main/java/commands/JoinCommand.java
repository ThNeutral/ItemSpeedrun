package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class JoinCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "join";

    private final Logger _logger;

    public JoinCommand(Logger logger) { 
        this._logger = logger;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command _command, String _s, String[] _strings) {
        if (!(commandSender instanceof Player)) {
            logger.warning(ChatColor.RED + "/" + COMMAND_NAME +  " command can only be done by a player.");
            return true;
        }

        Player player = (Player) commandSender;
        
        if (!(commandSender instanceof Player)) {
            _logger.warning(ChatColor.RED + "/" + COMMAND_NAME + " command can only be done by a player.");
            return true;
        }
        Player player = (Player) commandSender;

        if (strings.length > 0 && strings[0].equalsIgnoreCase(FORCE_SUBCOMMAND)) {
            _playersReadyList.forceAllVote();
        } else {
            if (!_playersReadyList.vote(player.getName())) {
                commandSender.sendMessage(ChatColor.GRAY + "You are already ready.");
            }
        }

        if (_playersReadyList.allVoted()) {
            _gameStateManager.startGame();
            return true;
        }

        return true;
    }
}
