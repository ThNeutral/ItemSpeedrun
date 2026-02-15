package state.game.implementations.Optimized;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import state.game.IWorldHandler;

public class PolledWorldHandler implements IWorldHandler {
    private Collection<? extends Player> GetPlayers() {
        return Bukkit.getOnlinePlayers();
    }

    private World _currentGameWorld;
    private World _mainWorld;
    private WorldPool _worldPool;

    public PolledWorldHandler(WorldPool worldPool) {
        _mainWorld = Bukkit.getWorlds().get(0);
        _worldPool = worldPool;
        _currentGameWorld = _worldPool.pull();
    }

    @Override
    public void unloadWorldPreviousAndGenerateNewWorld() {
        _currentGameWorld = _worldPool.pull();
    }

    @Override
    public void moveAllPlayersToMinigameWorld() {
        var spawnLocation = _currentGameWorld.getSpawnLocation();
        this.GetPlayers().forEach(player -> player.teleport(spawnLocation));
    }

    @Override
    public void moveAllPlayersToMainWorld() {
        var spawnLocation = _mainWorld.getSpawnLocation();
        this.GetPlayers().forEach(player -> player.teleport(spawnLocation));
    }
}