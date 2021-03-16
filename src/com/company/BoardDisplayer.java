package com.company;

import com.company.models.Board;
import com.company.models.Move;
import com.company.models.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BoardDisplayer extends JComponent {
    private final Board board;
    private final int sideLength;
    private Square selectedSquare;
    private ArrayList<Move> availableMoves;

    public BoardDisplayer(Board board, int screenSideLength) {
        this.board = board;
        this.setPreferredSize(new Dimension(screenSideLength, screenSideLength));
        this.sideLength = screenSideLength/8;
        this.availableMoves = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Square s : this.board.getSquares()) {

            // Drawing the board
            Color squareColor = s.equals(this.selectedSquare) ? Color.YELLOW : this.getSquareColor(s.getX(), s.getY());
            g.setColor(squareColor);
            g.fillRect(s.getX() * sideLength, s.getY() * sideLength, sideLength, sideLength);

            // Drawing possible moves
            for (Move m : this.availableMoves) {
                if (m.getEndSquare().equals(s)) {
                    g.setColor(Color.ORANGE);
                    if (s.getPiece() != null && s.getPiece().getColor() != m.getPiece().getColor()) {
                        g.fillRect(s.getX() * sideLength, s.getY() * sideLength, sideLength, sideLength);
                    }
                    else {
                        g.fillOval(s.getX() * sideLength + sideLength / 3,
                                s.getY() * sideLength + sideLength / 3,
                                sideLength / 3,
                                sideLength / 3);
                    }
                }
            }

            // Drawing the pieces
            if (s.getPiece() != null) { // && s.getPiece().getType() == PieceType.QUEEN) {
                g.drawImage(s.getPiece().getIcon().getScaledInstance(sideLength,
                        sideLength, Image.SCALE_SMOOTH),
                        s.getX() * sideLength,
                        s.getY() * sideLength,
                        null);
            }
        }
        repaint();
    }

    private Color getSquareColor(int x, int y) {
        Color color;

        if (y % 2 == 0) {
            if (x % 2 == 0)
                color = new Color(238,238,210,255);
            else
                color = new Color(118,150,86,255);
        }
        else {
            if (x % 2 == 0)
                color = new Color(118,150,86,255);
            else
                color = new Color(238,238,210,255);
        }

        return color;
    }

    public void setAvailableMoves(ArrayList<Move> moves) {
        this.availableMoves = moves;
        repaint();
    }

    public void emptyAvailableMoves() {
        this.availableMoves.clear();
    }

    public void setSelectedSquare(Square s) {
        this.selectedSquare = s;
        repaint();
    }
}
