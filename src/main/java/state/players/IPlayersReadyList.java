package state.players;

import helpers.IResettable;
import org.bukkit.entity.Player;

public interface IPlayersReadyList extends IResettable {
    void setReady(Player player);
    boolean allReady();
    void forceAllReady();
}
