package com.company;

import com.company.models.PieceColor;
import com.company.models.Player;

public class Main {
    public static void main(String[] args) {
        // String boardNotation = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        String boardNotation = "r3k2r/pppppppp/8/8/8/8/PPPPPPPP/R3K2R";
        Player player1 = new Player(PieceColor.WHITE, true);
        Player player2 = new Player(PieceColor.BLACK, true);
        
        new GameManager(boardNotation, player1, player2);
        
    }


}
