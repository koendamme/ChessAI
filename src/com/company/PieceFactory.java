package com.company;

import com.company.pieces.*;

import java.util.Locale;

public class PieceFactory {
    public Piece getPiece(char pieceNotation) {
        return switch (Character.toLowerCase(pieceNotation)) {
            case 'k' -> new King();
            case 'q' -> new Queen();
            case 'r' -> new Rook();
            case 'b' -> new Bishop();
            case 'n' -> new Knight();
            case 'p' -> new Pawn();
            default -> null;
        };
    }
}
