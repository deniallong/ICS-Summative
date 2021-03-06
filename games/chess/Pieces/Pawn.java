package games.chess.Pieces;

import games.chess.Structures.*;
import utils.*;

public class Pawn extends ChessPiece 
{
    // Constructor
    public Pawn(int player, int row, int col)
    {
        super(player, "pawn", row, col);
    }

    // Getting the moves for this chesspiece
    public Pair[] getMoves(ChessPiece[][] board)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid
        if (row < board.length - 1 && board[row + 1][col] == null && this.getPlayer() == 0) 
        {
            moves = Utils.add(moves, new Pair(row + 1, col));
        }

        if (row > 0 && board[row - 1][col] == null && this.getPlayer() == 1) 
        {
            moves = Utils.add(moves, new Pair(row - 1, col));
        }

        // Checking whether an enemy piece exists on the direct diagonal or not
        if (row < board.length - 1 && this.getPlayer() == 0) 
        {
            // Left diagonal
            if (col > 0 && board[row + 1][col - 1] != null && board[row + 1][col - 1].getPlayer() != this.getPlayer())
            {
                moves = Utils.add(moves, new Pair(row + 1, col - 1));
            }

            // Right diagonal
            if (col < board[0].length - 1 && board[row + 1][col + 1] != null && board[row + 1][col + 1].getPlayer() != this.getPlayer())
            {
                moves = Utils.add(moves, new Pair(row + 1, col + 1));
            }
        }

        // Checking whether an enemy piece exists on the direct diagonal or not
        if (row > 0 && this.getPlayer() == 1) 
        {
            // Left diagonal
            if (col > 0 && board[row - 1][col - 1] != null && board[row - 1][col - 1].getPlayer() != this.getPlayer())
            {
                moves = Utils.add(moves, new Pair(row - 1, col - 1));
            }

            // Right diagonal
            if (col < board[0].length - 1 && board[row - 1][col + 1] != null && board[row - 1][col + 1].getPlayer() != this.getPlayer())
            {
                moves = Utils.add(moves, new Pair(row - 1, col + 1));
            }
        }
        
        // Checking if the pawn is still on the 2nd or 7th row
        // (Meaning it can move 2 instead of 1)
        if (row == 1)
        {
            if (this.getPlayer() == 0)
            {
                moves = Utils.add(moves, new Pair(row + 2, col));
            }
        }
        else if (row == 6)
        {
            if (this.getPlayer() == 1)
            {
                moves = Utils.add(moves, new Pair(row - 2, col));
            }
        }

        return moves;
    }

    // Getting "danger zones" around the piece where it could attack
    public Pair[] getDangerZones(ChessPiece[][] board)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        if (this.getPlayer() == 0) {
        
            moves = new Pair[]{new Pair(row + 1, col - 1), new Pair(row + 1, col + 1)};
        }
        else
        {
            moves = new Pair[]{new Pair(row - 1, col - 1), new Pair(row - 1, col + 1)};
        }

        return moves;
    }

    // Cloning the current chesspiece
    public ChessPiece clonePiece()
    {
        return new Pawn(this.getPlayer(), row, col);
    }
}
