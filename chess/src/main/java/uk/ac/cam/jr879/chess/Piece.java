/*
 * Copyright 2021 Ben Philps <bp413@cam.ac.uk>, Joseph Rance
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

package uk.ac.cam.jr879.chess;

import static uk.ac.cam.jr879.chess.PieceColor.BLACK;
import static uk.ac.cam.jr879.chess.PieceColor.WHITE;
import static uk.ac.cam.jr879.chess.Position.Rank.SEVEN;
import static uk.ac.cam.jr879.chess.Position.Rank.TWO;

import java.util.ArrayList;
import java.util.List;

class King extends Piece {

  public static int KING_VALUE = 10000;

  public King(Position piecePosition, PieceColor pieceColor, Board board) {
    super(piecePosition, pieceColor, board);
  }

  @Override
  public List<Position> validNextPositions() {
    List<Position> nextPositions = new ArrayList<>();
    position.getAllDiagonalMoves(1, board(), nextPositions);
    position.getAllStraightMoves(1, board(), nextPositions);
    return nextPositions;
  }

  @Override
  public char icon() {
    boolean colourSwitch = pieceColor == PieceColor.BLACK;
    return colourSwitch ? '♚' : '♔';
  }

  @Override
  public int value() {
    return KING_VALUE;
    // the king's value must be some high value, but it must be less than
    //  integer max value - queen value (such that we can compute the effectiveness of a move
    // that takes a queen and also puts the king in check at the same time).
  }

  public char name() {
    return 'K';
  }
}

class Pawn extends Piece {
  public Pawn(Position piecePosition, PieceColor pieceColor, Board board) {
    super(piecePosition, pieceColor, board);
  }

  @Override
  public List<Position> validNextPositions() {
    List<Position> nextPositions = new ArrayList<>();
    computePawnNextPositions(nextPositions);
    return nextPositions;
  }

  @Override
  public char icon() {
    boolean colourSwitch = pieceColor == PieceColor.BLACK;
    return colourSwitch ? '♟' : '♙';
  }

  @Override
  public int value() {
    return 1;
  }

  public char name() {
    return 'P';
  }

  private void computePawnNextPositions(List<Position> nextPositions) {
    // The En passant move is not included.
    // The Promotion is not included.

    /*
    pawns can move up 1 if it is a non occupied square.
    pawns can move (and take) up 1 and left or right 1 if the square is occupied by an opponent
    pawns can move up 2 if they are currently on their home row (pawns cannot go backwards)

     */

    int upDir = (pieceColor == WHITE ? 1 : -1);

    // move up by one
    addPawnPositionIfValid(upDir, 0, false, nextPositions);

    // move up left and right
    addPawnPositionIfValid(upDir, -1, true, nextPositions);
    addPawnPositionIfValid(upDir, 1, true, nextPositions);

    // move up two if on their home row
    if (position().getRank() == (colour() == BLACK ? SEVEN : TWO)) {
      addPawnPositionIfValid(upDir + upDir, 0, false, nextPositions);
    }
  }

  private void addPawnPositionIfValid(
          int deltaRank,
          int deltaFile,
          boolean allowIfOccupiedByOpponent,
          List<Position> nextPositions) {

    Position movePosition = position().getPosAtDelta(deltaRank, deltaFile);

    if (movePosition != null) {
      boolean movePosOccupied = board().positionOccupied(movePosition);
      // the up left and right cases
      if (allowIfOccupiedByOpponent
              && movePosOccupied
              && board().atPosition(movePosition).colour() != colour()) {
        nextPositions.add(movePosition);
      }
      // the "up straight ahead" and "up straight ahead two" moves.
      else if (!allowIfOccupiedByOpponent && !movePosOccupied) {
        nextPositions.add(movePosition);
      }
    }
  }
}

class Bishop extends Piece {
  public Bishop(Position piecePosition, PieceColor pieceColor, Board board) {
    super(piecePosition, pieceColor, board);
  }

  @Override
  public List<Position> validNextPositions() {
    List<Position> nextPositions = new ArrayList<>();
    position.getAllDiagonalMoves(8, board(), nextPositions);
    return nextPositions;
  }

  @Override
  public char icon() {
    boolean colourSwitch = pieceColor == PieceColor.BLACK;
    return colourSwitch ? '♝' : '♗';
  }

  @Override
  public int value() {
    return 3;
  }

  public char name() {
    return 'B';
  }
}

class Knight extends Piece {
  public Knight(Position piecePosition, PieceColor pieceColor, Board board) {
    super(piecePosition, pieceColor, board);
  }

  @Override
  public List<Position> validNextPositions() {
    List<Position> nextPositions = new ArrayList<>();
    computeKnightNextPositions(nextPositions);
    return nextPositions;
  }

  @Override
  public char icon() {
    boolean colourSwitch = pieceColor == PieceColor.BLACK;
    return colourSwitch ? '♞' : '♘';
  }

  @Override
  public int value() {
    return 3;
  }

  public char name() {
    return 'N';
  }

  private void computeKnightNextPositions(List<Position> nextPositions) {
    // directions a knight can travel in.
    final int[][] nextPosDeltas =
            new int[][] {
                    {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
                    {2, 1}, {-2, 1}, {2, -1}, {-2, -1}
            };

    // iterate through all possible positions, getting any valid next positions
    for (int[] posDeltaPair : nextPosDeltas) {
      position.addPosAtDelta(posDeltaPair[0], posDeltaPair[1], board(), nextPositions);
    }
  }
}

class Rook extends Piece {
  public Rook(Position piecePosition, PieceColor pieceColor, Board board) {
    super(piecePosition, pieceColor, board);
  }

  @Override
  public List<Position> validNextPositions() {
    List<Position> nextPositions = new ArrayList<>();
    position.getAllStraightMoves(8, board(), nextPositions);
    return nextPositions;
  }

  @Override
  public char icon() {
    boolean colourSwitch = pieceColor == PieceColor.BLACK;
    return colourSwitch ? '♜' : '♖';
  }

  @Override
  public int value() {
    return 5;
  }

  public char name() {
    return 'R';
  }
}

class Queen extends Piece {
  public Queen(Position piecePosition, PieceColor pieceColor, Board board) {
    super(piecePosition, pieceColor, board);
  }

  @Override
  public List<Position> validNextPositions() {
    List<Position> nextPositions = new ArrayList<>();
    position.getAllDiagonalMoves(8, board(), nextPositions);
    position.getAllStraightMoves(8, board(), nextPositions);
    return nextPositions;
  }

  @Override
  public char icon() {
    boolean colourSwitch = pieceColor == PieceColor.BLACK;
    return colourSwitch ? '♛' : '♕';
  }

  @Override
  public int value() {
    return 10;
  }

  public char name() {
    return 'Q';
  }
}

public abstract class Piece {

  protected Position position;
  protected final PieceColor pieceColor;
  private final Board board;

  public Piece(Position piecePosition, PieceColor pieceColor, Board board) {
    this.position = piecePosition;
    this.pieceColor = pieceColor;
    this.board = board;
  }

  /* naming convention:
     K -> king
     P -> pawn
     B -> bishop
     N -> knight
     R -> rook
     Q -> queen
  */

  abstract List<Position> validNextPositions();

  abstract char icon();

  abstract int value();

  abstract char name();

  public Position position() {
    return position;
  }

  public void moveTo(Position newPosition) {
    position = newPosition;
  }

  public PieceColor colour() {
    return pieceColor;
  }

  public String toString() {
    return name() + " " + position.toString();
  }

  public Board board() {
    return board;
  }
}
