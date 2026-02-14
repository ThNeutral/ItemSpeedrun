package state.players.implementation;

import org.bukkit.entity.Player;
import state.players.IPlayerList;
import state.players.IPlayersReadyList;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class PlayerList implements IPlayersReadyList, IPlayerList {
    private final List<Player> players;
    private final Set<Player> readyPlayers;

    public PlayerList() {
        this.players = new java.util.ArrayList<>();
        this.readyPlayers = new HashSet<>();
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        // Overkill?
        if (!containsPlayer(player)) {
            throw new IllegalArgumentException("Tried to remove player that is not in the list.");
        }

        players.remove(player);
        readyPlayers.remove(player);
    }

    @Override
    public boolean containsPlayer(Player player) {
        return players.contains(player);
    }

    @Override
    public void setReady(Player player) {
        if (containsPlayer(player)) {
            readyPlayers.add(player);
        } else {
            throw new IllegalArgumentException("Player is not in the player list.");
        }
    }

    @Override
    public void setNotReady(Player player) {
        readyPlayers.remove(player);
    }

    @Override
    public boolean allReady() {
        return !players.isEmpty() && readyPlayers.size() == players.size();
    }

    @Override
    public void forceAllReady() {
        readyPlayers.clear();
        readyPlayers.addAll(players);
    }
}
