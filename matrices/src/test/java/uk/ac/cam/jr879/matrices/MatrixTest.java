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

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MatrixTest {

  @Test
  public void get_producesCorrectAnswer() {

    Matrix a = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });

    assertThat(a.get(0, 0)).isEqualTo(1);
    assertThat(a.get(0, 1)).isEqualTo(2);
    assertThat(a.get(0, 2)).isEqualTo(3);
    assertThat(a.get(1, 0)).isEqualTo(4);
    assertThat(a.get(1, 1)).isEqualTo(5);
    assertThat(a.get(1, 2)).isEqualTo(6);

  }

  @Test
  public void width_producesCorrectAnswer() {
    Matrix a = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });

    assertThat(a.width()).isEqualTo(3);
  }


  @Test
  public void height_producesCorrectAnswer() {
    Matrix a = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });

    assertThat(a.height()).isEqualTo(2);
  }

  @Test
  public void add_producesCorrectAnswer() {
    // ARRANGE
    Matrix a = new Matrix(new double[][] {
              {1, 2, 3}, //
              {4, 5, 6}
        });
    Matrix b = new Matrix(new double[][] {
              {7, 8, 9}, //
              {10, 11, 12},
        });

    // ACT
    Matrix c = a.add(b);

    // ASSERT
    assertThat(c.get(0, 0)).isWithin(1E-7).of(8);
    assertThat(c.get(0, 1)).isWithin(1E-7).of(10);
    assertThat(c.get(0, 2)).isWithin(1E-7).of(12);
    assertThat(c.get(1, 0)).isWithin(1E-7).of(14);
    assertThat(c.get(1, 1)).isWithin(1E-7).of(16);
    assertThat(c.get(1, 2)).isWithin(1E-7).of(18);
  }

  @Test
  public void mult_producesCorrectAnswer() {
    // ARRANGE
    Matrix a = new Matrix(new double[][] {
                            {1, 2, 3},
                            {4, 5, 6}
            });
    Matrix b = new Matrix(new double[][] {
                            {7, 8},
                            {9, 1},
                            {2, 3}
            });
    Matrix c = new Matrix(new double[][] {
                    {31, 19},
                    {85, 55}
            });

    // ACT
    Matrix d = a.mult(b);

    // ASSERT
    assertThat(d.get(0, 0)).isWithin(1E-7).of(c.get(0, 0));
    assertThat(d.get(0, 1)).isWithin(1E-7).of(c.get(0, 1));
    assertThat(d.get(1, 0)).isWithin(1E-7).of(c.get(1, 0));
    assertThat(d.get(1, 1)).isWithin(1E-7).of(c.get(1, 1));
  }

  @Test
  public void transpose_producesCorrectAnswer() {
    // ARRANGE
    Matrix a = new Matrix(new double[][] {
                    {1, 2, 3},
                    {4, 5, 6}
            });
    Matrix b = new Matrix(new double[][] {
                    {1, 4},
                    {2, 5},
                    {3, 6}
            });

    // ACT
    Matrix c = a.transpose();

    // ASSERT
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        assertThat(c.get(i, j)).isWithin(1E-7).of(b.get(i, j));
      }
    }
  }

  @Test
  public void constructor_doesNotMutate() {
    // ARRANGE
    double[][] a = new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    };

    // ACT
    Matrix b = new Matrix(a);

    // ASSERT
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        a[i][j] += 1;
        assertThat(b.get(i, j)).isNotEqualTo(a[i][j]);
      }
    }
  }

  @Test
  public void add_doesNotMutate() {
    // ARRANGE
    Matrix a = new Matrix(new double[][] {
                    {1, 2, 3}, //
                    {4, 5, 6}
            });
    Matrix b = new Matrix(new double[][] {
                    {1, 2, 3}, //
                    {4, 5, 6}
            });
    Matrix c = new Matrix(new double[][] {
                    {7, 8, 9}, //
                    {10, 11, 12}
            });
    Matrix d = new Matrix(new double[][] {
                    {7, 8, 9}, //
                    {10, 11, 12}
            });

    // ACT
    a.add(c);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 1; j++) {
        assertThat(a.get(i, j)).isWithin(1E-7).of(b.get(i, j));
        assertThat(c.get(i, j)).isWithin(1E-7).of(d.get(i, j));
      }
    }
  }

  @Test
  public void transpose_doesNotMutate() {
    // ARRANGE
    Matrix a = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });
    Matrix b = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });

    // ACT
    a.transpose();

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 1; j++) {
        assertThat(a.get(i, j)).isWithin(1E-7).of(b.get(i, j));
      }
    }
  }

  @Test
  public void mult_doesNotMutate() {
    // ARRANGE
    Matrix a = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });
    Matrix b = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });
    Matrix c = new Matrix(new double[][] {
            {7, 10}, //
            {8, 11},
            {9, 12}
    });
    Matrix d = new Matrix(new double[][] {
            {7, 10}, //
            {8, 11},
            {9, 12}
    });

    // ACT
    a.mult(c);

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 1; j++) {
        assertThat(a.get(i, j)).isWithin(1E-7).of(b.get(i, j));
        assertThat(c.get(j, i)).isWithin(1E-7).of(d.get(j, i));
      }
    }
  }

  @Test
  public void add_checksDimensions() {
    Matrix a = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });
    Matrix b = new Matrix(new double[][] {
            {1, 2}, //
            {4, 5}
    });

    assertThrows(IllegalArgumentException.class, () -> a.add(b));
  }

  @Test
  public void mult_checksDimensions() {
    Matrix a = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });
    Matrix b = new Matrix(new double[][] {
            {1, 2, 3}, //
            {4, 5, 6}
    });

    assertThrows(IllegalArgumentException.class, () -> a.mult(b));
  }
}