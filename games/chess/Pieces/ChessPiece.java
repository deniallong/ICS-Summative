package games.chess.Pieces;

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
    public Pair[] getMoves;
}
