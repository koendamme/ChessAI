package com.company;

import com.company.models.*;
import com.company.piecestrategy.PieceStrategy;
import com.company.piecestrategy.SlidingStrategy;

import java.util.ArrayList;

public class MoveGenerator {
    public ArrayList<Move> generateMovesForAllPieces(Board board, PieceColor color) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < board.getSquares().length; i++) {
            Square currSquare = board.getSquares()[i];

            if (currSquare.getPiece() != null && currSquare.getPiece().getColor() == color) {
                for (PieceStrategy strategy : currSquare.getPiece().getStrategies()) {
                    moves.addAll((strategy.generate(i, board)));
                }
            }
        }

        return moves;
    }

    public ArrayList<Move> generateMovesForPiece(Board board, int squareIndex) {
        ArrayList<Move> moves = new ArrayList<>();

        for (PieceStrategy strategy : board.getSquares()[squareIndex].getPiece().getStrategies()) {
            moves.addAll(strategy.generate(squareIndex, board));
        }

        return moves;
    }
}
