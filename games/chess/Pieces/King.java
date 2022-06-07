package games.chess.Pieces;

import games.chess.Structures.*;
import utils.*;

public class King extends ChessPiece 
{
    
    public King(int player, int row, int col)
    {
        super(player, "king", row, col);
    }

    public Pair[] getMoves(ChessPiece[][] board)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid
        // Checking top column
        if (row < 7) 
        {
            if (board[row + 1][col] == null || board[row + 1][col].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row + 1, col));
            }
        }

        // Checking bottom column
        if (row > 0)
        {
            if (board[row - 1][col] == null || board[row - 1][col].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row - 1, col));
            }
        }

        // Checking left row
        if (col > 0) 
        {
            if (board[row][col - 1] == null || board[row][col - 1].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row, col - 1));
            }
        }

        // Checking right row
        if (col < 7)
        {
            if (board[row][col + 1] == null || board[row][col + 1].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row, col + 1));
            }
        }

        // Checking top right diagonal
        if (col < 7 && row < 7)
        {
            if (board[row + 1][col + 1] == null || board[row + 1][col + 1].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row + 1, col + 1));
            }
        }

        // Checking top left diagonal
        if (col > 0 && row < 7) 
        {
            if (board[row + 1][col - 1] == null || board[row + 1][col - 1].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row + 1, col - 1));
            }
        }

        // Checking bottom right diagonal
        if (col < 7 && row > 0)
        {
            if (board[row - 1][col + 1] == null || board[row - 1][col + 1].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row - 1, col + 1));
            }
        }

        // Checking bottom left diagonal
        if (col > 0 && row > 0)
        {
            if (board[row - 1][col - 1] == null || board[row - 1][col - 1].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row - 1, col - 1));
            }
        }

        return moves;
    }

    public Pair[] getDangerZones(ChessPiece[][] board)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid
        // Checking top column
        moves = Utils.add(moves, new Pair(row + 1, col));
        moves = Utils.add(moves, new Pair(row - 1, col));
        moves = Utils.add(moves, new Pair(row, col - 1));
        moves = Utils.add(moves, new Pair(row, col + 1));
        moves = Utils.add(moves, new Pair(row + 1, col + 1));
        moves = Utils.add(moves, new Pair(row + 1, col - 1));
        moves = Utils.add(moves, new Pair(row - 1, col + 1));
        moves = Utils.add(moves, new Pair(row - 1, col - 1));

        return moves;
    }

    public ChessPiece clonePiece()
    {
        return new King(this.getPlayer(), row, col);
    }
}
