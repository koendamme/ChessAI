package com.company;

import javax.swing.JFrame;

import com.company.gamestate.GameStateContext;
import com.company.models.Board;

public class GameManager {
    private Board board;
    private MoveGenerator generator;
    private BoardDisplayer displayer;

    public GameManager() {
        this.board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        this.generator = new MoveGenerator();
        this.displayer = new BoardDisplayer(this.board, 800);
        new GameStateContext(this.board, displayer, generator);
        this.createWindow(this.displayer);
    }

    private void createWindow(BoardDisplayer displayer) {
        JFrame frame = new JFrame("My Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(displayer);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
