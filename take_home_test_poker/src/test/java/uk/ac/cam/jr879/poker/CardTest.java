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

package uk.ac.cam.jr879.poker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;
import static uk.ac.cam.jr879.poker.Card.Suit.SPADES;
import static uk.ac.cam.jr879.poker.Card.Value.*;

@RunWith(JUnit4.class)
public class CardTest {

  @Test
  public void getSuit_returnsCorrectSuit() {
    // ARRANGE
    Card c = new Card(SPADES, ACE);

    // ACT
    Card.Suit s = c.getSuit();

    // ASSERT
    assertThat(s).isEqualTo(SPADES);
  }

  @Test
  public void getValue_returnsCorrectValue() {
      // ARRANGE
      Card c = new Card(SPADES, ACE);

      // ACT
      Card.Value v = c.getValue();

      // ASSERT
      assertThat(v).isEqualTo(ACE);
  }
}
