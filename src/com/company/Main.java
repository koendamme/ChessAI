package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Board board = new Board("8/8/8/3Q4/8/8/8/8");

        int screenSideLength = 800;
        BoardDisplayer displayer = new BoardDisplayer(board, screenSideLength);

        MoveGenerator generator = new MoveGenerator();

        displayer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Click!");
                Square[] selected = {board.getSquare(e.getX() / (screenSideLength/8), e.getY() / (screenSideLength/8))};
                int xPos = e.getX() / (screenSideLength/8);
                int yPos = e.getY() / (screenSideLength/8);

                if (selected[0].getPiece() != null) {
                    displayer.setSelectedSquare(selected[0]);

                    ArrayList<Move> moves = generator.generateMovesForPiece(board.getSquares(), yPos*8 + xPos);
                    displayer.setAvailableMoves(moves);
                }
                else {
                    displayer.setSelectedSquare(null);
                    displayer.emptyAvailableMoves();
                }
            }
        });
//        ArrayList<Move> moves = generator.generateMoves(board.getSquares(), PieceColor.WHITE);
//        displayer.setAvailableMoves(moves);

        createWindow(displayer);
    }


    private static void createWindow(BoardDisplayer displayer) {
        JFrame frame = new JFrame("My Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(displayer);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
