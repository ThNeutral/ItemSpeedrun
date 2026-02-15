package state.time.implementations;

import state.time.ITimer;

public class Timer implements ITimer {
    private boolean _isActive;
    private long _startTime;
    private long _stopTime;

    public Timer() {
        _isActive = false;
        _startTime = 0;
        _stopTime = 0;
    }

    @Override
    public void start() {
        _isActive = true;
        _startTime = System.currentTimeMillis();
        _stopTime = 0;
    }

    @Override
    public void stop() {
        _isActive = false;
        _stopTime = System.currentTimeMillis();
    }

    @Override
    public String getFormattedTime() {
        long elapsedMillis = System.currentTimeMillis() - _startTime;
        if (!_isActive) {
            elapsedMillis = _stopTime - _startTime;
        }
        long elapsedSeconds = elapsedMillis / 1000;

        long hours = elapsedSeconds / 3600;
        long minutes = (elapsedSeconds % 3600) / 60;
        long seconds = elapsedSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
