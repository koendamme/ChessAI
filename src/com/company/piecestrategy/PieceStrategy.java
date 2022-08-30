package com.company.piecestrategy;

import com.company.models.Board;
import com.company.models.Move;

import java.util.ArrayList;

public interface PieceStrategy {
    ArrayList<Move> generate(int pieceIndex, Board board);
    boolean canCapture();
}
