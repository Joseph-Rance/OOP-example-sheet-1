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

import java.util.Arrays;
import java.util.Collections;

import java.util.NoSuchElementException;

public class ArrayedList<T> implements OopList<T> {

    private T[] array;
    private int size = 0;  // size always points to the next open space

    ArrayedList() {
        this(1);
    }

    ArrayedList(int length) {
        this.array = (T[])new Object[length];
    }

    private void doubleLength() {
        T[] newArray = (T[])new Object[array.length*2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void addFirst(T element) {
        if (size == array.length) {
            doubleLength();
        }

        for (int i = array.length-1; i > 0; i--) {
            array[i] = array[i-1];
        }
        array[0] = element;
        size++;
    }

    public T removeFirst() {

        if (size == 0) {
            throw new NoSuchElementException();
        }

        T out = array[0];

        for (int i = 0; i < array.length - 1; i++) {
            array[i] = array[i+1];
        }
        size--;
        return out;
    }

    public T get(int n){
        try {
            return array[n];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    public int length() {
        return size;
    }

    public void reverse() {  // is there any way to avoid pasting this code twice?
        int i = 0;
        int j = size - 1;

        while (i < j) {
            T temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            i++;
            j--;
        }
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

    static <T> ArrayedList create(T[] elements) {
        reverseArray(elements);  // reverse -> addFirst last elems of original list first
        ArrayedList list = new ArrayedList();
        for (T element: elements) {
            list.addFirst(element);
        }
        return list;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        String output = "[" + array[0];
        for (int i = 1; i < size; i++) {
            output += "," + array[i];
        }
        return output + "]";
    }
}
