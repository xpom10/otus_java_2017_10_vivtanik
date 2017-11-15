package ru.otus.gc.Helper;

import ru.otus.gc.GC.Benchmark;

import java.util.LinkedList;
import java.util.List;

public class GainMemoryClass {

    private int counter=0;

    public void startGainMemory( int size) {
        List<Object> gainList = new LinkedList<>();

        while (true) {

            int sizeOfList = size;
            for (int i = 0; i < sizeOfList; i++) {
                gainList.add(new Object[1024][1024]);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < sizeOfList /2; j++) {
                gainList.remove(j);
            }
            this.counter++;
            new Benchmark().startMonitoring();
        }
    }
}
