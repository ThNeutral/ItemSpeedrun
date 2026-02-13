package scoreboard.timer;

import java.time.Duration;

public interface TimerListener {
    void Update(long elapsedMillis);
    Duration GetTotalTimePassed();
}