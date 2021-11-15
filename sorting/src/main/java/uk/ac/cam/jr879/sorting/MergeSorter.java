/*
 * Copyright 2021 David Berry <dgb37@cam.ac.uk>, Joseph Rance
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

package uk.ac.cam.jr879.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class MergeSorter<T> implements Sorter<T> {

  private void merge(T[] array, int start, int size, Comparator<T> comparator) {

    T[] first = Arrays.copyOfRange(array, start, start+size);

    int end = Math.max(start+size, Math.min(start+2*size, array.length));
    T[] second = Arrays.copyOfRange(array, start+size, end);

    int p1 = 0;
    int p2 = 0;
    int p3 = start;

    while (p3 < start + 2*size) {

      if (p1 == first.length) {
        while (p2 < second.length) {
          array[p3] = second[p2];
          p2++;
          p3++;
        }
        break;
      } if (p2 == second.length) {
        while (p1 < first.length) {
          array[p3] = first[p1];
          p1++;
          p3++;
        }
        break;
      }

      T a = first[p1];
      T b = second[p2];

      if (comparator.compare(a, b) < 0) {
        array[p3] = a;
        p1++;
      } else {
        array[p3] = b;
        p2++;
      }

      if (p2 >= start+2*size) {
        break;
      } else if (p1 >= size) {
        break;
      }

      p3++;
    }
  }

  @Override
  public void sort(T[] array, Comparator<T> comparator) {
    for (int size = 1; size < array.length; size *= 2) {
      for (int start = 0; start < array.length; start += size * 2) {
        merge(array, start, size, comparator);
      }
    }
  }
}
