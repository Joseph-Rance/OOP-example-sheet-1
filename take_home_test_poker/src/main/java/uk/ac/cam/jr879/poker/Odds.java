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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for counting the occurrences of a particular hand rank and tabulating their probabilities.
 */
class Odds {

  private Counter dealCount;
  private Counter winCount;
  private long totalDealt;

  Odds() {
    this.dealCount = new Counter();
    this.winCount = new Counter();
    this.totalDealt = 0;
  }

  /** Record that we have been dealt this hand. */
  void dealt(Hand hand) {
    dealCount.inc(hand);
    totalDealt++;
  }

    /** Record that we have won with this hand. */
    void won(Hand hand) {
        winCount.inc(hand);
    }

  /** Return a list of the probabilities for each defined hand rank. */
  List<Count> tabulateByRank() {
    return Arrays.stream(HandRank.values())
        .map(
            handRank -> {
              double countD = dealCount.getOrDefault(handRank, 0L);
              double countW = winCount.getOrDefault(handRank, 0L);
              double dealProbability = totalDealt == 0 ? 0 : countD / totalDealt;
              double winProbability = countD == 0 ? 0 : countW / countD;
              return new Count(handRank, dealProbability, winProbability);
            })
        .collect(Collectors.toList());
  }

  private static class Counter extends HashMap<HandRank, Long> {
    void inc(Hand hand) {
      compute(hand.getRank(), (k, v) -> v == null ? 1 : v + 1);
    }
  }
}
