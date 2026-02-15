package state.game.implementations;

import state.game.GameStates;
import state.game.IGameStateManager;

public class GameStateManager implements IGameStateManager {
    private GameStates _currentState;

    public GameStateManager() {
        _currentState = GameStates.ROLLING;
    }

    @Override
    public GameStates getCurrentState() {
        return _currentState;
    }

    @Override
    public void setCurrentState(GameStates state) {
        _currentState = state;
    }
}
