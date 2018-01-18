package ru.otus.Cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("UncheckedCast")

public class CacheEngineImpl<K,V> implements CacheEngine<K,V> {

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private final Map<K, SoftReference<MyElement<V>>> elements = new HashMap<K, SoftReference<MyElement<V>>>();

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
        }
        elements.put(key, new SoftReference<>(element));
    }


    public V get(K key) {
        SoftReference<MyElement<V>> softReference = elements.get(key);
        if (softReference != null) {
            return softReference.get().getValue();
        }
        else {
            return null;
        }
    }

    public int getHitCount() {
        return 0;
    }

    public int getMissCount() {
        return 0;
    }

    public void dispose() {

    }
}
