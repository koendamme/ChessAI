package com.company;

import com.company.models.*;
import com.company.piecestrategy.PieceStrategy;

import java.util.ArrayList;

public class MoveGenerator {
    // public ArrayList<Move> generateMovesForAllPieces(Board board, PieceColor color) {
    //     ArrayList<Move> moves = new ArrayList<>();

    //     for (int i = 0; i < board.getSquares().length; i++) {
    //         Piece currPiece = board.getSquares()[i].getPiece();

    //         if (currPiece != null && currPiece.getColor() == color) {
    //             for (PieceStrategy strategy : currPiece.getStrategies()) {
    //                 moves.addAll((strategy.generate(i, board)));
    //             }

    //             // Castling
    //             if (currPiece.getType() == PieceType.KING && !currPiece.hasMoved()) {
    //                 CastleMove cm = this.generateCastleMove(board, i);
    //                 if (cm.getKingMove() != null) {
    //                     moves.add(new Move(cm));
    //                 }
    //             }
    //         }
    //     }

    //     return moves;
    // }

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

        // Castling
        if (piece.getType() == PieceType.KING && !piece.hasMoved()) {
            CastleMove cm = this.generateCastleMove(board, squareIndex);
            if (cm.getKingMove() != null && this.moveIsLegal(cm.getKingMove(), board) && this.moveIsLegal(cm.getRookMove(), board)) {
                legalMoves.add(new Move(cm));
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
        board.tempMove(move);
        ArrayList<Square> squaresUnderAttack = this.findSquaresUnderAttack(board, move.getPiece().getOpponentColor());
        boolean check = false;
        for (Square underAttack : squaresUnderAttack) {
            if (underAttack.getPiece() != null && underAttack.getPiece().getType() == PieceType.KING) {
                check = true;
            }
        }
        board.cancelMove(move);

        return !check;
    }

    private CastleMove generateCastleMove(Board board, int kingIndex) {
        CastleMove castleMove = new CastleMove();
        
        int[] squaresToEdge = board.getSquares()[kingIndex].getNumSquaresToEdge();
        
        Square eastEdgeSquare = board.getSquares()[kingIndex + squaresToEdge[1]];
        Square westEdgeSquare = board.getSquares()[kingIndex - squaresToEdge[3]];
        
        // East castling
        if (eastEdgeSquare.getPiece() != null && 
            eastEdgeSquare.getPiece().getType() == PieceType.ROOK && 
            !eastEdgeSquare.getPiece().hasMoved() && 
            board.getSquares()[kingIndex + 1].getPiece() == null &&
            board.getSquares()[kingIndex + 2].getPiece() == null) {
                Move kingMove = new Move(board.getSquares()[kingIndex], board.getSquares()[kingIndex+2], board.getSquares()[kingIndex].getPiece());
                Move rookMove = new Move(eastEdgeSquare, board.getSquares()[kingIndex+1], eastEdgeSquare.getPiece());
                castleMove.setKingMove(kingMove);
                castleMove.setRookMove(rookMove);
        }

        // West castling
        if (westEdgeSquare.getPiece() != null && 
            westEdgeSquare.getPiece().getType() == PieceType.ROOK && 
            !westEdgeSquare.getPiece().hasMoved() &&
            board.getSquares()[kingIndex -1].getPiece() == null &&
            board.getSquares()[kingIndex -2].getPiece() == null &&
            board.getSquares()[kingIndex -3].getPiece() == null) {
                Move kingMove = new Move(board.getSquares()[kingIndex], board.getSquares()[kingIndex-2], board.getSquares()[kingIndex].getPiece());
                Move rookMove = new Move(westEdgeSquare, board.getSquares()[kingIndex-1], westEdgeSquare.getPiece());
                castleMove.setKingMove(kingMove);
                castleMove.setRookMove(rookMove);
        }


        return castleMove;
    } 
}
