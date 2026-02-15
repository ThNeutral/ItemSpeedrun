package state.game.implementations.optimized;

import org.bukkit.Bukkit;
import org.bukkit.World;

import state.game.IWorldHandler;
import state.players.IPlayerList;

public class PolledWorldHandler extends IWorldHandler {
    private IPlayerList _playerList;

    private World _currentGameWorld;
    private World _mainWorld;
    private WorldPool _worldPool;

    public PolledWorldHandler(
            WorldPool worldPool,
            IPlayerList playerList) {
        _mainWorld = Bukkit.getWorlds().getFirst();
        _worldPool = worldPool;
        _playerList = playerList;
        _currentGameWorld = _worldPool.pull();
    }

    @Override
    public void unloadWorldPreviousAndGenerateNewWorld() {
        _currentGameWorld = _worldPool.pull();
    }

    @Override
    public void moveAllPlayersToMinigameWorld() {
        var spawnLocation = findAvailableSpawnLocation(_currentGameWorld);
        _playerList.getPlayers().forEach(player -> player.teleport(spawnLocation));
        _currentGameWorld.setSpawnLocation(spawnLocation);
    }

    @Override
    public void moveAllPlayersToMainWorld() {
        var spawnLocation = _mainWorld.getSpawnLocation();
        _playerList.getPlayers().forEach(player -> player.teleport(spawnLocation));
        _currentGameWorld.setSpawnLocation(spawnLocation);
    }
}