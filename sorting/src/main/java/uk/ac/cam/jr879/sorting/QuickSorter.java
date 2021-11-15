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

import java.util.Comparator;

public class QuickSorter<T> implements Sorter<T> {

  private int partition(T[] array, int start, int end, Comparator<T> comparator) {
    T pivot = array[end-1];
    int split = start - 1;

    for (int current = start; current < end; current++) {
      if (comparator.compare(array[current], pivot) <= 0) {
        split++;
        T temp = array[current];
        array[current] = array[split];
        array[split] = temp;
      }
    }
    return split;
  }

  private void qs(T[] array, int start, int end, Comparator<T> comparator) { // end exclusive
    if (end - start < 2) {
      return;
    }

    int pivot = partition(array, start, end, comparator);

    qs(array, start, pivot, comparator);
    qs(array, pivot+1, end, comparator);
  }

  @Override
  public void sort(T[] array, Comparator<T> comparator) {
    qs(array, 0, array.length, comparator);
  }
}
