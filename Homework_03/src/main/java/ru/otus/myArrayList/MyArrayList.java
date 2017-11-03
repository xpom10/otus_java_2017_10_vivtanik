package ru.otus.myArrayList;

import java.util.*;

public class MyArrayList<T> implements List {

    private int size;
    private T[] array;
    private int point = 0;

    public MyArrayList(int initSize) {
        if (initSize > 0) this.array = (T[]) new Object[initSize];
        else if (initSize == 0) {
            this.array = (T[]) new Object[]{};
        }
    }

    public MyArrayList() {
        this.array = (T[]) new Object[]{};
    }


    public int size() {
        this.size = array.length;
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) return true;
        }
        return false;
    }

    public Iterator iterator() {
        return null;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    public boolean add(Object o) {
        if (point == array.length) {
            this.array = reSize(array.length + 1);
        }
        array[point++] = (T) o;
        return true;
    }



    private T[] reSize(int newSize) {
        T[] newValue = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newValue, 0, point);
        this.size = newValue.length;
        return newValue;
    }


    public boolean remove(Object o) {
        return false;
    }

    public boolean addAll(Collection c) {
        return false;
    }

    public boolean addAll(int index, Collection c) {
        return false;
    }

    public void clear() {

    }

    public Object get(int index) {
        return array[index];
    }

    public Object set(int index, Object element) {
        this.array[index] = (T) element;
        return array;
    }

    public void add(int index, Object element) {

    }

    public Object remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator listIterator() {
        return new ListIterator()
        {   int count = 0;

            public boolean hasNext() {
                return count < size;
            }

            public Object next() {
                return array[count++];
            }

            public boolean hasPrevious() {
                return false;
            }

            public Object previous() {
                return null;
            }

            public int nextIndex() {
                return 0;
            }

            public int previousIndex() {
                return 0;
            }

            public void remove() {

            }

            public void set(Object o) {
                if (count == 0) array[count] = (T) o;
                else array[count-1] = (T) o;
            }

            public void add(Object o) {

            }
        };
    }

    public ListIterator listIterator(int index) {
        return null;
    }

    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    public Object[] toArray(Object[] a) {
        return null;
    }


}
