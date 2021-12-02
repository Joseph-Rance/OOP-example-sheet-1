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

package uk.ac.cam.jr879.productofotherelements;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    int[] testArr = new int[] {1, 2, 3, 4, 5};
    int[] result = findArrayProducts(testArr);
    System.out.println(Arrays.toString(result));
  }

  static int[] findArrayProducts(int[] arr) {

    int prod = 1;  // prod is the product of the array
                   // excluding 0s
    int num_zeros = 0;
    int zero_idx = 0; // index of any 0 in arr

    for (int j = 0; j < arr.length; j++) {
      if (arr[j] == 0) {
        num_zeros++;
        zero_idx = j;
      }
      else {
        prod *= arr[j];
      }
    }

    int[] newArr = new int[arr.length];

    if (num_zeros == 1) {
      newArr[zero_idx] = prod;
      return newArr; // if there is 1 0 there is only one
                     // non-0 element in the output
    }
    else if (num_zeros == 0) {
      for (int i = 0; i < arr.length; i++) {
        newArr[i] = prod / arr[i];
      }
    }

    return newArr; // if there are >1 0s return array of 0s
  }

  static double[] findArrayProducts(double[] arr) {

    double prod = 1;  // prod is the product of the array
    // excluding 0s
    int num_zeros = 0;
    int zero_idx = 0; // index of any 0 in arr

    for (int j = 0; j < arr.length; j++) {
      if (arr[j] == 0) {
        num_zeros++;
        zero_idx = j;
      }
      else {
        prod *= arr[j];
      }
    }

    double[] newArr = new double[arr.length];

    if (num_zeros == 1) {
      newArr[zero_idx] = prod;
      return newArr; // if there is 1 0 there is only one
      // non-0 element in the output
    }
    else if (num_zeros == 0) {
      for (int i = 0; i < arr.length; i++) {
        newArr[i] = prod / arr[i];
      }
    }

    return newArr; // if there are >1 0s return array of 0s
  }
}
