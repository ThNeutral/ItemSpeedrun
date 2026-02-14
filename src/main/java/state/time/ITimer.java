package state.time;

public interface ITimer {
    void start();
    void stop();
    String getFormattedTime();
}
