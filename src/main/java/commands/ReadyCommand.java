package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import state.game.IWorldHandler;
import state.players.IPlayersReadyList;

import java.util.logging.Logger;

public class ReadyCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "ready";
    public static final String FORCE_SUBCOMMAND = "force";

    private final Logger _logger;
    private final IPlayersReadyList _playersReadyList;
    private final IWorldHandler _worldHandler;

    public ReadyCommand(
            Logger logger,
            IPlayersReadyList playersReadyList,
            IWorldHandler worldHandler
    ) {
        this._logger = logger;
        this._playersReadyList = playersReadyList;
        this._worldHandler = worldHandler;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command _command, String _s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            _logger.warning(ChatColor.RED + "/" + COMMAND_NAME + " command can only be done by a player.");
            return true;
        }
        Player player = (Player) commandSender;

        if (strings.length > 0 && strings[0].equalsIgnoreCase(FORCE_SUBCOMMAND)) {
            _playersReadyList.forceAllReady();
        } else {
            _playersReadyList.setReady(player);
        }

        if (_playersReadyList.allReady()) {
            startChallenge();
            return true;
        }

        return true;
    }

    private void startChallenge() {
        _worldHandler.moveAllPlayersToMainWorld();
        _worldHandler.unloadWorldPreviousAndGenerateNewWorld();
        _worldHandler.moveAllPlayersToMinigameWorld();
    }
}
