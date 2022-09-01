package com.company;

import com.company.models.PieceColor;
import com.company.models.Player;

public class Main {
    public static void main(String[] args) {
        String boardNotation = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Player player1 = new Player(PieceColor.WHITE, true);
        Player player2 = new Player(PieceColor.BLACK, true);
        
        GameManager manager = new GameManager(boardNotation, player1, player2);

        new InputController(manager);
        
    }


}
