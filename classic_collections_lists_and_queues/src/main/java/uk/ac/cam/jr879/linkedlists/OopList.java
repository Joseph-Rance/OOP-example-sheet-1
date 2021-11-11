package uk.ac.cam.jr879.linkedlists;

import java.util.NoSuchElementException;

public interface OopList<T> {
    void addFirst(T element);
    T removeFirst();
    T get(int n);
    int length();
    public String toString();
}