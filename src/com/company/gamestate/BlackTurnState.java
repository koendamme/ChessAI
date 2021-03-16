package com.company.gamestate;

import com.company.models.Board;

public class BlackTurnState implements GameState {
    private final GameStateContext context;

    public BlackTurnState(GameStateContext context) {
        this.context = context;

        // TODO: Move logic
        System.out.println("Black made a move");
        this.context.setState(new WhiteTurnState(this.context));
    }

    @Override
    public void move() {
        // TODO: move logic
    }
}
