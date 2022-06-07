package games.chess.Pieces;

import games.chess.Structures.*;
import utils.*;

public class Bishop extends ChessPiece 
{
    
    public Bishop(int player, int row, int col)
    {
        super(player, "bishop", row, col);
    }

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

    public ChessPiece clonePiece()
    {
        return new Bishop(this.getPlayer(), row, col);
    }
}
