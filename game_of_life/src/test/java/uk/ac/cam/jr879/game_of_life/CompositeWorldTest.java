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
import static uk.ac.cam.jr879.game_of_life.WorldStringUtils.stringToWorld;
import static uk.ac.cam.jr879.game_of_life.WorldStringUtils.worldToString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CompositeWorldTest {

  @Test
  public void width_returns16_whenSizeIs2() {
    // ARRANGE
    CompositeWorld compositeWorld = new CompositeWorld(2, 1);

    // ACT
    int width = compositeWorld.width();

    // ASSERT
    assertThat(width).isEqualTo(16);
  }

  @Test
  public void height_returns8_whenSizeIs1() {
    // ARRANGE
    CompositeWorld compositeWorld = new CompositeWorld(2, 1);

    // ACT
    int height = compositeWorld.height();

    // ASSERT
    assertThat(height).isEqualTo(8);
  }

  @Test
  public void cellAlive_returnsFalse_whenOutOfRange() {
    // ARRANGE
    World compositeWorld =
            stringToWorld(
                    new CompositeWorld(2, 1),
                                 "________________",
                                       "________________",
                                       "________#_______",
                                       "_________#______",
                                       "_______###______",
                                       "________________",
                                       "________________",
                                       "________________");
    // ACT
    boolean alive = compositeWorld.cellAlive(-1, 0);

    // ASSERT
    assertThat(alive).isEqualTo(false);
  }

  @Test
  public void cellAlive_returnsFalse_whenCellNotAlive() {
    // ARRANGE
    World compositeWorld =
            stringToWorld(
                    new CompositeWorld(2, 1),
                                 "________________",
                                       "________________",
                                       "________#_______",
                                       "_________#______",
                                       "_______###______",
                                       "________________",
                                       "________________",
                                       "________________");
    // ACT
    boolean alive = compositeWorld.cellAlive(0, 0);

    // ASSERT
    assertThat(alive).isEqualTo(false);
  }

  @Test
  public void cellAlive_returnsTrue_whenCellAlive() {
    // ARRANGE
    World compositeWorld =
            stringToWorld(
                    new CompositeWorld(2, 1),
                                 "________________",
                                       "________________",
                                       "________#_______",
                                       "_________#______",
                                       "_______###______",
                                       "________________",
                                       "________________",
                                       "________________");
    // ACT
    boolean alive = compositeWorld.cellAlive(8, 2);

    // ASSERT
    assertThat(alive).isEqualTo(true);
  }

  @Test
  public void withCellAliveness_changesCell() {
    // ARRANGE
    World compositeWorld =
        stringToWorld(
            new CompositeWorld(2, 1),
                         "________________",
                               "________________",
                               "________#_______",
                               "_________#______",
                               "_______###______",
                               "________________",
                               "________________",
                               "________________");
    // ACT
    World next = compositeWorld.withCellAliveness(0, 0, true);

    // ASSERT
    assertThat(worldToString(next))
        .isEqualTo(
            lines(
                "#_______________",
                "________________",
                "________#_______",
                "_________#______",
                "_______###______",
                "________________",
                "________________",
                "________________"));
  }

  @Test
  public void withCellAliveness_doesntChangeCell_whenCellOutOfRange() {
    // ARRANGE
    World compositeWorld =
            stringToWorld(
                    new CompositeWorld(2, 1),
                                 "________________",
                                       "________________",
                                       "________#_______",
                                       "_________#______",
                                       "_______###______",
                                       "________________",
                                       "________________",
                                       "________________");
    // ACT
    World next = compositeWorld.withCellAliveness(0, -1, true);

    // ASSERT
    assertThat(worldToString(next))
            .isEqualTo(
                    lines(
                            "________________",
                            "________________",
                            "________#_______",
                            "_________#______",
                            "_______###______",
                            "________________",
                            "________________",
                            "________________"));
  }
}
