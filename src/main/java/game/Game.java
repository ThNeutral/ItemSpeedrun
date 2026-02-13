package game;

import java.util.List;

import org.bukkit.entity.Player;

import scoreboard.timer.Timer;

public class Game {
    private Timer gameTimer;

    private List<Player> players;
    public void addPlayer(Player player) {
        players.add(player);
    }
    public List<Player> getPlayers() {
        return players;
    }
    public boolean containsPlayer(Player player) {
        return players.contains(player);
    }


    public void start() {

        var timer = new Timer(); 
    }
}