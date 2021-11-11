/*
 * Copyright 2021 Ben Philps <bp413@cam.ac.uk>, Andrew Rice <acr31@cam.ac.uk>, Joseph Rance
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

package uk.ac.cam.jr879.linkedlists;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.NoSuchElementException;

@RunWith(JUnit4.class)
public class LinkQueueTest {

    @Test
    public void push_addsToBackList_whenGivenValue() {
        // ARRANGE
        LinkQueue<Integer> queue = new LinkQueue<Integer>();

        // ACT
        queue.push(1);

        // ASSERT
        assertThat(queue.toString()).isEqualTo("[]\n[1]");
    }

    @Test
    public void pop_returnsFirstElem_whenFrontListNonEmpty() {
        // ARRANGE
        LinkQueue<Integer> queue = new LinkQueue<Integer>();

        queue.push(0);
        queue.push(1);
        queue.push(2);
        queue.push(3);

        queue.pop();  // is there a way to do this without calling pop here?

        // ACT

        Integer elem = queue.pop();

        // ASSERT
        assertThat(elem).isEqualTo(1);
    }

    @Test
    public void pop_returnsCorrectElem_whenFrontListEmpty() {
        // ARRANGE
        LinkQueue<Integer> queue = new LinkQueue<Integer>();

        queue.push(0);
        queue.push(1);
        queue.push(2);
        queue.push(3);

        // ACT
        Integer elem = queue.pop();

        // ASSERT
        assertThat(elem).isEqualTo(0);
    }

    @Test
    public void pop_raisesError_whenBothListsEmpty() {
        // ARRANGE
        LinkQueue<Integer> queue = new LinkQueue<Integer>();

        // ACT
        // ASSERT
        assertThrows(NoSuchElementException.class, queue::pop);
    }

    @Test
    public void toString_printsCorrectString_whenBothListsNonEmpty() {
        // ARRANGE
        LinkQueue<Integer> queue = new LinkQueue<Integer>();
        queue.push(0);
        queue.push(0);
        queue.pop();
        queue.push(1);

        // ACT
        String str = queue.toString();

        // ASSERT
        assertThat(str).isEqualTo("[0]\n[1]");
    }

    @Test
    public void toString_printsCorrectString_whenBothListsEmpty() {
        // ARRANGE
        LinkQueue<Integer> queue = new LinkQueue<Integer>();

        // ACT
        String str = queue.toString();

        // ASSERT
        assertThat(str).isEqualTo("[]\n[]");
    }
}