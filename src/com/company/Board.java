package com.company;

import com.company.pieces.Piece;

import java.util.ArrayList;

public class Board {
    private final ArrayList<Square> squares;
    private ArrayList<PieceSet> pieceSets;


    public Board(String notation) {
        this.squares = this.initBoard();
        this.pieceSets = new ArrayList<>();
        PieceSet black = new PieceSet(PieceColor.BLACK);
        PieceSet white = new PieceSet(PieceColor.WHITE);
        pieceSets.add(black);
        pieceSets.add(white);

        PieceFactory factory = new PieceFactory();

        String[] rows = notation.split("/");

        for (int row = 0; row < 8; row++) {
            int column = 0;
            for (char c : rows[row].toCharArray()) {
                if (Character.isDigit(c)) {
                    column += Character.getNumericValue(c);
                }
                else {
                    Piece p = factory.getPiece(c);
                    this.getSquare(column, row).setPiece(p);

                    if (Character.isUpperCase(c)) {
                        white.addPiece(p);
                    }
                    else {
                        black.addPiece(p);
                    }
                    column++;
                }
            }
        }
    }

    public ArrayList<Square> getSquares() {
        return this.squares;
    }

    public PieceSet getPieceSet(PieceColor color) {
        for (PieceSet set : pieceSets) {
            if (set.getColor() == color) {
                return set;
            }
        }

        return null;
    }

    private Square getSquare(int x, int y) {
        for (Square s : this.squares) {
            if (s.getX() == x && s.getY() == y) {
                return s;
            }
        }

        throw new IllegalArgumentException();
    }

    ArrayList<Square> initBoard() {
        ArrayList<Square> squares = new ArrayList<>();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                squares.add(new Square(x, y));
            }
        }

        return squares;
    }
}
