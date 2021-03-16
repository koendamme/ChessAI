package com.company.models;

import java.util.Objects;

public class Square {
    private final int x, y;
    private Piece piece;
    private final int[] numSquaresToEdge;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.numSquaresToEdge = calcNumSquaresToEdge();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return x == square.x && y == square.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
