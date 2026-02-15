package state.game;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

public abstract class IWorldHandler {
    protected static final Set<Biome> BAD_SPAWN_LOCATIONS = EnumSet.of(
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

    protected Location findAvailableSpawnLocation(World world)
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

    public abstract void unloadWorldPreviousAndGenerateNewWorld();
    public abstract void moveAllPlayersToMinigameWorld();
    public abstract void moveAllPlayersToMainWorld();
}
