package state.players.implementation;

import java.util.ArrayList;
import java.util.Collection;

import state.players.IPlayerList;
import state.players.IVoteList;

public class VoteList implements IVoteList {
    private final Collection<String> _voted = new ArrayList<>();
    private final IPlayerList _playerList;

    public VoteList(IPlayerList playerList) {
        super();
        this._playerList = playerList;
    }

    @Override
    public boolean vote(String name) {
        if (_playerList.getPlayers().stream().anyMatch(p -> p.getName().equals(name))) {
            if (_voted.contains(name)) {
                return false;
            }
            _voted.add(name);
            return true;
        } else {
            throw new IllegalArgumentException("Player is not in the player list.");
        }
    }

    @Override
    public boolean allVoted() {
        var players = _playerList.getPlayers();
        return !players.isEmpty() && _voted.size() == players.size();
    }

    @Override
    public void forceAllVote() {
        _voted.clear();
        _voted.addAll(_playerList.getPlayers().stream().map(x -> x.getName()).toList());
    }

    @Override
    public void reset() {
        _voted.clear();
    }
}