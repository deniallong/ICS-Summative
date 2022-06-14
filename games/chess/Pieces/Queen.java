package games.chess.Pieces;

import games.chess.Structures.*;
import utils.*;

public class Queen extends ChessPiece 
{
    // Constructor
    public Queen(int player, int row, int col)
    {
        super(player, "queen", row, col);
    }

    // Gets the moves for this piece
    public Pair[] getMoves(ChessPiece[][] board)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

       // Processing
        // Checking which moves are valid
        // Checking top left diagonal
        for (int i = 1; i <= Math.min(7 - row, col); i++) 
        {
            if (board[row + i][col - i] == null || board[row + i][col - i].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row + i, col - i));
            } 
            else
            {
                if (board[row + i][col - i].getPlayer() != this.getPlayer())  
                {
                    moves = Utils.add(moves, new Pair(row + i, col - i));
                }
                
                i = Math.min(7 - row, col) + 1;
            } 
        }

        // Checking top right diagonal
        for (int i = 1; i <= Math.min(7 - row, 7 - col); i++) 
        {
            if (board[row + i][col + i] == null || board[row + i][col + i].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row + i, col + i));
            } 
            else
            {
                if (board[row + i][col + i].getPlayer() != this.getPlayer())  
                {
                    moves = Utils.add(moves, new Pair(row + i, col + i));
                }
                
                i = Math.min(7 - row, 7 - col) + 1;
            } 
        }

        // Checking bottom left diagonal
        for (int i = 1; i <= Math.min(row, col); i++) 
        {
            if (board[row - i][col - i] == null || board[row - i][col - i].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row - i, col - i));
            } 
            else
            {
                if (board[row - i][col - i].getPlayer() != this.getPlayer())  
                {
                    moves = Utils.add(moves, new Pair(row - i, col - i));
                }
                
                i = Math.min(row, col) + 1;
            } 
        }

        // Checking bottom right diagonal
        for (int i = 1; i <= Math.min(row, 7 - col); i++) 
        {
            if (board[row - i][col + i] == null || board[row - i][col + i].getPlayer() != this.getPlayer()) 
            {
                moves = Utils.add(moves, new Pair(row - i, col + i));
            } 
            else
            {
                if (board[row - i][col + i].getPlayer() != this.getPlayer())  
                {
                    moves = Utils.add(moves, new Pair(row - i, col + i));
                }
                
                i = Math.min(row, 7 - col) + 1;
            } 
        }

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

        // Checking top left diagonal
        for (int i = 1; i <= Math.min(7 - row, col); i++) 
        {
            moves = Utils.add(moves, new Pair(row + i, col - i));

            if (board[row + i][col - i] != null) 
            {
                i = Math.min(7 - row, col);
            } 
        }

        // Checking top right diagonal
        for (int i = 1; i <= Math.min(7 - row, 7 - col); i++) 
        {
            moves = Utils.add(moves, new Pair(row + i, col + i));

            if (board[row + i][col + i] != null) 
            {
                i = Math.min(7 - row, 7 - col);
            } 
        }

        // Checking bottom left diagonal
        for (int i = 1; i <= Math.min(row, col); i++) 
        {
            moves = Utils.add(moves, new Pair(row - i, col - i));

            if (board[row - i][col - i] != null) 
            {
                i = Math.min(row, col);
            } 
        }

        // Checking bottom right diagonal
        for (int i = 1; i <= Math.min(row, 7 - col); i++) 
        {
            moves = Utils.add(moves, new Pair(row - i, col + i));

            if (board[row - i][col + i] != null) 
            {                
                i = Math.min(row, 7 - col);
            } 
        }

        return moves;
    }

    // Cloning the current chesspiece
    public ChessPiece clonePiece()
    {
        return new Queen(this.getPlayer(), row, col);
    }
}
