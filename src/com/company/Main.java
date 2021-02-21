package com.company;

import com.company.pieces.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Board board = new Board("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBKR");

        for (int i = 0; i < board.getSquares().size(); i++) {
            if (i%8 == 0) {
                System.out.print("\n");
            }
            Square currSquare = board.getSquares().get(i);
            if (currSquare.getPiece() != null) {
                System.out.print(VisualizePiece(currSquare.getPiece()));
            }
            else {
                System.out.print("_");
            }
        }
    }

    private static String VisualizePiece(Piece p) {
        if (p instanceof King) {
            return "K";
        }
        else if (p instanceof Pawn) {
            return "P";
        }
        else if (p instanceof Rook) {
            return "R";
        }
        else if (p instanceof Knight) {
            return "N";
        }
        else if (p instanceof Queen) {
            return "Q";
        }
        else if (p instanceof Bishop) {
            return "B";
        }
        else {
            return "";
        }
    }
}
