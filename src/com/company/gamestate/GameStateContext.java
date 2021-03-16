package com.company.gamestate;

import com.company.BoardDisplayer;
import com.company.MoveGenerator;
import com.company.models.Board;

public class GameStateContext {
    private GameState state;
    private Board board;
    private final BoardDisplayer displayer;
    private final MoveGenerator generator;

    public GameStateContext(Board board, BoardDisplayer displayer, MoveGenerator generator) {
        // White starts
        this.board = board;
        this.displayer = displayer;
        this.generator = generator;
        this.state = new WhiteTurnState(this);
    }

    public void move() {
        this.state.move();
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameState getCurrentState() {
        return this.state;
    }

    public BoardDisplayer getDisplayer() {
        return displayer;
    }

    public MoveGenerator getGenerator() {
        return generator;
    }
}
