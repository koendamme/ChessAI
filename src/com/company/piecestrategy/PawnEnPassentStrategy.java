package com.company.piecestrategy;

import java.util.ArrayList;

import com.company.models.Board;
import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.PieceType;
import com.company.models.Square;

public class PawnEnPassentStrategy implements PieceStrategy {

    @Override
    public ArrayList<Move> generate(int pieceIndex, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        Square square = board.getSquares()[pieceIndex];
        int[] opponentPawnOffsets = {1, -1};
        int[] opponentCaptureOffsets = {9, 7};

        if ((square.getPiece().getColor() == PieceColor.WHITE && square.getY() == 3) || (square.getPiece().getColor() == PieceColor.BLACK && square.getY() == 4)) {
            for (int i = 0; i < 2; i++) {
                int opponentPawnOffset = square.getPiece().getColor() == PieceColor.WHITE ? opponentPawnOffsets[i] * -1 : opponentPawnOffsets[i];
                int opponentCaptureOffset = square.getPiece().getColor() == PieceColor.WHITE ? opponentCaptureOffsets[i] * -1 : opponentCaptureOffsets[i];

                Square opponentSquare = board.getSquares()[pieceIndex + opponentPawnOffset];

                // Can we en passant? 
                if (opponentSquare.getPiece() != null && opponentSquare.getPiece().getType() == PieceType.PAWN && opponentSquare.getPiece().getColor() != square.getPiece().getColor()) {
                    // Yes
                    moves.add(new Move(square, board.getSquares()[pieceIndex + opponentCaptureOffset], board.getSquares()[pieceIndex].getPiece(), opponentSquare));
                }
            }
        }

        return moves;
    }

    @Override
    public boolean canCapture() {
        return true;
    }
    
}
