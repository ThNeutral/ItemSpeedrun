package state.players;

public interface IVoteList {
    boolean vote(String name);

    boolean allVoted();

    void forceAllVote();

    void reset();
}
