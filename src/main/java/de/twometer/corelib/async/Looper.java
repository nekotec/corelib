package de.twometer.corelib.async;

public class Looper {

    private volatile boolean running = false;
    private final Runnable loopTarget;
    private Thread thread;

    public Looper(Runnable loopFunction) {
        this.loopTarget = loopFunction;
    }

    public void start() {
        if (!running) {
            running = true;
            thread = new Thread(this::run);
            thread.start();
        } else throw new IllegalStateException("Cannot start a running thread");
    }

    public void stop() {
        if (running) {
            running = false;
        } else throw new IllegalStateException("Cannot stop a non-running thread");
    }

    public void join() throws InterruptedException {
        thread.join();
    }

    private void run() {
        while (running) {
            loopTarget.run();
        }
    }

}
