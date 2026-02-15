package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import state.game.GameStates;
import state.game.IGameStateManager;
import state.players.IVoteList;

import java.util.logging.Logger;

public class SkipCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "skip";
    public static final String FORCE_SUBCOMMAND = "force";

    private final Logger _logger;
    private final IVoteList _voteList;
    private final IGameStateManager _gameStateManager;

    public SkipCommand(Logger logger,
        IVoteList voteList,
        IGameStateManager gameStateManager
    ) {
        this._logger = logger;
        this._voteList = voteList;
        this._gameStateManager = gameStateManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command _command, String _s, String[] _strings) {
        if (!(commandSender instanceof Player)) {
            _logger.warning(ChatColor.RED + "/" + COMMAND_NAME + " command can only be done by a player.");
            return true;
        }

        if (!_gameStateManager.getCurrentState().equals(GameStates.PLAYING)) {
            commandSender.sendMessage(ChatColor.RED + "There is no game in progress.");
            return true;
        }
        
        Player player = (Player) commandSender;

        if (_strings.length > 0 && _strings[0].equalsIgnoreCase(FORCE_SUBCOMMAND)) {
            _voteList.forceAllVote();
        } else {
            if (!_voteList.vote(player.getName())) {
                commandSender.sendMessage(ChatColor.GRAY + "You are already ready.");
            }
        }

        if (_voteList.allVoted()) {
            _gameStateManager.skipGame();
            return true;
        }

        

        return true;
    }
}
