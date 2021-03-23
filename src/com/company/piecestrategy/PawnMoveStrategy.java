package com.company.piecestrategy;

import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.Square;

import java.util.ArrayList;

public class PawnMoveStrategy implements PieceStrategy{
    @Override
    public ArrayList<Move> generate(int pieceIndex, Square[] board) {
        ArrayList<Move> generatedMoves = new ArrayList<>();

        int[] directionOffsets = { 8, 16 };

        int startIndex = 0;
        int endIndex = board[pieceIndex].getPiece().hasMoved() ? 0 : 1;

        for (int direction = startIndex; direction <= endIndex; direction++) {
            int offset = board[pieceIndex].getPiece().getColor() == PieceColor.WHITE ? directionOffsets[direction] * -1 : directionOffsets[direction];

            if (pieceIndex + offset >= 0 && pieceIndex + offset < 64) {
                Square targetSquare = board[pieceIndex + offset];

                if (targetSquare.getPiece() != null) {
                    // Can't move onto another piece
                    break;
                }

                generatedMoves.add(new Move(board[pieceIndex], targetSquare, board[pieceIndex].getPiece()));
            }
        }

        return generatedMoves;
    }
}
