import java.lang.Thread.State;
import java.util.List;

import org.bukkit.entity.Player;

import Scoreboard.Timer.Timer;

public class Game {
    private Timer gameTimer;

    private List<Player> players;
    public void AddPlayer(Player player) {
        players.add(player);
    }


    public void Start() {

        var timer = new Timer(); 
    }
}