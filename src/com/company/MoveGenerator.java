package com.company;

import com.company.models.*;
import com.company.piecestrategy.PieceStrategy;

import java.util.ArrayList;

public class MoveGenerator {
    public ArrayList<Move> generateMovesForPiece(Board board, int squareIndex) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        Piece piece = board.getSquares()[squareIndex].getPiece();

        for (PieceStrategy strategy : piece.getStrategies()) {
            ArrayList<Move> tempMoves = strategy.generate(squareIndex, board);

            for (Move move : tempMoves) {
                if (this.moveIsLegal(move, board)) {
                    legalMoves.add(move);
                }
            }
        }
        
        return legalMoves;
    }

    private ArrayList<Square> findSquaresUnderAttack(Board board, PieceColor color) {
        ArrayList<Square> squares = new ArrayList<>();

        for (int i = 0; i < board.getSquares().length; i++) {
            Square square = board.getSquares()[i];

            if (square.getPiece() != null && square.getPiece().getColor() == color) {
                for (PieceStrategy strategy : square.getPiece().getStrategies()) {
                    if (strategy.canCapture()) {
                        ArrayList<Move> moves = strategy.generate(i, board);
                        for (Move move : moves) {
                            squares.add(move.getEndSquare());
                        }
                    }
                }
            }
        }

        return squares;
    }

    private boolean moveIsLegal(Move move, Board board) {
        if (move.isCastleMove()) {
            board.tempMove(move.getCastleMove().getKingMove());
            board.tempMove(move.getCastleMove().getRookMove());
        } else {
            board.tempMove(move);
        }

        PieceColor color = move.isCastleMove() ? move.getCastleMove().getKingMove().getPiece().getOpponentColor() : move.getPiece().getOpponentColor();

        ArrayList<Square> squaresUnderAttack = this.findSquaresUnderAttack(board, color);
        boolean check = false;
        for (Square underAttack : squaresUnderAttack) {
            if (underAttack.getPiece() != null && underAttack.getPiece().getType() == PieceType.KING) {
                check = true;
            }
        }

        if (move.isCastleMove()) {
            board.cancelMove(move.getCastleMove().getKingMove());
            board.cancelMove(move.getCastleMove().getRookMove());
        } else {
            board.cancelMove(move);
        }

        return !check;
    }
}
