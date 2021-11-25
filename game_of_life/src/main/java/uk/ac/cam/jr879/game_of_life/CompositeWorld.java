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

public class CompositeWorld implements World {

  private final TinyWorld[][] worlds;

  private final int width;
  private final int height;

  /**
   * Creates a new instance of the world.
   *
   * <p>Note that the width and height parameters here correspond to the number of TinyWorld objects
   * to use rather than the number of cells.
   *
   * @param width the number of columns of TinyWorld objects to use
   * @param height the number of rows of TinyWorld objects to use
   */
  CompositeWorld(int width, int height) {

    this.height = height;
    this.width = width;

    worlds = new TinyWorld[this.height][this.width];

    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        worlds[y][x] = new TinyWorld();
      }
    }
  }

  CompositeWorld(TinyWorld[][] worlds) {

    this.worlds = worlds;

    this.height = worlds.length;
    this.width = worlds[0].length;
  }

  @Override
  public int width() {
    return width * 8;
  }

  @Override
  public int height() {
    return height * 8;
  }

  @Override
  public boolean cellAlive(int col, int row) {

    if (row < 0 || col < 0 || row >= this.height() || col >= this.width()) {
      return false;
    }

    int bigCol = col / 8;
    int bigRow = row / 8;
    int smallCol = col % 8;
    int smallRow = row % 8;

    return this.worlds[bigRow][bigCol].cellAlive(smallCol, smallRow);
  }

  @Override
  public CompositeWorld withCellAliveness(int col, int row, boolean b) {

    CompositeWorld newWorld = this.clone();

    if (row < 0 || col < 0 || row >= this.height() || col >= this.width()) {
      return newWorld;
    }

    int bigCol = col / 8;
    int bigRow = row / 8;
    int smallCol = col % 8;
    int smallRow = row % 8;

    newWorld.worlds[bigRow][bigCol] = newWorld.worlds[bigRow][bigCol].withCellAliveness(smallCol, smallRow, b);

    return newWorld;
  }

  @Override
  public CompositeWorld clone() {
    TinyWorld[][] newWorlds = new TinyWorld[this.height][this.width];

    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        newWorlds[y][x] = this.worlds[y][x].clone();
      }
    }

    return new CompositeWorld(newWorlds);
  }
}
