package state.time.implementations;

import state.time.ITimer;

public class Timer implements ITimer {
    private boolean _isActive;
    private long _startTime;

    public Timer() {
        _isActive = false;
        _startTime = 0;
    }

    @Override
    public void start() {
        _isActive = true;
        _startTime = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        _isActive = false;
        _startTime = 0;
    }

    @Override
    public String getFormattedTime() {
        if (!_isActive) {
            return "00:00:00";
        }

        long elapsedMillis = System.currentTimeMillis() - _startTime;
        long elapsedSeconds = elapsedMillis / 1000;

        long hours = elapsedSeconds / 3600;
        long minutes = (elapsedSeconds % 3600) / 60;
        long seconds = elapsedSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
