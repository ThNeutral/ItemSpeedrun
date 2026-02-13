package players;

import org.bukkit.entity.Player;

public interface IPlayersReadyList {
    void setReady(Player player);
    void setNotReady(Player player);
    boolean allReady();
    void forceAllReady();
}
