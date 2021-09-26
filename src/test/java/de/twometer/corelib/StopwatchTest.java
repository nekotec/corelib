package de.twometer.corelib;

import de.twometer.corelib.diagnostics.Stopwatch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopwatchTest {

    @Test
    void testBasic() throws InterruptedException {
        var sw = new Stopwatch();

        long start = System.currentTimeMillis();
        sw.start();

        Thread.sleep(1000);

        long stop = System.currentTimeMillis();
        sw.stop();

        long duration = stop - start;
        assertEquals(duration, sw.getElapsedTime());
    }

    @Test
    void testStatic() {
        var sw1 = new Stopwatch();
        sw1.start();

        var sw2 = Stopwatch.startNew();

        assertEquals(sw1.getElapsedTime(), sw2.getElapsedTime());
    }

}
