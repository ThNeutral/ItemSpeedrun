package state.game.implementations.Optimized;


import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;

import state.game.IWorldHandler;
import state.players.IPlayerList;

public class PolledWorldHandler implements IWorldHandler {
    private IPlayerList _playerList;

    private World _currentGameWorld;
    private World _mainWorld;
    private WorldPool _worldPool;

    public PolledWorldHandler(WorldPool worldPool, 
            IPlayerList playerList) {
        _mainWorld = Bukkit.getWorlds().get(0);
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
    }

    @Override
    public void moveAllPlayersToMainWorld() {
        var spawnLocation = _mainWorld.getSpawnLocation();
        _playerList.getPlayers().forEach(player -> player.teleport(spawnLocation));
    }

    
    private static final Set<Biome> BAD_SPAWN_LOCATIONS = EnumSet.of(
            Biome.OCEAN,
            Biome.DEEP_OCEAN,
            Biome.COLD_OCEAN,
            Biome.FROZEN_OCEAN,
            Biome.LUKEWARM_OCEAN,
            Biome.WARM_OCEAN,
            Biome.RIVER,
            Biome.DEEP_LUKEWARM_OCEAN,
            Biome.DEEP_COLD_OCEAN,
            Biome.DEEP_FROZEN_OCEAN
    );
    private Location findAvailableSpawnLocation(World world)
    {
        Random random = new Random();
        boolean acceptableBiome = false;
        int spawnX = 0;
        int spawnZ = 0;
        int maxAttempts = 300;

        for (int attempt = 0; attempt < maxAttempts && !acceptableBiome; attempt++) {
            spawnX = (random.nextInt(125) - 62) * 16; // -992 to 992 blocks (chunk-aligned)
            spawnZ = (random.nextInt(125) - 62) * 16;

            Biome biomeAtSpawn = world.getBiome(spawnX, spawnZ);

            acceptableBiome = !BAD_SPAWN_LOCATIONS.contains(biomeAtSpawn);
        }

        return new Location(world, spawnX, world.getHighestBlockYAt(spawnX, spawnZ) + 2, spawnZ);
    }
}