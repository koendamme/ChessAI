package com.company.models;

public class CastleMove {
    private Move kingMove;
    private Move rookMove;

    public CastleMove(Move kingMove, Move rookMove) {
        this.kingMove = kingMove;
        this.rookMove = rookMove;
    }

    public Move getRookMove() {
        return rookMove;
    }

    public void setRookMove(Move rookMove) {
        this.rookMove = rookMove;
    }

    public Move getKingMove() {
        return kingMove;
    }

    public void setKingMove(Move kingMove) {
        this.kingMove = kingMove;
    }
}
