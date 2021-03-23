package com.company.piecestrategy;

import com.company.models.Board;
import com.company.models.Move;
import com.company.models.PieceColor;

import java.util.ArrayList;

public class PawnCaptureStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Board board) {
        ArrayList<Move> generatedMoves = new ArrayList<>();

        int[] directionOffsets = { 9, 7 };

        for (int directionOffset : directionOffsets) {
            int offset = board.getSquares()[pieceIndex].getPiece().getColor() == PieceColor.WHITE ? directionOffset * -1 : directionOffset;

            if (board.squareInBounds(pieceIndex + offset) &&
                    board.getSquares()[pieceIndex + offset].getPiece() != null &&
                    board.getSquares()[pieceIndex + offset].getPiece().getColor() != board.getSquares()[pieceIndex].getPiece().getColor()) {

                generatedMoves.add(new Move(board.getSquares()[pieceIndex], board.getSquares()[pieceIndex + offset], board.getSquares()[pieceIndex].getPiece()));

            }
        }

        return generatedMoves;
    }
}
