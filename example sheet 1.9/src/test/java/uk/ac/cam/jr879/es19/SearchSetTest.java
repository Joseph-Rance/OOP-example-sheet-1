package uk.ac.cam.jr879.es19;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SearchSetTest {

  @Test
  public void insert_insertsElement_toEmptySet() {
    // ARRANGE
    SearchSet set = new SearchSet();

    // ACT
    set.insert(2);

    // ASSERT
    assertThat(set.contains(2)).isEqualTo(true);
  }

  @Test
  public void insert_insertsElement_toNonEmptySet() {
    // ARRANGE
    SearchSet set = new SearchSet();

    // ACT
    set.insert(2);
    set.insert(3);

    // ASSERT
    assertThat(set.contains(3)).isEqualTo(true);
  }

  @Test
  public void getNumberElements_returnsZero_forEmptySet() {
    // ARRANGE
    SearchSet set = new SearchSet();

    // ACT
    int number = set.getNumberElements();

    // ASSERT
    assertThat(number).isEqualTo(0);
  }

  @Test
  public void getNumberElements_returnsCorrect_forNonEmptySet() {
    // ARRANGE
    SearchSet set = new SearchSet();
    set.insert(2);
    set.insert(3);

    // ACT
    int number = set.getNumberElements();

    // ASSERT
    assertThat(number).isEqualTo(2);
  }


  @Test
  public void contains_returnsFalse_forEmptySet() {
    // ARRANGE
    SearchSet set = new SearchSet();

    // ACT
    boolean contains = set.contains(0);

    // ASSERT
    assertThat(contains).isEqualTo(false);
  }

  @Test
  public void contains_returnsFalse_forElemNotInSet() {
    // ARRANGE
    SearchSet set = new SearchSet();
    set.insert(1);
    set.insert(2);

    // ACT
    boolean contains = set.contains(0);

    // ASSERT
    assertThat(contains).isEqualTo(false);
  }

  @Test
  public void contains_returnsTrue_forElemInSet() {
  // ARRANGE
    SearchSet set = new SearchSet();
    set.insert(1);
    set.insert(2);


    // ACT
    boolean contains = set.contains(1);

    // ASSERT
    assertThat(contains).isEqualTo(true);
  }
}
