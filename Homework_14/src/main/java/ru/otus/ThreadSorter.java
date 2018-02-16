package ru.otus;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ThreadSorter<T extends Comparable<? super T>> {

    private final int numberOfThreads;

    public ThreadSorter() {
        this.numberOfThreads = 4;
    }

    public ThreadSorter(int threads) {
        this.numberOfThreads = threads;
    }



    public void sort(T[] array) {
        if (array != null) {
            ForkJoinPool pool = new ForkJoinPool(numberOfThreads);
            pool.invoke(new Task(array));
        } else {
            throw new IllegalArgumentException("Array is null");
        }
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
                Task subTask1 = new Task(left);
                Task subTask2 = new Task(right);
                subTask1.fork();
                subTask2.fork();
                merge(subTask1.join(), subTask2.join(), array);
            }
            return array;
        }

        private void merge(T[] array1, T[] array2, T[] mergeResult) {
            int i = 0, j = 0;
            for (int step = 0; step < array1.length + array2.length; step++) {
                boolean forStep;
                if (i < array1.length && j < array2.length) {
                    forStep = array1[i].compareTo(array2[j]) < 0;
                } else {
                    forStep = i < array1.length;
                }
                mergeResult[step] = forStep ? array1[i++] : array2[j++];
            }
        }


    }


}

