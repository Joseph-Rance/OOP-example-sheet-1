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

/**
 * Represents a world in the simulation.
 *
 * <p>Worlds are immutable and so altering cells (or computing the next generation) is done by
 * making a new copy with the required changes.
 *
 * <p>Cells outside the region stored are assumed to be dead. Implementors of this interface should
 * reflect this when handling attempts to read or write cells which are out of range.
 */
interface World {

  /** The number of columns of cells in the world. */
  int width();

  /** The number of rows of cells in the world. */
  int height();

  /**
   * Tests if a cell is alive or dead.
   *
   * @param col the column of the cell to test
   * @param row the row of the cell to test
   * @return true if the cell is alive and false if the cell is dead or out of range
   */
  boolean cellAlive(int col, int row);

  /**
   * Create a new world which is the same as this one but with one particular cell altered.
   *
   * <p>If row or column are out of range this function just returns an unchanged world.
   *
   * @param col the column of the cell to change
   * @param row the row of the cell to change
   * @param aliveness the state of the cell where true represents alive and false indicates death
   * @return a new copy of the world
   */
  World withCellAliveness(int col, int row, boolean aliveness);

  /**
   * Return the number of neighbouring cells which are alive.
   *
   * @param col the column of the cell whose neighbours we wish to count
   * @param row the row of the cell whose neighbours we wish to count
   * @return the number of neighbours which are alive
   */
  default int aliveNeighbourCount(int col, int row) {
    int[][] neighbourLocs = new int[][] { {col-1, row+1}, {col, row+1}, {col+1, row+1},
                                          {col-1, row  },               {col+1, row  },
                                          {col-1, row-1}, {col, row-1}, {col+1, row-1} };

    int count = 0;
    for (int[] pos : neighbourLocs) {
      if (pos[0] >= 0 && pos[0] < this.width() && pos[1] >= 0 && pos[1] < this.height()) {
        if (cellAlive(pos[0], pos[1])) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * Calculate whether a given cell is alive in the next generation.
   *
   * <p>Out of range cells are assumed to be dead and so this function should always return false in
   * these cases.
   *
   * @param col the column of the cell to inspect
   * @param row the row of the cell to inspect
   * @return true if the cell should be alive and false otherwise
   */
  default boolean cellAliveNextGeneration(int col, int row, Rule rule) {
    int neighbours = aliveNeighbourCount(col, row);
    return col >= 0 && col < this.width() && row >= 0 && row < this.height()
       && ((cellAlive(col, row) && rule.S(neighbours)) || (!cellAlive(col, row) && rule.B(neighbours)));
  }

  /** Return a new world containing the next generation of alive cells. */
  default World nextGeneration(Rule rule) {
    World current = withCellAliveness(0, 0, false);
    for (int col = 0; col < this.width(); col++) {
      for (int row = 0; row < this.height(); row++) {
        current = current.withCellAliveness(col, row, this.cellAliveNextGeneration(col, row, rule));
      }
    }
    return current;
  }
}
