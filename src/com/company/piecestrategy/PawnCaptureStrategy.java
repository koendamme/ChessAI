package com.company.piecestrategy;

import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.Square;

import java.util.ArrayList;

public class PawnCaptureStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Square[] board) {
        ArrayList<Move> generatedMoves = new ArrayList<>();

        int[] directionOffsets = { 9, 7 };

        for (int directionOffset : directionOffsets) {
            int offset = board[pieceIndex].getPiece().getColor() == PieceColor.WHITE ? directionOffset * -1 : directionOffset;

            if (pieceIndex + offset >= 0 &&
                    pieceIndex + offset < 64 && board[pieceIndex + offset].getPiece() != null &&
                    board[pieceIndex + offset].getPiece().getColor() != board[pieceIndex].getPiece().getColor()) {

                generatedMoves.add(new Move(board[pieceIndex], board[pieceIndex + offset], board[pieceIndex].getPiece()));

            }
        }

        return generatedMoves;
    }
}
