package games.chess.Pieces;

import games.chess.Structures.*;
import utils.*;

public class King extends ChessPiece {

    private boolean moved;

    public King(int player)
    {
        super(player);
    }

    public Pair[] getMoves(int[][] openSpaces, int row, int col)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid

        return moves;
    }

    public boolean hasMoved()
    {
        return moved;
    }

    public void setMoved()
    {
        this.moved = true;
    }
}
