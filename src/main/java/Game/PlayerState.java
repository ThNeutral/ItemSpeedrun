package Game;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import Scoreboard.Timer.TimerListener;
import java.Time;

public class PlayerState implements TimerListener {
    private Player player;
    private Scoreboard scoreboard;

    @Override
    public void Update(long elapsedMillis) {
        String time = String.format("%02d:%02d", elapsedMillis / 60000, (elapsedMillis / 1000) % 60);
        scoreboard.getTeam("timer").setSuffix("" + time);
    }

    public Duration GetTotalTimePassed()
    {

        return null!;
    }
}
