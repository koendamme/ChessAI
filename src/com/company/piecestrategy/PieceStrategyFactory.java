package com.company.piecestrategy;

import com.company.models.PieceType;

import java.util.ArrayList;

public class PieceStrategyFactory {
    public ArrayList<PieceStrategy> getStrategies(PieceType pieceType) {
        ArrayList<PieceStrategy> strategies = new ArrayList<>();
        if (pieceType == PieceType.BISHOP || pieceType == PieceType.ROOK || pieceType == PieceType.QUEEN) {
            strategies.add(new SlidingStrategy());
        } else if (pieceType == PieceType.PAWN) {
            strategies.add(new PawnMoveStrategy());
            strategies.add(new PawnCaptureStrategy());
        }

        return strategies;
    }
}
