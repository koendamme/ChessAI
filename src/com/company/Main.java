package com.company;

import com.company.models.PieceColor;
import com.company.models.Player;

public class Main {
    public static void main(String[] args) {
        // String boardNotation = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        String boardNotation = "8/8/8/8/3N4/8/8/8";
        Player player1 = new Player(PieceColor.WHITE, true);
        Player player2 = new Player(PieceColor.BLACK, false);
        
        new GameManager(boardNotation, player1, player2);
        
    }


}
