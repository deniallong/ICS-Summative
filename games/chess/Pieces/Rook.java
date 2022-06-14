package games.chess.Pieces;

import games.chess.Structures.*;
import utils.*;

public class Rook extends ChessPiece 
{
    // Constructor
    public Rook(int player, int row, int col)
    {
        super(player, "rook", row, col);
    }

    // Gets the moves for the piece
    public Pair[] getMoves(ChessPiece[][] board)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid
        // Checking top column
        for (int i = row + 1; i < board.length; i++)
        {
            if (board[i][col] == null)
            {
                moves = Utils.add(moves, new Pair(i, col));
            }
            else 
            {
                if (board[i][col].getPlayer() != this.getPlayer())
                {
                    moves = Utils.add(moves, new Pair(i, col));
                }

                i = board.length;
            }
        }

        // Checking bottom column
        for (int i = row - 1; i >= 0; i--)
        {
            if (board[i][col] == null)
            {
                moves = Utils.add(moves, new Pair(i, col));
            }
            else 
            {
                if (board[i][col].getPlayer() != this.getPlayer())
                {
                    moves = Utils.add(moves, new Pair(i, col));
                }

                i = -1;
            }
        }

        // Checking right row
        for (int i = col + 1; i < board[row].length; i++)
        {
            if (board[row][i] == null)
            {
                moves = Utils.add(moves, new Pair(row, i));
            }
            else 
            {
                if (board[row][i].getPlayer() != this.getPlayer())
                {
                    moves = Utils.add(moves, new Pair(row, i));
                }

                i = board[row].length;
            }
        }

        // Checking left row
        for (int i = col - 1; i >= 0; i--)
        {
            if (board[row][i] == null)
            {
                moves = Utils.add(moves, new Pair(row, i));
            }
            else 
            {
                if (board[row][i].getPlayer() != this.getPlayer())
                {
                    moves = Utils.add(moves, new Pair(row, i));
                }

                i = -1;
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
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid
        // Checking top column
        for (int i = row + 1; i < board.length; i++)
        {
            moves = Utils.add(moves, new Pair(i, col));

            if (board[i][col] != null)
            {
                i = board.length;
            }
        }

        // Checking bottom column
        for (int i = row - 1; i >= 0; i--)
        {
            moves = Utils.add(moves, new Pair(i, col));

            if (board[i][col] != null)
            {
                i = -1;
            }
        }

        // Checking right row
        for (int i = col + 1; i < board[row].length; i++)
        {
            moves = Utils.add(moves, new Pair(row, i));

            if (board[row][i] != null)
            {
                i = board[row].length;
            }
        }

        // Checking left row
        for (int i = col - 1; i >= 0; i--)
        {
            moves = Utils.add(moves, new Pair(row, i));

            if (board[row][i] != null)
            {
                i = -1;
            }
        }

        return moves;
    }

    // Clones the current chesspiece
    public ChessPiece clonePiece()
    {
        return new Rook(this.getPlayer(), row, col);
    }
}
