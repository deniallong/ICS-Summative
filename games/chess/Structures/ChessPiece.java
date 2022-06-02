package games.chess.Structures;

import java.io.IOException;
import java.io.File;

import utils.*;

public abstract class ChessPiece 
{
    static ImageDisplayer i;

    private int player;
    private File pieceImage;

    static 
    {
        i = new ImageDisplayer();
    }

    public ChessPiece(int player, String name) 
    {
        this.player = player;

        if (player == 0)
        {
            pieceImage = new File("resources/chess/images/" + name + "white.png");
        }
        else 
        {
            pieceImage = new File("resources/chess/images/" + name + "black.png");
        }
    }

    // Getters
    public int getPlayer() 
    {
        return this.player;
    }

    // Placeholder functions
    public abstract Pair[] getMoves(ChessPiece[][] board, int row, int col);
   
    public void display(int row, int col) throws IOException
    {
        i.display(pieceImage, col * 59, (7 - row) * 59);
    }
}
