package com.company.gamestate;

import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.PieceType;
import com.company.models.Square;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class WhiteTurnState extends MouseAdapter implements GameState {
    private final GameStateContext context;
    private ArrayList<Move> availableMoves;

    public WhiteTurnState(GameStateContext context) {
        context.getDisplayer().addMouseListener(this);
        this.context = context;
    }

    @Override
    public void move() {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int xPos = e.getX() / (this.context.getDisplayer().getScreenSideLength()/8);
        int yPos = e.getY() / (this.context.getDisplayer().getScreenSideLength()/8);

        Square s = this.context.getBoard().getSquare(xPos, yPos);

        if (s.getPiece() != null && s.getPiece().getColor() == PieceColor.WHITE) {
            this.context.getDisplayer().setSelectedSquare(s);
            this.availableMoves = this.context.getGenerator().generateMovesForPiece(this.context.getBoard(), s.getY()*8 + s.getX());
            this.context.getDisplayer().setAvailableMoves(this.availableMoves);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (this.availableMoves != null) {
            int xPos = e.getX() / (this.context.getDisplayer().getScreenSideLength() / 8);
            int yPos = e.getY() / (this.context.getDisplayer().getScreenSideLength() / 8);

            Square s = this.context.getBoard().getSquare(xPos, yPos);

            for (Move move : this.availableMoves) {
                if (move.isCastleMove() && move.getCastleMove().getKingMove().getEndSquare().equals(s)) {
                    this.context.getBoard().applyMove(move.getCastleMove().getRookMove());
                    this.context.getBoard().applyMove(move.getCastleMove().getKingMove());
                    System.out.println("Hello this is a castle move");
                    this.finishMove();
                } else if (!move.isCastleMove() && move.getEndSquare().equals(s)) {
                    this.context.getBoard().applyMove(move);
                    System.out.println("This is not a castle move");
                    this.finishMove();
                }
            }

            this.context.getDisplayer().setSelectedSquare(null);
            this.context.getDisplayer().emptyAvailableMoves();
        }
    }

    private void finishMove() {
        this.context.getDisplayer().removeMouseListener(this);
        this.context.setState(new BlackTurnState(this.context));
    }
}
