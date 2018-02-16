package ru.otus;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] a = new Generator().generateArray(50,10);
        ThreadSorter sorter = new ThreadSorter();
        System.out.println(Arrays.toString(a));

        sorter.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
