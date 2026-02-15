package state.game.implementations.basic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;
import state.game.IWorldHandler;
import state.players.IPlayerList;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class WorldHandler extends IWorldHandler {
    private static final String MINIGAME_WORLD_NAME = "fuckmedaddy";

    private Optional<World> _minigameWorld;
    private World _mainWorld;

    private IPlayerList _playerList;

    public WorldHandler(
            IPlayerList playerList
    ) {
        _playerList = playerList;

        var minigameWorld = Bukkit.getWorld(MINIGAME_WORLD_NAME);
        if (minigameWorld != null) {
            _minigameWorld = Optional.of(minigameWorld);
        } else {
            _minigameWorld = Optional.empty();
        }

        _mainWorld = Bukkit.getWorlds().get(0);
    }

    @Override
    public void unloadWorldPreviousAndGenerateNewWorld() {
        if(_minigameWorld.isPresent()) {
            Bukkit.unloadWorld(MINIGAME_WORLD_NAME, false);
        }

        var worldGenerator = new WorldCreator(MINIGAME_WORLD_NAME);
        // ensure world is generated each time
        var world = worldGenerator.createWorld();
        _minigameWorld = Optional.of(world);
    }

    @Override
    public void moveAllPlayersToMinigameWorld() {
        if (_minigameWorld.isEmpty()) {
            throw new IllegalStateException("Tried to move players to minigame world before it was generated.");
        }

        var minigameWorld = _minigameWorld.get();
        var spawnLocation = findAvailableSpawnLocation(minigameWorld);
        _playerList.getPlayers().forEach(player -> player.teleport(spawnLocation));
        _playerList.getPlayers().forEach(player -> player.setBedSpawnLocation(spawnLocation, true));
        minigameWorld.setSpawnLocation(spawnLocation);
    }

    @Override
    public void moveAllPlayersToMainWorld() {
        var spawnLocation = _mainWorld.getSpawnLocation();
        _playerList.getPlayers().forEach(player -> player.teleport(spawnLocation));
    }
}
