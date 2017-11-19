package ru.otus.gc;

import ru.otus.gc.GC.Benchmark;
import ru.otus.gc.Helper.GainMemoryClass;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String... argc) throws Exception {

        int size = 10;

        Benchmark benchmark = new Benchmark();
        GainMemoryClass memoryClass = new GainMemoryClass();

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("ru.otus.gc:type=Benchmark");
        Benchmark mbean = new Benchmark();
        mBeanServer.registerMBean(mbean, name);

        mbean.setSize(size);

        try {
            memoryClass.startGainMemory(mbean.getSize(), benchmark);
        } catch (OutOfMemoryError error) {
            System.out.println(error.getLocalizedMessage());
        }

//        benchmark.writeStatisticsToFile("111.txt");
        benchmark.printStatisticsToTerminal();
    }

}
