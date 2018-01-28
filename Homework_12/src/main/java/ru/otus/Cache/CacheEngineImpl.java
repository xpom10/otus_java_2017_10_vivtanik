package ru.otus.Cache;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.function.Function;


public class CacheEngineImpl<K,V> implements CacheEngine<K,V> {

    private static final int TIME_THRESHOLD_MS = 5;

    private int hitCount = 0;
    private int missCount = 0;

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private final Map<K, SoftReference<MyElement<V>>> elements = new HashMap<>();
    private final Timer timer = new Timer();


    public CacheEngineImpl(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
        this.isEternal = lifeTimeMs == 0 && idleTimeMs == 0 || isEternal;
    }


    public void put(K key,MyElement<V> element) {
        if (elements.size() == maxElements) {
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
            System.out.println("Remove from cache id: " + firstKey.toString());
        }
        elements.put(key, new SoftReference<>(element));
        if (!isEternal) {
            if (lifeTimeMs != 0) {
                TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> Objects.requireNonNull(lifeElement.get()).getCreationTime() + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }
            if (idleTimeMs != 0) {
                TimerTask idleTimerTask = getTimerTask(key, idleElement -> Objects.requireNonNull(idleElement.get()).getLastAccessTime() + idleTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
            }
        }
    }


    public V get(K key) {
        SoftReference<MyElement<V>> softReference = elements.get(key);
        if (softReference != null) {
            hitCount++;
            Objects.requireNonNull(softReference.get()).setAccessed();
            return Objects.requireNonNull(softReference.get()).getValue();
        }
        else {
            missCount++;
            return null;
        }
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getMissCount() {
        return missCount;
    }

    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<SoftReference<MyElement<V>>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<MyElement<V>> element = elements.get(key);
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }

    public Map<String,Object> getStat() {
        Map<String,Object> stat = new HashMap<>();
        stat.put("maxElements",maxElements);
        stat.put("lifeTimeMs",lifeTimeMs);
        stat.put("idleTimeMs",idleTimeMs);
        stat.put("isEternal",String.valueOf(isEternal));
        return stat;
    }
}
