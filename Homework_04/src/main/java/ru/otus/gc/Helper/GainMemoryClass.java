package ru.otus.gc.Helper;

import ru.otus.gc.GC.Benchmark;

import java.util.LinkedList;
import java.util.List;

public class GainMemoryClass {

    private int counter=0;

    public void startGainMemory(int size, Benchmark benchmark) {
        List<Object> gainList = new LinkedList<>();
        benchmark.startTime();

        while (true) {

            for (int i = 0; i < size; i++) {
                gainList.add(new Object[1024][1024]);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < size /2; j++) {
                gainList.remove(j);
            }
            this.counter++;
            benchmark.startMonitoring();
        }
    }

    public int getCounter() {
        return counter;
    }
}
