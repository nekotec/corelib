package de.twometer.corelib.diagnostics;

public class Benchmark {

    private final int warmupCycles;
    private final int benchmarkCycles;

    private double totalTime;
    private double timePerCycle;
    private double cyclesPerSecond;

    public Benchmark(int warmupCycles, int benchmarkCycles) {
        this.warmupCycles = warmupCycles;
        this.benchmarkCycles = benchmarkCycles;
    }

    public void run(Runnable runnable) {
        for (int i = 0; i < warmupCycles; i++)
            runnable.run();

        var startTime = System.nanoTime();
        for (int i = 0; i < benchmarkCycles; i++)
            runnable.run();
        var endTime = System.nanoTime();

        totalTime = (endTime - startTime) / 1E6;
        timePerCycle = totalTime / benchmarkCycles;
        cyclesPerSecond = 1000 / timePerCycle;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public double getTimePerCycle() {
        return timePerCycle;
    }

    public double getCyclesPerSecond() {
        return cyclesPerSecond;
    }

    public void printStats(String title) {
        System.out.println("==== " + title + " ====");
        System.out.println("Warmup cycles:           " + warmupCycles);
        System.out.println("Number of cycles:        " + benchmarkCycles);
        System.out.println("Total duration [ms]:     " + totalTime);
        System.out.println("Duration per cycle [ms]: " + timePerCycle);
        System.out.println("Cycles per second:       " + cyclesPerSecond);
    }

}
