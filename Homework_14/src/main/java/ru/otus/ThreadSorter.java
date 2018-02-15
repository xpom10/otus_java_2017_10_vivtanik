package ru.otus;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ThreadSorter<T extends Comparable<? super T>> {

    private int THREAD_NUMBER = 4;

    public ThreadSorter() {}

    public ThreadSorter(int threads) {
        this.THREAD_NUMBER = threads;
    }



    public void sort(T[] array) {
        ForkJoinPool pool = new ForkJoinPool(THREAD_NUMBER);
        pool.invoke(new Task(array));
    }

    private class Task extends RecursiveTask<T[]> {

        private T[] array;

        Task(T[] array) {
            this.array = array;
        }

        @Override
        protected T[] compute() {
            if (array.length > 1) {
                T[] left = Arrays.copyOfRange(array, 0, array.length / 2);
                T[] right = Arrays.copyOfRange(array, array.length / 2, array.length);
                Task h1 = new Task(left);
                Task h2 = new Task(right);
                h1.fork();
                h2.fork();
                merge(h1.join(), h2.join(), array);
            }
            return array;
        }

        private void merge(T[] array1, T[] array2, T[] result) {
            int i = 0, j = 0, k = 0;
            for (; i < array1.length && j < array2.length; ) {
                if (array1[i].compareTo(array2[j]) < 0) {
                    result[k] = array1[i];
                    i++;
                } else {
                    result[k] = array2[j];
                    j++;
                }
                k++;
            }
            for (; i < array1.length; i++, k++) {
                result[k] = array1[i];
            }
            for (; j < array2.length; j++, k++) {
                result[k] = array2[j];
            }
        }
    }
}

