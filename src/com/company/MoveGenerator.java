package com.company;

import com.company.strategy.PieceStrategy;
import com.company.strategy.SlidingStrategy;

import java.util.ArrayList;

public class MoveGenerator {
    public ArrayList<Move> generateMovesForAllPieces(Square[] board, PieceColor color) {
        ArrayList<Move> moves = new ArrayList<>();
        PieceStrategy strategy;

        for (int i = 0; i < board.length; i++) {
            Square currSquare = board[i];

            if (currSquare.getPiece() != null && currSquare.getPiece().getColor() == color) {

                if (currSquare.getPiece().getType() == PieceType.QUEEN) {
                    strategy = new SlidingStrategy();
                    ArrayList<Move> rookMoves = strategy.generate(i, board);
                    moves.addAll(rookMoves);
                }
            }
        }

        return moves;
    }

    public ArrayList<Move> generateMovesForPiece(Square[] board, int squareIndex) {
        ArrayList<Move> moves = new ArrayList<>();
        PieceStrategy strategy;

        if (board[squareIndex].getPiece() != null && board[squareIndex].getPiece().getType() == PieceType.QUEEN) {
            strategy = new SlidingStrategy();
            ArrayList<Move> rookMoves = strategy.generate(squareIndex, board);
            moves.addAll(rookMoves);
        }

        return moves;
    }
}
