package state.game;

public interface IWorldHandler {
        void unloadWorldPreviousAndGenerateNewWorld();
        void moveAllPlayersToMinigameWorld();
        void moveAllPlayersToMainWorld();
}
