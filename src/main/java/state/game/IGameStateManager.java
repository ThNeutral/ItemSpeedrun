package state.game;


public interface IGameStateManager {
    void startGame();
    void roll(ChallengeDifficulty difficulty);
    void endGame(boolean skipped);
    GameStates getCurrentState();
}
