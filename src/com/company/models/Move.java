package com.company.models;

public class Move {
    private Square startSquare, endSquare, enPassentCaptureSquare;
    
    private Piece piece;
    private CastleMove castleMove;
    
    public Move(Square startSquare, Square endSquare, Piece piece) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.piece = piece;
    }
    
    public Move(CastleMove castleMove) {
        this.castleMove = castleMove;
    }

    public Move(Square startSquare, Square endSquare, Piece piece, Square enPassantCaptureSquare) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.piece = piece;
        this.enPassentCaptureSquare = enPassantCaptureSquare;
    }
    
    public Square getEnPassentCaptureSquare() {
        return enPassentCaptureSquare;
    }

    public void setEnPassentCaptureSquare(Square enPassentCaptureSquare) {
        this.enPassentCaptureSquare = enPassentCaptureSquare;
    }
    
    public boolean isEnPassantMove() {
        return this.enPassentCaptureSquare != null;
    }

    public boolean isCastleMove() {
        return this.castleMove != null;
    }

    public CastleMove getCastleMove() {
        return this.castleMove;
    }

    public Square getStartSquare() {
        return startSquare;
    }

    public void setStartSquare(Square startSquare) {
        this.startSquare = startSquare;
    }

    public Square getEndSquare() {
        return endSquare;
    }

    public void setEndSquare(Square endSquare) {
        this.endSquare = endSquare;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
