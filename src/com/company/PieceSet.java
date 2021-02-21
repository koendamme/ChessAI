package com.company;

import com.company.pieces.Piece;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PieceSet {
    private ArrayList<Piece> pieces;
    private PieceColor color;

    public PieceSet(PieceColor color) {
        this.pieces = new ArrayList<>();
        this.color = color;
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public ArrayList<Piece> getPieces() {
        return this.pieces;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }
}
