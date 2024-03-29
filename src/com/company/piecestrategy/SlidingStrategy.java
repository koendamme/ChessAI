package com.company.piecestrategy;

import com.company.models.*;

import java.util.ArrayList;

public class SlidingStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Board board) {
        ArrayList<Move> generatedMoves = new ArrayList<>();

        int[] directionOffsets = { -8, 1, 8, -1, -7, 9, 7, -9 };
        int startIndex = board.getSquares()[pieceIndex].getPiece().getType() == PieceType.BISHOP ? 4 : 0;
        int endIndex = board.getSquares()[pieceIndex].getPiece().getType() == PieceType.ROOK ? 3 : 7;

        for (int direction = startIndex; direction <= endIndex; direction++) {
            for (int i = 0; i < board.getSquares()[pieceIndex].getNumSquaresToEdge()[direction]; i++) {
                Square targetSquare =  board.getSquares()[pieceIndex + directionOffsets[direction] * (i + 1)];

                if (targetSquare.getPiece() != null && targetSquare.getPiece().getColor() == board.getSquares()[pieceIndex].getPiece().getColor()) {
                    // Friendly piece
                    break;
                }

                generatedMoves.add(new Move(board.getSquares()[pieceIndex], targetSquare, board.getSquares()[pieceIndex].getPiece()));

                if (targetSquare.getPiece() != null && targetSquare.getPiece().getColor() != board.getSquares()[pieceIndex].getPiece().getColor()) {
                    // Can't move further after capturing enemy piece
                    break;
                }
            }
        }

        return generatedMoves;
    }

    @Override
    public boolean canCapture() {
        return true;
    }
}
