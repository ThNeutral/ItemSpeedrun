package actions;

import org.bukkit.Location;
import org.bukkit.World;

public interface IWorldManipulator {
    World createNewWorld();
    void setSpawnPoint(Location location);
}
