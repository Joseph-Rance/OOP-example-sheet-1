/*
 * Copyright 2021 Andrew Rice <acr31@cam.ac.uk>, Joseph Rance
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

package uk.ac.cam.jr879.matrices;

import java.util.Arrays;

/** An immutable matrix of arbitrary dimensions. */
class Matrix {

  private final double[][] elements;
  private final int width;
  private final int height;

  private Matrix(int height, int width) {
    this(new double[height][width]);
  }

  private double[][] deepClone(double[][] in) {
    double[][] out = new double[in.length][in[0].length];
    for (int i = 0; i < in.length; i++) {
      out[i] = in[i].clone();
    }
    return out;
  }

  /** Create a new matrix based on the elements provided. */
  Matrix(double[][] elements) {
    this.elements = deepClone(elements);
    this.width = elements[0].length;
    this.height = elements.length;
  }

  private double dot(double[] v1, double[] v2) {
    double total = 0;
    for (int i = 0; i < v1.length; i++) {
      total += v1[i] * v2[i];
    }
    return total;
  }

  /** Multiply this matrix by the provided matrix and return the result. */
  Matrix mult(Matrix other) {  // other is second operand

    if (other.height() != width) {
      throw new IllegalArgumentException("Dimension mismatch");
    }

    Matrix otherTransposed = other.transpose();
    double[][] ans = new double[height][other.width()];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < other.width(); j++) {
        ans[i][j] = dot(get(i), otherTransposed.get(j));
      }
    }

    return new Matrix(ans);
  }

  /** Add this matrix to the provided matrix and return the result. */
  Matrix add(Matrix other) {
    if (width != other.width || height != other.height) {
      throw new IllegalArgumentException("Dimension mismatch");
    }
    Matrix r = new Matrix(height, width);
    for (int col = 0; col < width; col++) {
      for (int row = 0; row < height; row++) {
        r.elements[row][col] = elements[row][col] + other.elements[row][col];
      }
    }
    return r;
  }

  /** Transpose this matrix and return the result. */
  Matrix transpose() {
    double[][] m = new double[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        m[j][i] = get(i, j);
      }
    }
    return new Matrix(m);
  }

  /**
   * Return one element of the matrix.
   *
   * @param row the row of the element
   * @param col the column of the element
   * @return the value of the element
   */
  double get(int row, int col) {
    return elements[row][col];
  }

  double[] get(int row) { return elements[row].clone(); }

  int width() {
    return width;
  }

  int height() {
    return height;
  }

  @Override
  public String toString() {
    return Arrays.deepToString(elements);
  }
}
