package de.twometer.corelib;

import de.twometer.corelib.async.Promise;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PromiseTest {

    private static final int TOLERANCE = 50; // Timeout tolerance in ms

    @Test
    void basicTest() {
        var promise = new Promise<String>();
        assertFalse(promise.isDone());

        promise.resolve("Test");
        assertTrue(promise.isDone());
        assertEquals("Test", promise.getResult());
    }

    @Test
    void waitTest() {
        var promise = new Promise<String>();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                promise.resolve("Test");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        assertTimeout(Duration.ofMillis(1000 + TOLERANCE), () -> assertTrue(promise.await()));
        assertTrue(promise.isDone());
        assertEquals(promise.getResult(), "Test");
    }

    @Test
    void timeoutTest() {
        var promise = new Promise<String>();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                promise.resolve("Test");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        assertTimeout(Duration.ofMillis(500 + TOLERANCE), () -> assertFalse(promise.await(500)));
        assertFalse(promise.isDone());
    }

}
