package com.company;

import com.company.gamestate.GameStateContext;
import com.company.models.Board;

import javax.swing.*;

public class GameManager {
    private Board board;
    private BoardDisplayer displayer;
    private MoveGenerator generator;
    private GameStateContext gameStateContext;

    public GameManager() {
        this.board = new Board("rnbqkbnr/pppppppp/8/8/8/8/8/RNBQKBNR");
//        this.board = new Board("8/8/8/3Q4/8/8/pppppppp/8");

        int screenSideLength = 800;
        this.displayer = new BoardDisplayer(this.board, screenSideLength);
        this.generator = new MoveGenerator();
        this.gameStateContext = new GameStateContext(this.board, this.displayer, this.generator);
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
