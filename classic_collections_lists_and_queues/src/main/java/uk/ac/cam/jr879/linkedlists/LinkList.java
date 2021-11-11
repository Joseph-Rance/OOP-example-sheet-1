/*
 * Copyright 2021 Ben Philps <bp413@cam.ac.uk>, Andrew Rice <acr31@cam.ac.uk>, Joseph Rance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.cam.jr879.linkedlists;

import jdk.jshell.spi.ExecutionControlProvider;

import java.util.Collections;

import java.util.NoSuchElementException;

public class LinkList<T extends Comparable<T>> implements OopList<T> {

    private static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        Node(T value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            if (next == null) {
                return String.valueOf(value);
            }
            return value + "," + next;
        }
    }

    private Node<T> head;

    LinkList() {
        this.head = null;
    }

    public void addFirst(T element) {
        if (head == null) {
            head = new Node<T>(element);
        } else {
            head = new Node<T>(element, head);
        }
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            T old = head.value;
            head = head.next;
            return old;
        }
    }

    public T get(int n){
        if (n < 0 || head == null) {
            throw new NoSuchElementException();
        }

        Node<T> current = head;
        while (n > 0) {

            current = current.next;
            n--;

            if (current == null) {
                throw new NoSuchElementException();
            }

        }

        return current.value;
    }

    public int length() {
        int n = 0;
        Node<T> current = head;
        while (current != null) {
            current = current.next;
            n++;
        }
        return n;
    }

    public void reverse() {
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> next;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    private static <T> void reverseArray(T[] array) {
        int i = 0;
        int j = array.length - 1;

        while (i < j) {
            T temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            i++;
            j--;
        }

        // no return since mutates input
    }

    static <T extends Comparable<T>> LinkList create(T[] elements) {
        reverseArray(elements);  // reverse -> addFirst last elems of original list first
        LinkList list = new LinkList();
        for (T element: elements) {
            list.addFirst(element);
        }
        return list;
    }

    public void reorderLowHigh() {
        Node<T> current = head;
        int ctr = 0;

        while (current.next != null) {
            if (ctr % 2 == 0 && current.value > current.next.value
             || ctr % 2 == 1 && current.value < current.next.value) {
                T temp = current.value;
                current.value = current.next.value;
                current.next.value = temp;
            }
            current = current.next;
            ctr++;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]", head == null ? "" : head.toString());
    }
}
