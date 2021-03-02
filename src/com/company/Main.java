package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Board board = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        MoveGenerator generator = new MoveGenerator();

        ArrayList<Move> moves = generator.generateMoves(board.getSquares(), PieceColor.WHITE);

        System.out.println(moves.size());

        createWindow(board);
    }

    private static void createWindow(Board board) {
        JFrame frame = new JFrame("My Drawing");
        board.setSize(800, 600);
        frame.add(board);
        frame.pack();
        frame.setVisible(true);
    }
}
