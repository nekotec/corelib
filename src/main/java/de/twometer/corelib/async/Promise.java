package de.twometer.corelib.async;

public class Promise<T> {

    private T result;
    private boolean done;
    private final Object monitor = new Object();

    public T getResult() {
        return result;
    }

    public boolean isDone() {
        return done;
    }

    public void resolve(T result) {
        synchronized (monitor) {
            this.result = result;
            this.done = true;
            monitor.notifyAll();
        }
    }

    public boolean await() {
        synchronized (monitor) {
            uninterruptibleWait(0L);
            return done;
        }
    }

    public boolean await(long timeout) {
        synchronized (monitor) {
            uninterruptibleWait(timeout);
            return done;
        }
    }

    private void uninterruptibleWait(long timeout) {
        long startTime = System.currentTimeMillis();
        while (!done && (timeout == 0 || System.currentTimeMillis() - startTime < timeout)) {
            try {
                monitor.wait(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
