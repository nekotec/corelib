package de.twometer.corelib;

import de.twometer.corelib.async.Looper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LooperTest {

    @Test
    void test() throws InterruptedException {
        AtomicInteger loops = new AtomicInteger();
        AtomicLong lastLoop = new AtomicLong(0);

        var looper = new Looper(() -> {
            loops.incrementAndGet();
            lastLoop.set(System.currentTimeMillis());
        });
        looper.start();

        Thread.sleep(1000);
        looper.stop();
        looper.join();

        Assertions.assertTrue(loops.get() > 10, "Expected more than 10 loops, got " + loops.get());

        long timeDiff = System.currentTimeMillis() - lastLoop.get();
        Assertions.assertTrue(timeDiff < 10, "Expected no more loops after shutdown (loop recorded " + timeDiff + " ms after shutdown)");
    }

}
