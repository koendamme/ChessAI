package com.company.piecestrategy;

import com.company.models.Move;
import com.company.models.Square;

import java.util.ArrayList;

public interface PieceStrategy {
    ArrayList<Move> generate(int pieceIndex, Square[] board);
}
