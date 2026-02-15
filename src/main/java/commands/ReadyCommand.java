package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import state.game.GameStates;
import state.game.IGameStateManager;
import state.game.IWorldHandler;
import state.players.IInventoryManager;
import state.players.IPlayerList;
import state.players.IPlayersReadyList;
import state.time.ITimer;

import java.util.logging.Logger;

public class ReadyCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "ready";
    public static final String FORCE_SUBCOMMAND = "force";

    private final Logger _logger;
    private final IPlayersReadyList _playersReadyList;
    private final IPlayerList _playerList;
    private final IInventoryManager _inventoryManager;
    private final IWorldHandler _worldHandler;
    private final ITimer _timer;
    private final IGameStateManager _gameStateManager;

    public ReadyCommand(
            Logger logger,
            IPlayersReadyList playersReadyList,
            IPlayerList playerList,
            IInventoryManager inventoryManager,
            IWorldHandler worldHandler,
            ITimer timer,
            IGameStateManager gameStateManager) {
        this._logger = logger;
        this._playersReadyList = playersReadyList;
        this._playerList = playerList;
        this._inventoryManager = inventoryManager;
        this._worldHandler = worldHandler;
        this._timer = timer;
        this._gameStateManager = gameStateManager;
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
        _gameStateManager.setCurrentState(GameStates.PLAYING);
        _worldHandler.moveAllPlayersToMainWorld();
        _worldHandler.unloadWorldPreviousAndGenerateNewWorld();
        _worldHandler.moveAllPlayersToMinigameWorld();
        _timer.start();

        for (Player player : _playerList.getPlayers()) {
            _inventoryManager.setDefaultInventory(player);
        }
    }
}
