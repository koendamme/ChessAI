package com.company.pieces;

import com.company.Move;
import com.company.Square;

import java.util.ArrayList;

public abstract class Piece {
    public abstract ArrayList<Square> generateMoves(ArrayList<Square> currentSquares);
}
