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
import static uk.ac.cam.jr879.game_of_life.WorldStringUtils.lines;
import static uk.ac.cam.jr879.game_of_life.WorldStringUtils.worldToString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TinyWorldTest {

  @Test
  public void width_returns8() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld();

    // ACT
    int width = tinyWorld.width();

    // ASSERT
    assertThat(width).isEqualTo(8);
  }

  @Test
  public void height_returns8() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld();

    // ACT
    int width = tinyWorld.height();

    // ASSERT
    assertThat(width).isEqualTo(8);
  }

  @Test
  public void cellAlive_returnsFalse_whenOutOfRange() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld();

    // ACT
    boolean cellAlive = tinyWorld.cellAlive(-1, 0);

    // ASSERT
    assertThat(cellAlive).isFalse();
  }

  @Test
  public void cellAlive_returnsFalse_whenCellNotAlive() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld(0L);

    // ACT
    boolean cellAlive = tinyWorld.cellAlive(0, 0);

    // ASSERT
    assertThat(cellAlive).isFalse();
  }

  @Test
  public void cellAlive_returnsFalse_whenCellAlive() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld(1L);

    // ACT
    boolean cellAlive = tinyWorld.cellAlive(0, 0);

    // ASSERT
    assertThat(cellAlive).isTrue();
  }

  @Test
  public void withCellAliveness_doesNotChange_withInvalidCoordinates() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld();

    // ACT
    tinyWorld = tinyWorld.withCellAliveness(-1, 0, true);

    // ASSERT
    assertThat(worldToString(tinyWorld))
            .isEqualTo(
                    lines(
                            "________",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________"));
  }

  @Test
  public void withCellAliveness_doesNotChangeOriginal() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld();

    // ACT
    tinyWorld.withCellAliveness(0, 0, true);

    // ASSERT
    assertThat(worldToString(tinyWorld))
        .isEqualTo(
            lines(
                "________",
                "________",
                "________",
                "________",
                "________",
                "________",
                "________",
                "________"));
  }

  @Test
  public void withCellAliveness_SpecifiedCellToTrueOnly() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld();

    // ACT
    tinyWorld = tinyWorld.withCellAliveness(1, 1, true);

    assertThat(worldToString(tinyWorld))
    // ASSERT
            .isEqualTo(
                    lines(
                            "________",
                            "_#______",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________"));
  }

  @Test
  public void withCellAliveness_SpecifiedCellToFalseOnly() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld(16777215L);

    // ACT
    tinyWorld = tinyWorld.withCellAliveness(3, 1, false);

    // ASSERT
    assertThat(worldToString(tinyWorld))
            .isEqualTo(
                    lines(
                            "########",
                            "###_####",
                            "########",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________"));
  }


  @Test
  public void stringToWorld_returnsCorrectWorld() {
    // ARRANGE
    TinyWorld tinyWorld = (TinyWorld)WorldStringUtils.stringToWorld(new TinyWorld(),
                                                              "#_______",
                                                                    "________",
                                                                    "________",
                                                                    "________",
                                                                    "________",
                                                                    "________",
                                                                    "________",
                                                                    "________");

    // ACT
    long packedLong = tinyWorld.packedLong.getLong();

    // ASSERT
    assertThat(packedLong).isEqualTo(1L);
  }

  @Test
  public void worldToString_returnsCorrectString() {
    // ARRANGE
    TinyWorld tinyWorld = new TinyWorld(1L);

    // ACT
    String string = WorldStringUtils.worldToString(tinyWorld);

    // ASSERT
    assertThat(worldToString(tinyWorld))
            .isEqualTo(
                    lines(
                            "#_______",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________",
                            "________"));
  }
}