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

import java.util.AbstractList;

class PackedLong extends AbstractList<Boolean> {

  long packed;

  public PackedLong() {
    this.packed = 0L;
  }

  public PackedLong(long value) {
    this.packed = value;
  }

  @Override
  public int size() {
    return 64;
  }

  @Override
  public Boolean get(int idx) {
    return PackedLong.get(this.packed, idx);
  }

  @Override
  public Boolean set(int idx, Boolean value) {
    boolean original = PackedLong.get(this.packed, idx);
    this.packed = PackedLong.set(this.packed, idx, value);
    return original;
  }

  public long getLong() {
    return this.packed;
  }

  /**
   * Unpack the nth bit from the packed number and return it
   *
   * @param packed the packed number
   * @param position the position of the bit we are interested in from 0 to 63 inclusive
   * @return the value of the position'th bit
   */
  public static boolean get(long packed, int position) {
    return ((packed >>> position) & 1) == 1;
  }

  /**
   * Set the nth bit in the packed number to the value given and return the new packed number
   *
   * @param packed the packed number
   * @param position the position of the bit of interest
   * @param value the value to set the bit to
   * @return the new packed number
   */
  public static long set(long packed, int position, boolean value) {
    if (value) {
      packed |= 1L << position;
    } else {
      packed &= ~(1L << position);
    }
    return packed;
  }
}
