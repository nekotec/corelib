package de.twometer.corelib;

import de.twometer.corelib.diagnostics.Benchmark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BenchmarkTest {

    private void benchMethod() {
        //noinspection StatementWithEmptyBody
        for (int i = 0; i < 1E5; i++) ;
    }

    @Test
    void test() {
        var bm = new Benchmark(2000, 10000);
        bm.run(this::benchMethod);
        bm.printStats("Test benchmark");

        assertNotEquals(0, bm.getTotalTime());
        assertEquals(bm.getTimePerCycle(), bm.getTotalTime() / 10000);
        assertEquals(bm.getCyclesPerSecond(), 1000 / bm.getTimePerCycle());
    }

}
