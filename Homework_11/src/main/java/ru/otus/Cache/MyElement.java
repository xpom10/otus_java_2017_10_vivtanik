package ru.otus.Cache;

public class MyElement<V> {
    private V value;

    public MyElement(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
