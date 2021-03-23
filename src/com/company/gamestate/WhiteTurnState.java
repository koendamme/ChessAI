package com.company.gamestate;

import com.company.models.Move;
import com.company.models.PieceColor;
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
        // TODO: Move logic
//        context.setState(new BlackTurnState(this.context));
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

            this.availableMoves.stream()
                    .filter(m -> m.getEndSquare().equals(s))
                    .findFirst().ifPresent(move -> {
                this.context.getBoard().applyMove(move);
                this.context.getDisplayer().removeMouseListener(this);
                this.context.setState(new BlackTurnState(this.context));
            });

            this.context.getDisplayer().setSelectedSquare(null);
            this.context.getDisplayer().emptyAvailableMoves();
        }
    }
}
