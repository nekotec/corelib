package de.twometer.corelib.diagnostics;

public class Stopwatch {

    private long startTime;
    private long endTime;
    private boolean running;

    public static Stopwatch startNew() {
        var watch = new Stopwatch();
        watch.start();
        return watch;
    }

    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public void stop() {
        endTime = System.currentTimeMillis();
        running = false;
    }

    public long getElapsedTime() {
        return running ? System.currentTimeMillis() - startTime : endTime - startTime;
    }

}
