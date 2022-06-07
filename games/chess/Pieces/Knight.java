package games.chess.Pieces;

import games.chess.Chess;
import games.chess.Structures.*;
import utils.*;

public class Knight extends ChessPiece 
{

    public Knight(int player, int row, int col)
    {
        super(player, "knight", row, col);
    }

    public Pair[] getMoves(ChessPiece[][] board)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid
        if (row < board.length - 1 && board[row + 1][col] == null) 
        {
            moves = Utils.add(moves, new Pair(row + 1, col));
        }

        // Checking whether an enemy piece exists on the direct diagonal or not
        if (row < board.length - 1) 
        {
            // Left diagonal
            if (col > 0 && board[row + 1][col - 1] != null && board[row + 1][col - 1].getPlayer() == this.getPlayer())
            {
                moves = Utils.add(moves, new Pair(row + 1, col - 1));
            }

            // Right diagonal
            if (col < board[0].length - 1 && board[row + 1][col + 1] != null && board[row + 1][col + 1].getPlayer() == this.getPlayer())
            {
                moves = Utils.add(moves, new Pair(row + 1, col + 1));
            }
        }

        return moves;
    }

    public Pair[] getDangerZones(ChessPiece[][] board)
    {
        return new Pair[0];
    }
}
