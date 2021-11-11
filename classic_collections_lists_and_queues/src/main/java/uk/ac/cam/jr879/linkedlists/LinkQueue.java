package uk.ac.cam.jr879.linkedlists;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkQueue<T extends Comparable<T>> implements OopQueue<T> {

    private LinkList<T> frontList = new LinkList<T>();
    private LinkList<T> backList = new LinkList<T>();

    public void push(T val) {
        backList.addFirst(val);
    }

    public T pop() {
        if (frontList.length() == 0) {

            if (backList.length() == 0) {
                throw new NoSuchElementException();
            }

            backList.reverse();
            frontList = backList;
            backList = new LinkList<T>();
        }


        T elem = frontList.removeFirst();

        return elem;
    }

    public String toString() {
        return frontList + "\n" + backList;
    }
}
