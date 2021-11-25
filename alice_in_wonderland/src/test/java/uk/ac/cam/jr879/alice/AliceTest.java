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

package uk.ac.cam.jr879.alice;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AliceTest {

  @Test
  public void countWords_returns0_forEmptyList() {
    // ARRANGE
    List<Token> words = List.of();

    // ACT
    long count = Alice.countWords(words);

    // ASSEERT
    assertThat(count).isEqualTo(0);
  }

  @Test
  public void countWords_returns0_whenOnlyPunctuation() {
    // ARRANGE
    List<Token> words = List.of(new Token(".", ".", 1.0), new Token(",", ",", 1.0));

    // ACT
    long count = Alice.countWords(words);

    // ASSEERT
    assertThat(count).isEqualTo(0);
  }

  @Test
  public void countWords_returnsFive_whenFiveWords() {
    // ARRANGE
    List<Token> words = List.of(new Token("and", "CC", 1.0),
                                new Token("the", "DT", 1.0),
                                new Token("happy", "JJ", 1.0),
                                new Token("summer", "NN", 1.0),
                                new Token("days", "NNS", 1.0));

    // ACT
    long count = Alice.countWords(words);

    // ASSEERT
    assertThat(count).isEqualTo(5);
  }

  @Test
  public void countWords_returnsFive_whenFiveWordsAndPunctutation() {
    // ARRANGE
    List<Token> words = List.of(new Token(".", ".", 1.0),
                                new Token("and", "CC", 1.0),
                                new Token("the", "DT", 1.0),
                                new Token(":", ":", 1.0),
                                new Token("happy", "JJ", 1.0),
                                new Token("summer", "NN", 1.0),
                                new Token("days", "NNS", 1.0),
                                new Token(",", ",", 1.0));

    // ACT
    long count = Alice.countWords(words);

    // ASSEERT
    assertThat(count).isEqualTo(5);
  }

  @Test
  public void vocabulary_ignoresCase() {
    // ARRANGE
    List<Token> words =
        List.of(
            new Token("Alice", "NNP", 1.0),
            new Token("alice", "NNP", 1.0),
            new Token("Queen", "NNP", 1.0),
            new Token("King", "NNP", 1.0),
            new Token("King", "NNP", 1.0));

    // ACT
    List<String> vocab = Alice.vocabulary(words, 2);

    // ASSERT
    assertThat(vocab).containsExactly("alice", "king");
  }

  @Test
  public void vocabulary_ignoresPunctuation() {
    // ARRANGE
    List<Token> words =
            List.of(
                    new Token(".", ".", 1.0),
                    new Token(".", ".", 1.0),
                    new Token(".", ".", 1.0),
                    new Token("King", "NNP", 1.0),
                    new Token("King", "NNP", 1.0));

    // ACT
    List<String> vocab = Alice.vocabulary(words, 2);

    // ASSERT
    assertThat(vocab).containsExactly("king");
  }

  @Test
  public void vocabulary_returnsEmptyList_whenGivenEmptyList() {
    // ARRANGE
    List<Token> words = List.of();

    // ACT
    List<String> vocab = Alice.vocabulary(words, 2);

    // ASSERT
    assertThat(vocab).containsExactly();
  }

  @Test
  public void topN_returnsTopOne() {
    // ARRANGE
    Map<String, Long> frequencies = Map.of("apple", 10L,
                                           "pear", 5L,
                                           "banana", 1L);

    // ACT
    List<String> top = Alice.topN(1, frequencies);

    // ASSERT
    assertThat(top).containsExactly("apple");
  }

  @Test
  public void topN_returnsTopThree() {
    // ARRANGE
    Map<String, Long> frequencies = Map.of("apple", 10L,
                                           "pear", 5L,
                                           "banana", 1L,
                                           "mango", 3L);

    // ACT
    List<String> top = Alice.topN(3, frequencies);

    // ASSERT
    assertThat(top).containsExactly("apple", "pear", "mango");
  }

  @Test
  public void topN_returnsEmpty_whenGivenEmpty() {
    // ARRANGE
    Map<String, Long> frequencies = Map.of();

    // ACT
    List<String> top = Alice.topN(2, frequencies);

    // ASSERT
    assertThat(top).containsExactly();
  }

  @Test
  public void topN_returnsEmpty_whenNIsZero() {
    // ARRANGE
    Map<String, Long> frequencies = Map.of("apple", 10L,
                                           "pear", 5L,
                                           "banana", 1L,
                                           "mango", 3L);

    // ACT
    List<String> top = Alice.topN(0, frequencies);

    // ASSERT
    assertThat(top).containsExactly();
  }

  @Test
  public void topN_returnsAll_ifNotEnoughPresent() {
    // ARRANGE
    Map<String, Long> frequencies = Map.of("apple", 10L, "pear", 5L, "banana", 1L);

    // ACT
    List<String> top = Alice.topN(10, frequencies);

    // ASSERT
    assertThat(top).containsExactly("apple", "pear", "banana");
  }

  @Test
  public void properNouns_returnsTopOne() {
    // ARRANGE
    List<Token> words = List.of(new Token(".", ".", 1.0),
            new Token("and", "NNP", 1.0),
            new Token("and", "NNP", 1.0),
            new Token("the", "NNP", 1.0),
            new Token("summer", "NNP", 1.0),
            new Token("days", "NNP", 1.0),
            new Token(",", ",", 1.0));

    // ACT
    List<String> top = Alice.properNouns(words, 1);

    // ASSERT
    assertThat(top).containsExactly("and");
  }

  @Test
  public void properNouns_returnsTopThree() {
    // ARRANGE
    List<Token> words = List.of(new Token(".", ".", 1.0),
            new Token("and", "NNP", 1.0),
            new Token("and", "NNP", 1.0),
            new Token("the", "NNP", 1.0),
            new Token("the", "NNP", 1.0),
            new Token("the", "NNP", 1.0),
            new Token("summer", "NNP", 1.0),
            new Token("days", "NNP", 1.0),
            new Token("days", "NNP", 1.0),
            new Token(",", ",", 1.0));

    // ACT
    List<String> top = Alice.properNouns(words, 3);

    // ASSERT
    assertThat(top).containsExactly("and", "the", "days");
  }

  @Test
  public void properNouns_returnsEmpty_whenGivenEmpty() {
    // ARRANGE
    List<Token> words = List.of();

    // ACT
    List<String> top = Alice.properNouns(words, 3);

    // ASSERT
    assertThat(top).containsExactly();
  }

  @Test
  public void properNouns_returnsEmpty_whenNIsZero() {
    // ARRANGE
    List<Token> words = List.of(new Token(".", ".", 1.0),
            new Token("and", "NNP", 1.0),
            new Token("and", "NNP", 1.0),
            new Token("the", "NNP", 1.0));

    // ACT
    List<String> top = Alice.properNouns(words, 0);

    // ASSERT
    assertThat(top).containsExactly();
  }

  @Test
  public void properNouns_returnsAll_ifNotEnoughPresent() {
    // ARRANGE
    List<Token> words = List.of(new Token(".", ".", 1.0),
            new Token("and", "NNP", 1.0),
            new Token("and", "NNP", 1.0),
            new Token("the", "NNP", 1.0));

    // ACT
    List<String> top = Alice.properNouns(words, 3);

    // ASSERT
    assertThat(top).containsExactly("and", "the");
  }

  @Test
  public void properNouns_doesNotCountOtherWords() {
    // ARRANGE
    List<Token> words = List.of(new Token(".", ".", 1.0),
            new Token("and", "CC", 1.0),
            new Token("and", "CC", 1.0),
            new Token("the", "DT", 1.0),
            new Token("the", "DT", 1.0),
            new Token("the", "DT", 1.0),
            new Token("PNoun", "NNP", 1.0),
            new Token("summer", "NN", 1.0),
            new Token("days", "NNS", 1.0),
            new Token("days", "NNS", 1.0),
            new Token(",", ",", 1.0));

    // ACT
    List<String> top = Alice.properNouns(words, 1);

    // ASSERT
    assertThat(top).containsExactly("PNoun");
  }

  @Test
  public void leastConfidentToken_returnsNull_forEmptyList() {
    // ARRANGE
    List<Token> words = List.of();

    // ACT
    Token LCToken = Alice.leastConfidentToken(words);

    // ASSEERT
    assertThat(LCToken).isEqualTo(null);
  }

  @Test
  public void leastConfidentToken_returnsLeastConfident_forNonEmptyList() {
    // ARRANGE
    List<Token> words = List.of(new Token("and", "CC", 1.0),
                                new Token("the", "DT", 0.8),
                                new Token("happy", "JJ", 0.1),
                                new Token("summer", "NN", 1.0),
                                new Token("days", "NNS", 1.0));

    // ACT
    Token LCToken = Alice.leastConfidentToken(words);

    // ASSERT
    assertThat(LCToken.contents()).isEqualTo("happy");
  }

  @Test
  public void posFrequencies_returnsEmptySet_forEmptyList() {
    // ARRANGE
    List<Token> words = List.of();

    // ACT
    Map<String, Long> frequencies = Alice.posFrequencies(words);

    // ASSERT
    assertThat(frequencies.isEmpty()).isEqualTo(true);
  }

  @Test
  public void posFrequencies_returnsCorrectSet() {
    // ARRANGE
    List<Token> words = List.of(new Token("happy", "JJ", 0.1),
                                new Token("happy", "JJ", 0.1),
                                new Token("summer", "NN", 1.0),
                                new Token("summer", "NN", 1.0),
                                new Token("summer", "NN", 1.0),
                                new Token("days", "NNS", 1.0));

    // ACT
    Map<String, Long> frequencies = Alice.posFrequencies(words);

    // ASSERT
    assertThat(frequencies.toString()).isEqualTo("{JJ=2, NN=3, NNS=1}");
  }

  // This test is not really useful but its here to make sure we get coverage of the Token class
  @Test
  public void tokenToString_returnsOneDecimalPlace() {
    // ARRANGE
    Token token = new Token("Alice", "NNP", 1.888);

    // ACT
    String string = token.toString();

    // ASSERT
    assertThat(string).isEqualTo(String.format("Alice(NNP:%.1f)", 1.9));
  }
}