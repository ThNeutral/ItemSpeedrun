package players;

import org.bukkit.entity.Player;

public interface IPlayerList {
    void addPlayer(Player player);
    void removePlayer(Player player);
    boolean containsPlayer(Player player);
}
