package com.company.piecestrategy;

import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.PieceType;
import com.company.models.Square;

import java.util.ArrayList;

public class SlidingStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Square[] board) {
        ArrayList<Move> generatedMoves = new ArrayList<>();

        int[] directionOffsets = { -8, 1, 8, -1, -7, 9, 7, -9 };
        int startIndex = board[pieceIndex].getPiece().getType() == PieceType.BISHOP ? 4 : 0;
        int endIndex = board[pieceIndex].getPiece().getType() == PieceType.ROOK ? 3 : 7;

        for (int direction = startIndex; direction <= endIndex; direction++) {
            for (int i = 0; i < board[pieceIndex].getNumSquaresToEdge()[direction]; i++) {
                Square targetSquare =  board[pieceIndex + directionOffsets[direction] * (i + 1)];

                if (targetSquare.getPiece() != null && targetSquare.getPiece().getColor() == board[pieceIndex].getPiece().getColor()) {
                    // Friendly piece
                    break;
                }

                generatedMoves.add(new Move(board[pieceIndex], targetSquare, board[pieceIndex].getPiece()));

                if (targetSquare.getPiece() != null && targetSquare.getPiece().getColor() != board[pieceIndex].getPiece().getColor()) {
                    // Can't move further after capturing enemy piece
                    break;
                }
            }
        }

        return generatedMoves;
    }
}
