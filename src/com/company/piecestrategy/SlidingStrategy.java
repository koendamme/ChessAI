package com.company.piecestrategy;

import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.Square;

import java.util.ArrayList;

public class SlidingStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Square[] board) {
        ArrayList<Move> generatedMoves = new ArrayList<>();
        int[] directionOffsets = { -8, 1, 8, -1, -7, 9, 7, -9 };

        for (int direction = 0; direction < 8; direction++) {
            for (int i = 0; i < board[pieceIndex].getNumSquaresToEdge()[direction]; i++) {
                Square targetSquare =  board[pieceIndex + directionOffsets[direction] * (i + 1)];

                if (targetSquare.getPiece() != null && targetSquare.getPiece().getColor() == PieceColor.WHITE) {
                    // Friendly piece
                    break;
                }

                generatedMoves.add(new Move(board[pieceIndex], targetSquare, board[pieceIndex].getPiece()));

                if (targetSquare.getPiece() != null && targetSquare.getPiece().getColor() == PieceColor.BLACK) {
                    // Can't move further after capturing enemy piece
                    break;
                }
            }
        }

        return generatedMoves;
    }
}
