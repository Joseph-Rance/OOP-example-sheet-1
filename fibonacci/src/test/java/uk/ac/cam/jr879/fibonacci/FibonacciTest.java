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

package uk.ac.cam.jr879.fibonacci;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FibonacciTest {

  @Test
  public void fibonacci_returns1_for1() {
    // ARRANGE
    Fibonacci fibonacci = new Fibonacci();

    // ACT
    int result = fibonacci.fib(1);

    // ASSERT
    assertThat(result).isEqualTo(1);
  }

  @Test
  public void fibonacci_returns21_for8() {
    // ARRANGE
    Fibonacci fibonacci = new Fibonacci();

    // ACT
    int result = fibonacci.fib(8);

    // ASSERT
    assertThat(result).isEqualTo(21);
  }

  @Test
  public void fib_throwsIllegalArgumentException_atMinus1() {
    // ARRANGE
    Fibonacci fibonacci = new Fibonacci();

    // ACT
    // ASSERT
    assertThrows(IllegalArgumentException.class, () -> fibonacci.fib(-1));
  }
}
