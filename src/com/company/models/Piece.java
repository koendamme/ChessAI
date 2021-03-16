package com.company.models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece {
    private PieceType type;
    private PieceColor color;
    private BufferedImage icon;

    public Piece(PieceType type, PieceColor color) {
        this.type = type;
        this.color = color;
    }

    public Piece(char type) {
        this.color = Character.isUpperCase(type) ? PieceColor.WHITE : PieceColor.BLACK;
        switch (Character.toLowerCase(type)) {
            case 'k' -> this.type = PieceType.KING;
            case 'q' -> this.type = PieceType.QUEEN;
            case 'r' -> this.type = PieceType.ROOK;
            case 'b' -> this.type = PieceType.BISHOP;
            case 'n' -> this.type = PieceType.KNIGHT;
            case 'p' -> this.type = PieceType.PAWN;
        };

        try {
            BufferedImage allPieces = ImageIO.read(new File("./img/chess_pieces.png"));
            this.setIconImage(allPieces);
        } catch (IOException e) {
            System.out.println("Cant load image");
        }
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    private void setIconImage(BufferedImage allPieces) {
        BufferedImage pieceImg;
        int widthPerPiece = allPieces.getWidth()/6;

        if (this.type == PieceType.KING) {
            pieceImg = allPieces.getSubimage(0, 0, widthPerPiece, allPieces.getHeight());
        }
        else if (this.type == PieceType.QUEEN) {
            pieceImg = allPieces.getSubimage(widthPerPiece, 0, widthPerPiece, allPieces.getHeight());
        }
        else if (this.type == PieceType.BISHOP) {
            pieceImg = allPieces.getSubimage(widthPerPiece*2, 0, widthPerPiece, allPieces.getHeight());
        }
        else if (this.type == PieceType.KNIGHT) {
            pieceImg = allPieces.getSubimage(widthPerPiece*3, 0, widthPerPiece, allPieces.getHeight());
        }
        else if (this.type == PieceType.ROOK) {
            pieceImg = allPieces.getSubimage(widthPerPiece*4, 0, widthPerPiece, allPieces.getHeight());
        }
        else {
            pieceImg = allPieces.getSubimage(widthPerPiece*5, 0, widthPerPiece, allPieces.getHeight());
        }

        if (color == PieceColor.WHITE) {
            pieceImg = pieceImg.getSubimage(0, 0, widthPerPiece, allPieces.getHeight()/2);
        }
        else {
            pieceImg = pieceImg.getSubimage(0, allPieces.getHeight()/2, widthPerPiece, allPieces.getHeight()/2);
        }

        this.icon = pieceImg;
    }

    @Override
    public String toString() {
        return switch (this.type) {
            case KING -> "K";
            case QUEEN -> "Q";
            case ROOK -> "R";
            case BISHOP -> "B";
            case KNIGHT -> "N";
            case PAWN -> "P";
        };
    }

    public BufferedImage getIcon() {
        return icon;
    }
}
