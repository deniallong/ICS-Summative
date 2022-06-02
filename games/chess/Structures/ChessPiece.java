package games.chess.Structures;

import java.io.IOException;
import java.io.File;

import utils.*;

public abstract class ChessPiece 
{
    static ImageDisplayer i;

    private int player;
    private File pieceImage;

    public int row;
    public int col;

    static 
    {
        i = new ImageDisplayer();
    }

    public ChessPiece(int player, String name, int row, int col) 
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

        this.row = row;
        this.col = col;
    }

    // Getters
    public int getPlayer() 
    {
        return this.player;
    }

    // Placeholder functions
    public abstract Pair[] getMoves(ChessPiece[][] board);
   
    public void display() throws IOException
    {
        i.display(pieceImage, col * 59, (7 - row) * 59);
    }
}
