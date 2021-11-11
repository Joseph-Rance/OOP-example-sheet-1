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