package com.company.models;

public class Board {
    private final Square[] squares;

    public Board(String notation) {
        this.squares = this.initBoard();

        String[] rows = notation.split("/");

        for (int row = 0; row < 8; row++) {
            int column = 0;
            for (char c : rows[row].toCharArray()) {
                if (Character.isDigit(c)) {
                    column += Character.getNumericValue(c);
                }
                else {
                    Piece p = new Piece(c);
                    this.getSquare(column, row).setPiece(p);

                    column++;
                }
            }
        }
    }

    public void applyMove(Move move) {
        move.getPiece().setHasMoved(true);
        move.getEndSquare().setPiece(move.getPiece());
        move.getStartSquare().setPiece(null);
    }

    public Square[] getSquares() {
        return this.squares;
    }

    public Square getSquare(int x, int y) {
        return this.squares[y*8 + x];
    }

    public boolean squareInBounds(int squareIndex) {
        return squareIndex >= 0 && squareIndex < 64;
    }

    private Square[] initBoard() {
        Square[] squares = new Square[64];

        int i = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                squares[i] = new Square(x, y);
                i++;
            }
        }

        return squares;
    }
}
