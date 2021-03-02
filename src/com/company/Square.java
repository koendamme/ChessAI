package com.company;

public class Square {
    private int x, y;
    private Piece piece;
    private int[] numSquaresToEdge;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.numSquaresToEdge = calcNumSquaresToEdge();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int[] getNumSquaresToEdge() {
        return numSquaresToEdge;
    }

    public void setNumSquaresToEdge(int[] numSquaresToEdge) {
        this.numSquaresToEdge = numSquaresToEdge;
    }

    private int[] calcNumSquaresToEdge() {
        final int WEST = this.x;
        final int EAST = 7 - this.x;
        final int NORTH = this.y;
        final int SOUTH = 7 - this.y;

        return new int[] {
                NORTH,
                EAST,
                SOUTH,
                WEST,
                Math.min(NORTH, EAST),
                Math.min(EAST, SOUTH),
                Math.min(SOUTH, WEST),
                Math.min(WEST, NORTH)
        };
    }
}
