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
public class LinkListTest {

    @Test
    public void linkList_toStringIsEmptyList_whenListEmpty() {
        // ARRANGE
        LinkList<Integer> empty = new LinkList<Integer>();

        // ACT
        String value = empty.toString();

        // ASSERT
        assertThat(value).isEqualTo("[]");
    }

    @Test
    public void linkList_toStringIsSingleItem_whenListContainsOneItem() {
        // ARRANGE
        LinkList<Integer> list = new LinkList<Integer>();
        list.addFirst(1);

        // ACT
        String value = list.toString();

        // ASSERT
        assertThat(value).isEqualTo("[1]");
    }

    @Test
    public void linkList_toStringIsTwoThenOne_whenListHasOneThenTwoAdded() {
        // ARRANGE
        LinkList<Integer> list = new LinkList<Integer>();
        list.addFirst(1);
        list.addFirst(2);

        // ACT
        String value = list.toString();

        // ASSERT
        assertThat(value).isEqualTo("[2,1]");
    }

    @Test
    public void linkList_returnsEmptyLinkedList_whenConstructedWithEmptyArray() {
        // ARRANGE
        Integer[] array = new Integer[0];  // ?

        // ACT
        LinkList<Integer> empty = LinkList.<Integer>create(array);

        // ASSERT
        assertThat(empty.toString()).isEqualTo("[]");
    }

    @Test
    public void linkList_returnsCorrectLinkedList_whenConstructedWithArray() {
        // ARRANGE
        Integer[] array = new Integer[] {0, 1};

        // ACT
        LinkList<Integer> list = LinkList.<Integer>create(array);

        // ASSERT
        assertThat(list.toString()).isEqualTo("[0,1]");
    }

    @Test
    public void removeFirst_throwsError_whenCalledOnNonEmptyList() {
        // ARRANGE
        Integer[] array = new Integer[0];

        // ACT
        LinkList<Integer> list = LinkList.<Integer>create(array);

        // ASSERT
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    public void removeFirst_returnsFirstElement_whenCalledOnNonEmptyList() {
        // ARRANGE
        Integer[] array = new Integer[] {0, 1};

        // ACT
        LinkList<Integer> list = LinkList.<Integer>create(array);
        int first = list.removeFirst();

        // ASSERT
        assertThat(first).isEqualTo(0);
    }

    @Test
    public void removeFirst_removesFirstElement_whenCalledOnNonEmptyList() {
        // ARRANGE
        Integer[] array = new Integer[] {0, 1};

        // ACT
        LinkList<Integer> list = LinkList.<Integer>create(array);
        list.removeFirst();

        // ASSERT
        assertThat(list.toString()).isEqualTo("[1]");
    }

    @Test
    public void get_throwsError_withNegativeIndex() {
        // ARRANGE
        LinkList<Integer> list = LinkList.<Integer>create(new Integer[] {0, 1});

        // ACT
        // ASSERT
        assertThrows(NoSuchElementException.class, () -> list.get(-1));
    }

    @Test
    public void get_throwsError_withIndexTooLarge() {
        // ARRANGE
        LinkList<Integer> list = LinkList.<Integer>create(new Integer[] {0, 1});

        // ACT
        // ASSERT
        assertThrows(NoSuchElementException.class, () -> list.get(10));
    }

    @Test
    public void get_returnsCorrectValue_withIndexWithinListBounds() {
        // ARRANGE
        LinkList<Integer> list = LinkList.<Integer>create(new Integer[] {3, 4, 5});

        // ACT
        Integer ans = list.get(1);

        // ASSERT
        assertThat(ans).isEqualTo(4);
    }

    @Test
    public void length_returnsZero_withEmptyList() {
        // ARRANGE
        LinkList<Integer> list = LinkList.<Integer>create(new Integer[0]);

        // ACT
        int len = list.length();

        // ASSERT
        assertThat(len).isEqualTo(0);
    }

    @Test
    public void length_returnsThree_withListLengthThree() {
        // ARRANGE
        LinkList<Integer> list = LinkList.<Integer>create(new Integer[] {0, 1, 2});

        // ACT
        int len = list.length();

        // ASSERT
        assertThat(len).isEqualTo(3);
    }

    @Test
    public void reverse_returnsEmpty_withEmptyInput() {
        // ARRANGE
        LinkList<Integer> list = LinkList.<Integer>create(new Integer[0]);

        // ACT
        list.reverse();

        // ASSERT
        assertThat(list.length()).isEqualTo(0);
    }

    @Test
    public void reverse_returnsReversedList_withNonEmptyInput() {
        // ARRANGE
        LinkList<Integer> list = LinkList.create(new Integer[] {4, 5, 6, 7});

        // ACT
        list.reverse();

        // ASSERT
        assertThat(list.toString()).isEqualTo("[7,6,5,4]");
    }

    @Test
    public void reorderLowHigh_returnsEmpty_withEmptyInput() {
        // ARRANGE
        LinkList<Integer> list = LinkList.<Integer>create(new Integer[0]);

        // ACT
        list.reorderLowHigh();

        // ASSERT
        assertThat(list.toString()).isEqualTo("[]");
    }

    @Test
    public void reorderLowHigh_returnsCorrect_withNonEmptyInput() {
        // ARRANGE
        LinkList<Integer> list = LinkList.<Integer>create(new Integer[] {4, 5, 6, 7, 9, 10});

        // ACT
        list.reorderLowHigh();

        // ASSERT
        assertThat(list.toString()).isEqualTo("[4,6,5,9,7,10]");
    }
}
