package com.company.strategy;

import com.company.Move;
import com.company.Square;

import java.util.ArrayList;

public interface PieceStrategy {
    ArrayList<Move> generate(int pieceIndex, Square[] board);
}
