package ru.otus.Cache;

public interface CacheEngine<K, V> {

    void put(K key,MyElement<V> element);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}