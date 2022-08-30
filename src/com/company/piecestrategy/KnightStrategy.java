package com.company.piecestrategy;

import com.company.models.Board;
import com.company.models.Move;
import java.util.ArrayList;

public class KnightStrategy implements PieceStrategy {
    @Override
    public ArrayList<Move> generate(int pieceIndex, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        int[][] offsets = {{-17, -15}, {-6, 10}, {15, 17}, {-10, 6}};
        int[] squaresToEdge = board.getSquares()[pieceIndex].getNumSquaresToEdge();

        for (int i = 0; i < 4; i++) {
            if (squaresToEdge[i] >= 2) {
                for (int offset : offsets[i]) {
                    int oldX = board.getSquares()[pieceIndex].getX();
                    int newX = board.getSquares()[pieceIndex + offset].getX();
                    
                    if (board.squareInBounds(pieceIndex + offset) &&
                            (board.getSquares()[pieceIndex + offset].getPiece() == null ||
                            board.getSquares()[pieceIndex + offset].getPiece().getColor() != board.getSquares()[pieceIndex].getPiece().getColor())
                            && Math.abs(oldX - newX) <= 2) {
                        moves.add(new Move(board.getSquares()[pieceIndex], board.getSquares()[pieceIndex + offset], board.getSquares()[pieceIndex].getPiece()));
                    }
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
