package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import state.game.ChallengeDifficulty;
import state.game.GameStates;
import state.game.IGameStateManager;

import java.util.logging.Logger;

public class RollCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "roll";
    public static final String DIFFICULTIES_SUBCOMMAND = "difficulties";

    private final Logger _logger;
    private final IGameStateManager _gameStateManager;

    public RollCommand(
            Logger logger,
            IGameStateManager gameStateManager)
    {
        this._logger = logger;
        this._gameStateManager = gameStateManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            _logger.warning(ChatColor.RED + "/" + COMMAND_NAME + " command can only be done by a player.");
            return true;
        }

        if (!_gameStateManager.getCurrentState().equals(GameStates.PLAYING)) {
            commandSender.sendMessage(ChatColor.RED + "There is no game in progress.");
            return true;
        }

        if (strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "Invalid command usage.");
            return true;
        }

        if (strings[0].equalsIgnoreCase(DIFFICULTIES_SUBCOMMAND)) {
            commandSender.sendMessage(ChatColor.GREEN + "Available difficulties:");
            for (var difficulty : ChallengeDifficulty.values()) {
                commandSender.sendMessage(ChatColor.GREEN + "- " + difficulty.name());
            }
            return true;
        }

        var difficultyOpt = ChallengeDifficulty.fromString(strings[0]);
        if (difficultyOpt.isEmpty()) {
            commandSender.sendMessage(ChatColor.RED + "Invalid difficulty. Use '/roll difficulties' to see all available difficulties.");
            return true;
        }

        _gameStateManager.roll(difficultyOpt.get());

        return true;
    }
}
