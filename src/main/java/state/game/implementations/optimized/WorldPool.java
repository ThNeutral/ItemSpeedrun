package state.game.implementations.optimized;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldPool {
    private int poolSize;
    private String prefix;
    private File worldContainer;

    private World currentWorld = null;

    public WorldPool(int poolSize, String prefix, File worldsContainer) {
        super();
        this.poolSize = poolSize;
        this.prefix = prefix;
        this.worldContainer = worldsContainer;

        fullify();
    }

    public World pull() {
        if (currentWorld != null) {
            deleteWorld(currentWorld);
        }

        var worldDataList = getWorlds();
        if (worldDataList.isEmpty()) {
            generate();
            worldDataList = getWorlds();
        }

        var worldName = worldDataList.get(0).getName(); 
        return Bukkit.createWorld(new org.bukkit.WorldCreator(worldName));
    }

    public void deleteWorld(World world) {
        world.getName();
        File folder = world.getWorldFolder();
        Bukkit.unloadWorld(world, false);
        folder.delete();
    }

    public void fullify() {
        List<File> worldDataList = getWorlds();

        int missing = poolSize - worldDataList.size();
        if (missing > 0) {
            generate(missing);
        }
    }

    private List<File> getWorlds() {
        File[] allFiles = worldContainer.listFiles();
        List<File> worldDataList = new ArrayList<>();
        if (allFiles != null) {
            for (File file : allFiles) {
                if (file.isDirectory() && file.getName().startsWith(prefix)) {
                    worldDataList.add(file);
                }
            }
        }
        return worldDataList;
    }

    private void generate() {
        generate(1);
    }

    private void generate(int count) {
        for (int i = 0; i < count; i++) {
            var worldName = prefix + "_" + UUID.randomUUID();
            var world = Bukkit.createWorld(new org.bukkit.WorldCreator(worldName));
            Bukkit.unloadWorld(world, false);
        }
    }
}
