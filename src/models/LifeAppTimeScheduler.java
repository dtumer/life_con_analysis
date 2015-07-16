package models;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Deniz on 7/15/2015.
 */
public class LifeAppTimeScheduler {
    private Timer timer;

    public void start(TimerTask task, int interval) {
        timer = new Timer();
        timer.schedule(task, 0, interval);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
    }
}
