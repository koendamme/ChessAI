package com.company.models;

public class Player {
    private PieceColor color;
    private boolean isHumanPlayer;

    public Player(PieceColor color, boolean isHumanPlayer) {
        this.color = color;
        this.isHumanPlayer = isHumanPlayer;
    }
    public PieceColor getColor() {
        return color;
    }
    public void setWhite(PieceColor color) {
        this.color = color;
    }
    public boolean isHumanPlayer() {
        return isHumanPlayer;
    }
    public void setHumanPlayer(boolean isHumanPlayer) {
        this.isHumanPlayer = isHumanPlayer;
    }
}
