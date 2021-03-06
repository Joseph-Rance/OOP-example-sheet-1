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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** A hand of cards dealt to the player. */
public class Hand implements Comparable<Hand> {

  private final List<Card> cards;
  private final HandRank rank;
  private final List<Card> highestCards;

  private Hand(List<Card> cards, HandRank rank, List<Card> highestCards) {
    this.cards = List.copyOf(cards);
    this.rank = rank;
    this.highestCards = highestCards;
  }

  /** Create a hand of cards and work out what rank it has. */
  static Hand create(List<Card> cards) {
    for (HandRank handRank : HandRank.values()) {
      List<Card> highestCards = handRank.highCardsIfMatching(cards);
      if (!highestCards.isEmpty()) {
        return new Hand(cards, handRank, highestCards);
      }
    }
    throw new IllegalArgumentException("Failed to match rank for this hand");
  }

  /** Create an empty hand with a specific rank (for testing). */
  static Hand create(HandRank handRank) {
    return new Hand(List.of(), handRank, new ArrayList<Card>());
  }

  HandRank getRank() {
    return rank;
  }

  List<Card> getHighestCards() {
    return highestCards;
  }

  List<Card> getCards() {
    return cards;
  }


  @Override
  public int compareTo(Hand o) {
    int c = o.getRank().compareTo(this.getRank());
    int idx = 0;
    while (c == 0) {
      c = this.getHighestCards().get(idx).getValue().ordinal()
           - o.getHighestCards().get(idx).getValue().ordinal();
      idx++;
      if (idx >= Math.max(this.getHighestCards().size(), o.getHighestCards().size())) {
        break;
      }
    }
    if (c == 0) {
      return 0; // WHAT HAPPENS NOW???
    }
    c = c / Math.abs(c);
    return c;
  }
}
