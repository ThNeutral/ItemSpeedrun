package state.players.implementation;

import org.bukkit.entity.Player;
import state.players.IPlayerList;
import state.players.IPlayersReadyList;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class PlayerList implements IPlayersReadyList, IPlayerList {
    private final List<Player> _players;
    private final Set<Player> _readyPlayers;

    public PlayerList() {
        this._players = new java.util.ArrayList<>();
        this._readyPlayers = new HashSet<>();
    }

    @Override
    public void addPlayer(Player player) {
        _players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        if (!containsPlayer(player)) {
            throw new IllegalArgumentException("Tried to remove player that is not in the list.");
        }

        _players.remove(player);
        _readyPlayers.remove(player);
    }

    @Override
    public boolean containsPlayer(Player player) {
        return _players.contains(player);
    }

    @Override
    public List<Player> getPlayers() {
        return _players;
    }

    @Override
    public void setReady(Player player) {
        if (containsPlayer(player)) {
            _readyPlayers.add(player);
        } else {
            throw new IllegalArgumentException("Player is not in the player list.");
        }
    }

    @Override
    public boolean allReady() {
        return !_players.isEmpty() && _readyPlayers.size() == _players.size();
    }

    @Override
    public void forceAllReady() {
        _readyPlayers.clear();
        _readyPlayers.addAll(_players);
    }

    @Override
    public void reset() {
        _readyPlayers.clear();
    }
}

