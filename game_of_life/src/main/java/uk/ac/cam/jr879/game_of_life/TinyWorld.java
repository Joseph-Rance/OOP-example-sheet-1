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

public final class TinyWorld implements World {

  PackedLong packedLong;

  TinyWorld() {
    this.packedLong = new PackedLong();
  }

  TinyWorld(long value) {
    this.packedLong = new PackedLong(value);
  }

  @Override
  public int width() {
    return 8;
  }

  @Override
  public int height() {
    return 8;
  }

  private int getIdx(int col, int row) {
    if (col < 0 || row < 0 || col >= width() || row >= height()) {
      return -1;
    }
    return row * width() + col;
  }

  @Override
  public boolean cellAlive(int col, int row) {
    int idx = getIdx(col, row);
    if (idx == -1) {
      return false;
    }
    return packedLong.get(idx);
  }

  @Override
  public TinyWorld withCellAliveness(int col, int row, boolean b) {
    TinyWorld newWorld = this.clone();
    int idx = getIdx(col, row);
    if (idx != -1) {
      newWorld.packedLong.set(idx, b);
    }
    return newWorld;
  }

  @Override
  public TinyWorld clone() {
    return new TinyWorld(this.packedLong.getLong());
  }
}
