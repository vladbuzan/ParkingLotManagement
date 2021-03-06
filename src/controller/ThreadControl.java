package controller;

import view.View;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ThreadControl {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final View view;

    ThreadControl(View view) {
        this.view = view;
    }

    void start() {
        Runnable dateThread = new DateUpdaterThread(view);
        Runnable feeThread = new FeeUpdaterThread(view);

        scheduler.scheduleAtFixedRate(dateThread, 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(feeThread, 0, 1, TimeUnit.MINUTES);
    }

    void stop() {
        scheduler.shutdown();
    }

}
