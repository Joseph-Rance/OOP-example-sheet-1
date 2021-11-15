/*
 * Copyright 2021 David Berry <dgb37@cam.ac.uk>, Joseph Rance
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

package uk.ac.cam.jr879.sorting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class TestQuickSorter {

    @Test
    public void quickSorter_sortsEmptyList() {
        // ARRANGE
        Integer[] array = new Integer[] {};

        // ACT
        new QuickSorter<Integer>().sort(array, Integer::compare);

        // ASSERT
        assertThat(array).asList().isEmpty();
    }

    @Test
    public void quickSorter_sortsAscendingIntegers() {
        // ARRANGE
        Integer[] array = new Integer[] {76,4,2,6,7,3,6};

        // ACT
        new QuickSorter<Integer>().sort(array, Integer::compareTo);

        // ASSERT
        assertThat(array).asList().containsExactly(2,3,4,6,6,7,76).inOrder();
    }

    @Test
    public void quickSorter_sortsStrings() {
        // ARRANGE
        String[] array = new String[] {"a", "c", "b", "d"};

        // ACT
        new QuickSorter<String>().sort(array, String::compareTo);

        // ASSERT
        assertThat(array).asList().containsExactly("a", "b", "c", "d").inOrder();
    }
}
