package ru.otus.gc.GC;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Benchmark implements BenchmarkMBean {

    private int size;
    private Map<String, GCStat> statistics = new HashMap<>();
    private long duration;
    private long startTimeNano;
    private List<GarbageCollectorMXBean> gcList;

    public void startMonitoring() {
        this.gcList = ManagementFactory.getGarbageCollectorMXBeans();
    }

    public void getCurrentStatistics() {
        for (GarbageCollectorMXBean gc: gcList) {
            if (this.statistics.containsKey(gc.getName())) {
                GCStat gcStat = statistics.get(gc.getName());
                gcStat.setCount(gc.getCollectionCount());
                gcStat.setTime(gc.getCollectionTime());
                this.statistics.put(gc.getName(), gcStat);
            }
            else {
                this.statistics.put(gc.getName(), new GCStat(gc.getCollectionTime(), gc.getCollectionCount()));
            }
        }
    }

    public void writeStatisticsToFile(String filename) {
        try {
            this.duration = (System.nanoTime() - startTimeNano) / 1000000000;
            FileWriter file = new FileWriter(filename);
            StringBuilder str = new StringBuilder();
            str.append("\n");
            Set<String> set = this.statistics.keySet();
            for (String key : set) {
                str.append("GarbageCollector: ").append(key).append("\n");
                str.append("The number of garbage collections: ").append(statistics.get(key).getCount()).append("\n");
                str.append("Time of garbage collector per minute: ").append(statistics.get(key).getTime() / duration).append(" ms/m").append("\n");
                str.append("---------------------------------------\n");
            }
            str.append("Duration of work: ").append(duration/60).append(" m ").append(duration % 60).append(" s").append("\n");
            file.write(String.valueOf(str));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void printStatisticsToTerminal() {
        StringBuilder str = new StringBuilder();
        str.append("\n");
        Set<String> set = this.statistics.keySet();
        str.append("---------------------------------------\n");
        for (String key : set) {
            str.append("GarbageCollector: ").append(key).append("\n");
            str.append("The number of garbage collections: ").append(statistics.get(key).getCount()).append("\n");
            str.append("Time of garbage collector per minute: ").append(statistics.get(key).getTime() / duration).append(" ms/m").append("\n");
            str.append("---------------------------------------\n");
        }
        str.append("Duration of work: ").append(duration/60).append(" m ").append(duration % 60).append(" s").append("\n");
        System.out.println(str);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    public void startTime() {
        this.startTimeNano = System.nanoTime();
    }
}
