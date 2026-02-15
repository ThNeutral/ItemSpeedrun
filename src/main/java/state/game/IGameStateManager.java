package state.game;

import org.bukkit.entity.Player;

public interface IGameStateManager {
    GameStates getCurrentState();
    void setCurrentState(GameStates state);
}
