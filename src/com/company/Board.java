package com.company;

import java.awt.*;

public class Board extends Canvas {
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

    public void print() {
        for (int i = 0; i < this.squares.length; i++) {
            if (i%8 == 0) {
                System.out.print("\n");
            }
            Square currSquare = this.squares[i];
            if (currSquare.getPiece() != null) {
                System.out.print(currSquare.getPiece().toString());
            }
            else {
                System.out.print("_");
            }
        }
        System.out.print("\n");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Square s : squares) {
            Color squareColor = this.getSquareColor(s.getX(), s.getY());
            g.setColor(squareColor);

            int sideLength = 70;
            g.fillRect(s.getX() * sideLength, s.getY() * sideLength, sideLength, sideLength);
            if (s.getPiece() !=null) {
                g.drawImage(s.getPiece().getIcon().getScaledInstance(sideLength,
                        sideLength, Image.SCALE_SMOOTH),
                        s.getX() * sideLength,
                        s.getY() * sideLength,
                        squareColor,
                        null);
            }
        }
    }

    private Square getSquare(int x, int y) {
        Square square = null;

        for (Square s : this.squares) {
            if (s.getX() == x && s.getY() == y) {
                square = s;
            }
        }

        return square;
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

    private Color getSquareColor(int x, int y) {
        Color color;

        if (y % 2 == 0) {
            if (x % 2 == 0)
                color = new Color(238,238,210,255);
            else
                color = new Color(118,150,86,255);
        }
        else {
            if (x % 2 == 0)
                color = new Color(118,150,86,255);
            else
                color = new Color(238,238,210,255);
        }

        return color;
    }
}
