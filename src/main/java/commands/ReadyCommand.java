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

public class ReadyCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "ready";
    public static final String FORCE_SUBCOMMAND = "force";

    private final Logger _logger;
    private final IVoteList _playersReadyList;
    private final IGameStateManager _gameStateManager;

    public ReadyCommand(
            Logger logger,
            IVoteList playersReadyList,
            IGameStateManager gameStateManager) {
        this._logger = logger;
        this._playersReadyList = playersReadyList;
        this._gameStateManager = gameStateManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command _command, String _s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            _logger.warning(ChatColor.RED + "/" + COMMAND_NAME + " command can only be done by a player.");
            return true;
        }

        if (_gameStateManager.getCurrentState().equals(GameStates.PLAYING)) {
            commandSender.sendMessage(ChatColor.RED + "There is game in progress.");
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
