package state.game.implementations.Basic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;
import state.game.IWorldHandler;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class WorldHandler implements IWorldHandler {

    private static final String MINIGAME_WORLD_NAME = "fuckmedaddy";
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

    private Optional<World> _minigameWorld;
    private World _mainWorld;

    public WorldHandler() {
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
        // ну хуйня, лучше оперировать листом плееров
        Bukkit.getOnlinePlayers().forEach(player -> player.teleport(spawnLocation));
        Bukkit.getOnlinePlayers().forEach(player -> player.setBedSpawnLocation(spawnLocation, true));
        minigameWorld.setSpawnLocation(spawnLocation);
    }

    @Override
    public void moveAllPlayersToMainWorld() {
        var spawnLocation = _mainWorld.getSpawnLocation();
        Bukkit.getOnlinePlayers().forEach(player -> player.teleport(spawnLocation));
    }

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
