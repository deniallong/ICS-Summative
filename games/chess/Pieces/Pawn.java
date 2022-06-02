package games.chess.Pieces;

import games.chess.Structures.*;
import utils.*;

public class Pawn extends ChessPiece {
    
    public Pawn(int player)
    {
        super(player);
    }

    public Pair[] getMoves(int[][] openSpaces, int row, int col)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[1];

        // Processing
        // Checking which moves are valid
        if (row < openSpaces.length - 1 && openSpaces[row + 1][col] == 0) {
            moves[0] = new Pair(row + 1, col);
        }

        return moves;
    }
}
