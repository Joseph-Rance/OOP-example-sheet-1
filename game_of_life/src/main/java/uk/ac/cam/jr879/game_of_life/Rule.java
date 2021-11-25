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

public class Rule {

  public boolean B(int neighbours) {
    return true;
  }

  public boolean S(int neighbours) {
    return false;
  }

  static Rule gameOfLife() {
    return new Rule() {
      public boolean B(int neighbours) {
        return neighbours == 3;
      }

      public boolean S(int neighbours) {
        return neighbours > 1 && neighbours < 4;
      }
    };
  }

  static Rule highLife() {
    return new Rule() {
      public boolean B(int neighbours) {
        return neighbours == 3 || neighbours == 6;
      }

      public boolean S(int neighbours) {
        return neighbours > 1 && neighbours < 4;
      }
    };
  }

  static Rule seeds() {
    return new Rule() {
      public boolean B(int neighbours) {
        return neighbours == 2;
      }

      public boolean S(int neighbours) {
        return false;
      }
    };
  }
}
