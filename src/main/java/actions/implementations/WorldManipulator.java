package actions.implementations;

import actions.IWorldManipulator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.logging.Logger;

public class WorldManipulator implements IWorldManipulator {
    private Logger logger;
    private World currentWorld;

    public World createNewWorld() {
        var oldWorld = currentWorld;
        var worldCreator = new WorldCreator("todo_world_name");
        currentWorld = Bukkit.createWorld(worldCreator);
        return currentWorld;
    }

    public void setSpawnPoint(Location location) {
        currentWorld.setSpawnLocation(location);
    }

    public void setSpawnPoint(World world) {
        // Todo. I don't care for Location, I just want it to figure out where to place players.
        // Not somewhere before calling this method;
    }
}
