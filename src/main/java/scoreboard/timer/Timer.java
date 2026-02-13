package scoreboard.timer;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Timer extends BukkitRunnable {
    public final static int TICK = 1000;

    private long start;
    private List<TimerListener> subscribers;
    public void addSubscriber(TimerListener subscriber) {
        subscribers.add(subscriber);
    }

    private boolean isRunning;

    public Timer() {
        isRunning = false;
    }

    public void start() {
        isRunning = true;
        start = Now();
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        if (!isRunning) return;

        long elapsedMillis = Now() - start;
        for (TimerListener subscriber : subscribers) {
            subscriber.Update(elapsedMillis);
        }
    }

    public static Timer startTimer(Plugin plugin) {
        var timer = new Timer();
        timer.start();
        timer.runTaskTimerAsynchronously(plugin, 0, TICK);
        return timer;
    }

    private long Now() {
        return System.currentTimeMillis();
    }

}