package com.company;

import javax.swing.JFrame;

import com.company.models.Board;
import com.company.models.Move;
import com.company.models.PieceColor;
import com.company.models.Player;
import com.company.models.Square;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends MouseAdapter {
    private Board board;
    private MoveGenerator generator;
    private BoardDisplayer displayer;
    private Player currentTurn, player1, player2;
    ArrayList<Move> availableMoves;
    JFrame frame;

    public GameManager(String boardNotation, Player player1, Player player2) {
        this.board = new Board(boardNotation);
        this.generator = new MoveGenerator();
        this.displayer = new BoardDisplayer(this.board, 800);
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = this.player1;

        this.displayer.addMouseListener(this);
        
        this.createWindow(this.displayer);
    }

    private void createWindow(BoardDisplayer displayer) {
        this.frame = new JFrame("Chess");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().add(displayer);
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        if (this.availableMoves != null) {
                int xPos = e.getX() / (this.displayer.getScreenSideLength() / 8);
                int yPos = e.getY() / (this.displayer.getScreenSideLength() / 8);

                Square s = this.board.getSquare(xPos, yPos);

                for (Move move : this.availableMoves) {
                    if (move.isCastleMove() && move.getCastleMove().getKingMove().getEndSquare().equals(s)) {
                        this.board.applyMove(move.getCastleMove().getRookMove());
                        this.board.applyMove(move.getCastleMove().getKingMove());
                        this.nextTurn();
                    } else if (!move.isCastleMove() && move.getEndSquare().equals(s)) {
                        this.board.applyMove(move);
                        this.nextTurn();
                    }
                }

                this.displayer.setSelectedSquare(null);
                this.displayer.emptyAvailableMoves();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int xPos = e.getX() / (this.displayer.getScreenSideLength()/8);
        int yPos = e.getY() / (this.displayer.getScreenSideLength()/8);

        Square s = this.board.getSquare(xPos, yPos);

        if (s.getPiece() != null && s.getPiece().getColor() == this.currentTurn.getColor()) {
            this.displayer.setSelectedSquare(s);
            this.availableMoves = this.generator.generateMovesForPiece(this.board, s.getY()*8 + s.getX());
            this.displayer.setAvailableMoves(availableMoves);
        }
    }

    private void generateRandomMove() {
        ArrayList<Move> legalMoves = new ArrayList<>();

        for (int i = 0; i < board.getSquares().length; i++) {
            Square currSquare = board.getSquares()[i];

            if (currSquare.getPiece() != null && currSquare.getPiece().getColor() == PieceColor.BLACK) {
                ArrayList<Move> legalMovesForPiece = generator.generateMovesForPiece(board, i);
                legalMoves.addAll(legalMovesForPiece);
            }
        }

        if (legalMoves.size() > 0) {
            Random random = new Random();

            Move randomMove = legalMoves.get(random.nextInt(legalMoves.size()));
            
            if (randomMove.isCastleMove()) {
                this.board.applyMove(randomMove.getCastleMove().getKingMove());
                this.board.applyMove(randomMove.getCastleMove().getRookMove());
            }
            else {
                this.board.applyMove(randomMove);
            }

        }
        this.nextTurn();
    }

    private void nextTurn() {
        this.currentTurn = this.currentTurn == this.player1 ? this.player2 : this.player1;

        if (!this.currentTurn.isHumanPlayer()) {
            this.generateRandomMove();
        }
    }
}
