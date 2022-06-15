package games.chess.Pieces;

import games.chess.Structures.*;
import utils.*;

public class Knight extends ChessPiece 
{
    // Constructor
    public Knight(int player, int row, int col)
    {
        super(player, "knight", row, col);
    }

    // Getting the moves for the current chesspiece
    public Pair[] getMoves(ChessPiece[][] board)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid
        if (row < 6)
        {
            if (col > 0 && (board[row + 2][col - 1] == null || board[row + 2][col - 1].getPlayer() != this.getPlayer()))
            {
                moves = Utils.add(moves, new Pair(row + 2, col - 1));
            }
            if (col < 7 && (board[row + 2][col + 1] == null || board[row + 2][col + 1].getPlayer() != this.getPlayer()))
            {
                moves = Utils.add(moves, new Pair(row + 2, col + 1));
            }
        }
        if (row < 7) 
        {
            if (col > 1 && (board[row + 1][col - 2] == null || board[row + 1][col - 2].getPlayer() != this.getPlayer()))
            {
                moves = Utils.add(moves, new Pair(row + 1, col - 2));
            }
            if (col < 6 && (board[row + 1][col + 2] == null || board[row + 1][col + 2].getPlayer() != this.getPlayer()))
            {
                moves = Utils.add(moves, new Pair(row + 1, col + 2));
            }
        }
        if (row > 0) 
        {
            if (col > 1 && (board[row - 1][col - 2] == null || board[row - 1][col - 2].getPlayer() != this.getPlayer()))
            {
                moves = Utils.add(moves, new Pair(row - 1, col - 2));
            }
            if (col < 6 && (board[row - 1][col + 2] == null || board[row - 1][col + 2].getPlayer() != this.getPlayer()))
            {
                moves = Utils.add(moves, new Pair(row - 1, col + 2));
            }
        }
        if (row > 1)
        {
            if (col > 0 && (board[row - 2][col - 1] == null || board[row - 2][col - 1].getPlayer() != this.getPlayer()))
            {
                moves = Utils.add(moves, new Pair(row - 2, col - 1));
            }
            if (col < 7 && (board[row - 2][col + 1] == null || board[row - 2][col + 1].getPlayer() != this.getPlayer()))
            {
                moves = Utils.add(moves, new Pair(row - 2, col + 1));
            }
        }

        return moves;
    }

    // Getting "danger zones" around the piece where it could attack
    public Pair[] getDangerZones(ChessPiece[][] board)
    {
        return this.getMoves(board);
    }

    // Cloning the current chesspiece
    public ChessPiece clonePiece()
    {
        return new Knight(this.getPlayer(), row, col);
    }
}
