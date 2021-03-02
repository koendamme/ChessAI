package com.company;

import com.company.strategy.PieceStrategy;
import com.company.strategy.SlidingStrategy;

import java.util.ArrayList;

public class MoveGenerator {
    public ArrayList<Move> generateMoves(Square[] squares, PieceColor color) {
        ArrayList<Move> moves = new ArrayList<>();
        PieceStrategy strategy;

        for (int i = 0; i < squares.length; i++) {
            Square currSquare = squares[i];

            if (currSquare.getPiece() != null && currSquare.getPiece().getColor() == color) {

                if (currSquare.getPiece().getType() == PieceType.QUEEN) {
                    strategy = new SlidingStrategy();
                    ArrayList<Move> rookMoves = strategy.generate(i, squares);
                    moves.addAll(rookMoves);
                }
            }
        }

        return moves;
    }
}
