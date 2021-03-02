package com.company.strategy;

import com.company.Move;
import com.company.Square;

import java.util.ArrayList;

public class RookStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Square[] board) {
        //offset { -1, 1, -8, 8}

        ArrayList<Move> generatedMoves = new ArrayList<>();

        // WEST
        if (board[pieceIndex - 1].getPiece() == null) {
            Move move = new Move(board[pieceIndex], board[pieceIndex - 1]);
            generatedMoves.add(move);
        }


        return generatedMoves;
    }
}
