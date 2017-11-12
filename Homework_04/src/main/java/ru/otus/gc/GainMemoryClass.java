package ru.otus.gc;

import java.util.LinkedList;
import java.util.List;

public class GainMemoryClass {

    private List<Object> gainList;
    private final int sizeOfList = 10;
    private int counter=0;

    public void startGainMemory() {
        this.gainList = new LinkedList<>();

        while (true) {

            for (int i = 0; i < sizeOfList; i++) {
                gainList.add(new Object[1024][1024]);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < sizeOfList/2; j++) {
                gainList.remove(j);
            }
            this.counter++;
            System.out.println("Cycle: " + counter + " ListSize: " + gainList.size());
        }
    }
}
