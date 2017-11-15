package ru.otus.gc.GC;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Benchmark implements BenchmarkMBean {

    private int size;
    private Map<String, GCStat> statistics = new HashMap<>();

    public void startMonitoring() {
        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc: gcList) {
            if (this.statistics.containsKey(gc.getName())) {
                GCStat gcStat = statistics.get(gc.getName());
                gcStat.setCount(gc.getCollectionCount());
                gcStat.setTime(gc.getCollectionTime());
                statistics.put(gc.getName(), gcStat);
            }
            else {
                statistics.put(gc.getName(), new GCStat(gc.getCollectionTime(), gc.getCollectionCount()));
            }
        }
    }


    public void writeStatisticsToFile() {

    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}
