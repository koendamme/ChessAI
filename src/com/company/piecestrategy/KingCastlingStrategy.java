package com.company.piecestrategy;

import java.util.ArrayList;

import com.company.models.Board;
import com.company.models.CastleMove;
import com.company.models.Move;
import com.company.models.PieceType;
import com.company.models.Square;

public class KingCastlingStrategy implements PieceStrategy {

    @Override
    public ArrayList<Move> generate(int pieceIndex, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        
        int[] squaresToEdge = board.getSquares()[pieceIndex].getNumSquaresToEdge();
        
        Square eastEdgeSquare = board.getSquares()[pieceIndex + squaresToEdge[1]];
        Square westEdgeSquare = board.getSquares()[pieceIndex - squaresToEdge[3]];
        
        // East castling
        if (eastEdgeSquare.getPiece() != null && 
            eastEdgeSquare.getPiece().getType() == PieceType.ROOK && 
            !eastEdgeSquare.getPiece().hasMoved() && 
            board.getSquares()[pieceIndex + 1].getPiece() == null &&
            board.getSquares()[pieceIndex + 2].getPiece() == null) {
                Move kingMove = new Move(board.getSquares()[pieceIndex], board.getSquares()[pieceIndex+2], board.getSquares()[pieceIndex].getPiece());
                Move rookMove = new Move(eastEdgeSquare, board.getSquares()[pieceIndex+1], eastEdgeSquare.getPiece());
                
                moves.add(new Move(new CastleMove(kingMove, rookMove)));
        }

        // West castling
        if (westEdgeSquare.getPiece() != null && 
            westEdgeSquare.getPiece().getType() == PieceType.ROOK && 
            !westEdgeSquare.getPiece().hasMoved() &&
            board.getSquares()[pieceIndex -1].getPiece() == null &&
            board.getSquares()[pieceIndex -2].getPiece() == null &&
            board.getSquares()[pieceIndex -3].getPiece() == null) {
                Move kingMove = new Move(board.getSquares()[pieceIndex], board.getSquares()[pieceIndex-2], board.getSquares()[pieceIndex].getPiece());
                Move rookMove = new Move(westEdgeSquare, board.getSquares()[pieceIndex-1], westEdgeSquare.getPiece());
                moves.add(new Move(new CastleMove(kingMove, rookMove)));
        }

        return moves;
    }

    @Override
    public boolean canCapture() {
        return false;
    }
    
}
