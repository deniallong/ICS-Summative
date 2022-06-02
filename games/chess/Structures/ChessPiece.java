package games.chess.Structures;

import java.io.IOException;

import utils.*;

public abstract class ChessPiece 
{
    private int player;

    public ChessPiece(int player) 
    {
        this.player = player;
    }

    // Getters
    public int getPlayer() 
    {
        return this.player;
    }

    // Placeholder functions
    public abstract Pair[] getMoves(ChessPiece[][] board, int row, int col);
    public abstract void display(int row, int col) throws IOException;
}
