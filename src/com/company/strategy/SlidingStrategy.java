package com.company.strategy;

import com.company.Move;
import com.company.PieceColor;
import com.company.Square;

import java.util.ArrayList;

public class SlidingStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Square[] board) {
        ArrayList<Move> generatedMoves = new ArrayList<>();
        int[] directionOffsets = { -8, 1, 8, -1, -7, 9, 7, -9 };

        for (int direction = 0; direction < 8; direction++) {
            for (int i = 0; i < board[pieceIndex].getNumSquaresToEdge()[direction]; i++) {
                int index = pieceIndex + directionOffsets[direction] * (i + 1);

                if (index < 0) {
                    System.out.println("err");
                }
                Square targetSquare =  board[pieceIndex + directionOffsets[direction] * (i + 1)];

                if (targetSquare.getPiece() != null && targetSquare.getPiece().getColor() == PieceColor.WHITE) {
                    // Friendly piece
                    break;
                }

                generatedMoves.add(new Move(board[pieceIndex], targetSquare));

                if (targetSquare.getPiece() != null && targetSquare.getPiece().getColor() == PieceColor.BLACK) {
                    // Can't move further after capturing enemy piece
                    break;
                }
            }
        }

        return generatedMoves;
    }
}
