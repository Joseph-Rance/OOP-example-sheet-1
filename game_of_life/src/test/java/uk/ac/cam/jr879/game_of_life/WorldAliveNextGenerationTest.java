/*
 * Copyright 2021 David Berry <dgb37@cam.ac.uk>, Joe Isaacs <josi2@cam.ac.uk>, Andrew Rice <acr31@cam.ac.uk>, Joseph Rance
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

package uk.ac.cam.jr879.game_of_life;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

public class WorldAliveNextGenerationTest {

  @Test
  public void aliveNextGeneration_isBorn_withThreeNeighboursInGameOfLife() {
    // ARRANGE
    StubWorld w =
        new StubWorld() {

          @Override
          public int width() {
              return 3;
          }

          @Override
          public int height() {
              return 3;
          }

          @Override
          public boolean cellAlive(int col, int row) {
            return false;
          }

          @Override
          public int aliveNeighbourCount(int col, int row) {
            return 3;
          }
        };

    // ACT
    boolean alive = w.cellAliveNextGeneration(1, 1, Rule.gameOfLife());

    // ASSERT
    assertThat(alive).isTrue();
  }

    @Test
    public void aliveNextGeneration_isNotBorn_withTwoNeighboursInGameOfLife() {
        // ARRANGE
        StubWorld w =
                new StubWorld() {

                    @Override
                    public int width() {
                        return 3;
                    }

                    @Override
                    public int height() {
                        return 3;
                    }

                    @Override
                    public boolean cellAlive(int col, int row) {
                        return false;
                    }

                    @Override
                    public int aliveNeighbourCount(int col, int row) {
                        return 2;
                    }
                };

        // ACT
        boolean alive = w.cellAliveNextGeneration(1, 1, Rule.gameOfLife());

        // ASSERT
        assertThat(alive).isFalse();
    }

    @Test
    public void aliveNextGeneration_isNotBorn_withFourNeighboursInGameOfLife() {
        // ARRANGE
        StubWorld w =
                new StubWorld() {

                    @Override
                    public int width() {
                        return 3;
                    }

                    @Override
                    public int height() {
                        return 3;
                    }

                    @Override
                    public boolean cellAlive(int col, int row) {
                        return false;
                    }

                    @Override
                    public int aliveNeighbourCount(int col, int row) {
                        return 4;
                    }
                };

        // ACT
        boolean alive = w.cellAliveNextGeneration(1, 1, Rule.gameOfLife());

        // ASSERT
        assertThat(alive).isFalse();
    }

    @Test
    public void aliveNextGeneration_dies_withOneNeighbourInGameOfLife() {
        // ARRANGE
        StubWorld w =
                new StubWorld() {

                    @Override
                    public int width() {
                        return 3;
                    }

                    @Override
                    public int height() {
                        return 3;
                    }

                    @Override
                    public boolean cellAlive(int col, int row) {
                        return true;
                    }

                    @Override
                    public int aliveNeighbourCount(int col, int row) {
                        return 1;
                    }
                };

        // ACT
        boolean alive = w.cellAliveNextGeneration(1, 1, Rule.gameOfLife());

        // ASSERT
        assertThat(alive).isFalse();
    }

    @Test
    public void aliveNextGeneration_dies_withFourNeighboursInGameOfLife() {
        // ARRANGE
        StubWorld w =
                new StubWorld() {

                    @Override
                    public int width() {
                        return 3;
                    }

                    @Override
                    public int height() {
                        return 3;
                    }

                    @Override
                    public boolean cellAlive(int col, int row) {
                        return true;
                    }

                    @Override
                    public int aliveNeighbourCount(int col, int row) {
                        return 4;
                    }
                };

        // ACT
        boolean alive = w.cellAliveNextGeneration(1, 1, Rule.gameOfLife());

        // ASSERT
        assertThat(alive).isFalse();
    }

    @Test
    public void aliveNextGeneration_lives_withTwoNeighboursInGameOfLife() {
        // ARRANGE
        StubWorld w =
                new StubWorld() {

                    @Override
                    public int width() {
                        return 3;
                    }

                    @Override
                    public int height() {
                        return 3;
                    }

                    @Override
                    public boolean cellAlive(int col, int row) {
                        return true;
                    }

                    @Override
                    public int aliveNeighbourCount(int col, int row) {
                        return 2;
                    }
                };

        // ACT
        boolean alive = w.cellAliveNextGeneration(1, 1, Rule.gameOfLife());

        // ASSERT
        assertThat(alive).isTrue();
    }

    @Test
    public void aliveNextGeneration_lives_withThreeNeighboursInGameOfLife() {
        // ARRANGE
        StubWorld w =
                new StubWorld() {

                    @Override
                    public int width() {
                        return 3;
                    }

                    @Override
                    public int height() {
                        return 3;
                    }

                    @Override
                    public boolean cellAlive(int col, int row) {
                        return true;
                    }

                    @Override
                    public int aliveNeighbourCount(int col, int row) {
                        return 3;
                    }
                };

        // ACT
        boolean alive = w.cellAliveNextGeneration(1, 1, Rule.gameOfLife());

        // ASSERT
        assertThat(alive).isTrue();
    }

    @Test
    public void aliveNextGeneration_lives_withThreeNeighboursInSeeds() {
        // ARRANGE
        StubWorld w =
                new StubWorld() {

                    @Override
                    public int width() {
                        return 3;
                    }

                    @Override
                    public int height() {
                        return 3;
                    }

                    @Override
                    public boolean cellAlive(int col, int row) {
                        return true;
                    }

                    @Override
                    public int aliveNeighbourCount(int col, int row) {
                        return 3;
                    }
                };

        // ACT
        boolean alive = w.cellAliveNextGeneration(1, 1, Rule.seeds());

        // ASSERT
        assertThat(alive).isFalse();
    }
}
