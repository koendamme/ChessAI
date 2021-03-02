package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board extends JComponent {
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

    public Square[] getSquares() {
        return this.squares;
    }

    public Square getSquare(int x, int y) {
        return this.squares[y*8 + x];
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
