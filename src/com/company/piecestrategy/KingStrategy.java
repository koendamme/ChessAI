package com.company.piecestrategy;

import com.company.models.Board;
import com.company.models.Move;
import com.company.models.Square;

import java.util.ArrayList;

public class KingStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        int[] offsets = { -8, 1, 8, -1, -7, 9, 7, -9 };

        for (int offset : offsets) {
            if (board.squareInBounds(pieceIndex + offset)) {
                Square targetSquare = board.getSquares()[pieceIndex + offset];

                if (targetSquare.getPiece() == null || targetSquare.getPiece().getColor() != board.getSquares()[pieceIndex].getPiece().getColor()) {
                    moves.add(new Move(board.getSquares()[pieceIndex], targetSquare, board.getSquares()[pieceIndex].getPiece()));
                }

            }
        }

        return moves;
    }
}
