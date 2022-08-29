package com.company;

import com.company.models.*;
import com.company.piecestrategy.PieceStrategy;

import java.util.ArrayList;

public class MoveGenerator {
    public ArrayList<Move> generateMovesForAllPieces(Board board, PieceColor color) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < board.getSquares().length; i++) {
            Piece currPiece = board.getSquares()[i].getPiece();

            if (currPiece != null && currPiece.getColor() == color) {
                for (PieceStrategy strategy : currPiece.getStrategies()) {
                    moves.addAll((strategy.generate(i, board)));
                }

                // Castling
                if (currPiece.getType() == PieceType.KING && !currPiece.hasMoved()) {
                    CastleMove cm = this.generateCastleMove(board, i);
                    if (cm.getKingMove() != null) {
                        moves.add(new Move(cm));
                    }
                }
            }
        }

        return moves;
    }

    public ArrayList<Move> generateMovesForPiece(Board board, int squareIndex) {
        ArrayList<Move> moves = new ArrayList<>();
        Piece piece = board.getSquares()[squareIndex].getPiece();

        for (PieceStrategy strategy : piece.getStrategies()) {
            moves.addAll(strategy.generate(squareIndex, board));
        }

        // Castling
        if (piece.getType() == PieceType.KING && !piece.hasMoved()) {
            CastleMove cm = this.generateCastleMove(board, squareIndex);
            if (cm.getKingMove() != null) {
                moves.add(new Move(cm));
            }
        }

        return moves;
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

    private boolean squaresAreFree(Board board, int[] squares) {
        boolean free = true;
        
        for (int i : squares) {
            free = board.getSquares()[i] == null;
        }

        return free;
    }
}
