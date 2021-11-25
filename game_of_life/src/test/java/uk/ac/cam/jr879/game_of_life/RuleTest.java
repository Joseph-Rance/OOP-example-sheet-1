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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;
import static uk.ac.cam.jr879.game_of_life.WorldStringUtils.lines;
import static uk.ac.cam.jr879.game_of_life.WorldStringUtils.worldToString;

@RunWith(JUnit4.class)
public class RuleTest {

    @Test
    public void gameOfLife_Dies_whenOneNeighbour() {
        // ARRANGE
        Rule rule = Rule.gameOfLife();

        // ACT
        boolean alive = rule.S(1);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void gameOfLife_notDies_whenTwoNeighbours() {
        // ARRANGE
        Rule rule = Rule.gameOfLife();

        // ACT
        boolean alive = rule.S(2);

        // ASSERT
        assertThat(alive).isEqualTo(true);
    }

    @Test
    public void gameOfLife_notDies_whenThreeNeighbours() {
        // ARRANGE
        Rule rule = Rule.gameOfLife();

        // ACT
        boolean alive = rule.S(3);

        // ASSERT
        assertThat(alive).isEqualTo(true);
    }

    @Test
    public void gameOfLife_Dies_whenFourNeighbours() {
        // ARRANGE
        Rule rule = Rule.gameOfLife();

        // ACT
        boolean alive = rule.S(4);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void gameOfLife_notBorn_whenTwoNeighbour() {
        // ARRANGE
        Rule rule = Rule.gameOfLife();

        // ACT
        boolean alive = rule.B(2);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void gameOfLife_born_whenThreeNeighbours() {
        // ARRANGE
        Rule rule = Rule.gameOfLife();

        // ACT
        boolean alive = rule.B(3);

        // ASSERT
        assertThat(alive).isEqualTo(true);
    }

    @Test
    public void gameOfLife_notBorn_whenFourNeighbours() {
        // ARRANGE
        Rule rule = Rule.gameOfLife();

        // ACT
        boolean alive = rule.B(4);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void highLife_Dies_whenOneNeighbour() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.S(1);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void highLife_notDies_whenTwoNeighbours() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.S(2);

        // ASSERT
        assertThat(alive).isEqualTo(true);
    }

    @Test
    public void highLife_notDies_whenThreeNeighbours() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.S(3);

        // ASSERT
        assertThat(alive).isEqualTo(true);
    }

    @Test
    public void highLife_Dies_whenFourNeighbours() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.S(4);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void highLife_notBorn_whenTwoNeighbours() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.B(2);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void highLife_born_whenThreeNeighbours() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.B(3);

        // ASSERT
        assertThat(alive).isEqualTo(true);
    }

    @Test
    public void highLife_notBorn_whenFourNeighbours() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.B(4);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void highLife_born_whenSixNeighbours() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.B(6);

        // ASSERT
        assertThat(alive).isEqualTo(true);
    }

    @Test
    public void highLife_notBorn_whenSevenNeighbours() {
        // ARRANGE
        Rule rule = Rule.highLife();

        // ACT
        boolean alive = rule.B(7);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void Seeds_notBorn_whenOneNeighbour() {
        // ARRANGE
        Rule rule = Rule.seeds();

        // ACT
        boolean alive = rule.B(1);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void Seeds_born_whenTwoNeighbours() {
        // ARRANGE
        Rule rule = Rule.seeds();

        // ACT
        boolean alive = rule.B(2);

        // ASSERT
        assertThat(alive).isEqualTo(true);
    }

    @Test
    public void Seeds_notBorn_whenThreeNeighbours() {
        // ARRANGE
        Rule rule = Rule.seeds();

        // ACT
        boolean alive = rule.B(3);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }

    @Test
    public void Seeds_dies_whenThreeNeighbours() {
        // ARRANGE
        Rule rule = Rule.seeds();

        // ACT
        boolean alive = rule.S(3);

        // ASSERT
        assertThat(alive).isEqualTo(false);
    }
}
