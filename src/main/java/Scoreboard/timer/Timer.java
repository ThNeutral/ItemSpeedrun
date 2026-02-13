package Scoreboard.Timer;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Timer extends BukkitRunnable {
    private long start;
    private List<TimerListener> subscribers;

    public Timer(List<TimerListener> subscribers) {
        this.start = Now();
        this.subscribers = subscribers;
    }

    @Override
    public void run() {
        long elapsedMillis = Now() - start;
        for (TimerListener subscriber : subscribers) {
            subscriber.Update(elapsedMillis);
        }
    }

    private long Now() {
        return System.currentTimeMillis();
    }

}