package state.players;

import org.bukkit.entity.Player;

import java.util.List;

public interface IPlayerList {
    void addPlayer(Player player);
    void removePlayer(Player player);
    boolean containsPlayer(Player player);
    List<Player> getPlayers();
}
