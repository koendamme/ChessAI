package com.company.gamestate;

import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.Square;

import java.util.ArrayList;
import java.util.Random;

public class BlackTurnState implements GameState {

    public BlackTurnState(GameStateContext context) {
        // TODO: Move logic
        ArrayList<Move> availableMoves = context.getGenerator().generateMovesForAllPieces(context.getBoard(), PieceColor.BLACK);

        if (availableMoves.size() > 0) {
            Random random = new Random();

            Move randomMove = availableMoves.get(random.nextInt(availableMoves.size()));

            context.getBoard().applyMove(randomMove);

            context.setState(new WhiteTurnState(context));
        }

    }

    @Override
    public void move() {
        // TODO: move logic
    }
}
