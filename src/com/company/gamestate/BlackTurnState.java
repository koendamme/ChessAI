package com.company.gamestate;

import com.company.models.Board;
import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.Square;

import java.util.ArrayList;
import java.util.Random;

public class BlackTurnState implements GameState {

    public BlackTurnState(GameStateContext context) {
        // TODO: Move logic
        ArrayList<Move> legalMoves = new ArrayList<>();

        Board board = context.getBoard();

        for (int i = 0; i < board.getSquares().length; i++) {
            Square currSquare = board.getSquares()[i];

            if (currSquare.getPiece() != null && currSquare.getPiece().getColor() == PieceColor.BLACK) {
                ArrayList<Move> legalMovesForPiece = context.getGenerator().generateMovesForPiece(board, i);
                legalMoves.addAll(legalMovesForPiece);
            }
        }


        if (legalMoves.size() > 0) {
            Random random = new Random();

            Move randomMove = legalMoves.get(random.nextInt(legalMoves.size()));
            
            if (randomMove.isCastleMove()) {
                context.getBoard().applyMove(randomMove.getCastleMove().getKingMove());
                context.getBoard().applyMove(randomMove.getCastleMove().getRookMove());
            }
            else {
                context.getBoard().applyMove(randomMove);
            }

            context.setState(new WhiteTurnState(context));
        }

    }

    @Override
    public void move() {
        // TODO: move logic
    }
}
