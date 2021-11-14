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
import java.util.stream.IntStream;

public class MergeSorter<T> implements Sorter<T> {

  private T[] merge(T[] array) {

  }

  /*
  // realised first attempt was bad
  @Override
  public void sort(T[] array, Comparator<T> comparator) {
    int n = 2;
    // 1,1,2,4,3,2,3,4
    //       ^ ^
    while (n <= array.length/2) {
      for (int i = 0; i+n-1 < array.length; i += n) {
        // merge i to i+n-1 with i+n and i+2n-1
        T[] temp = (T[])new Object[2*n];
        int p1 = i;
        int p2 = i+n;
        for (int j = 0; j < 2*n; j++) {
          if (comparator.compare(array[p1], array[p2]) < 0) {  // a_p1 < a_p2
            temp[j] = array[p1];
            p1++;
          } else {  // a_p1 >= a_p2
            temp[j] = array[p2];
            p2++;
          }

          if (p1 >= i+n) {
            while (p2 < i+2*n) {
              temp[j] = array[p2];
              p2++;
              j++;
            }
            break;
          } else if (p2 >= i+2*n) {
            while (p1 < i+*n) {
              temp[j] = array[p1];
              p1++;
              j++;
            }
            break;
          }

        }
      }
      n = 2*n;
    }
    // merge 0 to i+n-1 with i+n to end
  }
  */


  @Override
  public void sort(T[] array, Comparator<T> comparator) {

  }
}
