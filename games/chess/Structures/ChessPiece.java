package games.chess.Structures;

import java.io.IOException;
import java.io.File;

import utils.*;

public abstract class ChessPiece 
{
    // Declaring variables
    static ImageDisplayer i;

    private int player;
    private File pieceImage;

    public String name;
    public int row;
    public int col;
    public boolean moved;

    static 
    {
        // Initializing variables
        i = new ImageDisplayer();
    }

    // Constructor
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
        this.name = name;
    }

    // Getters
    public int getPlayer() 
    {
        return this.player;
    }

    // Placeholder functions
    public abstract Pair[] getMoves(ChessPiece[][] board);
    public abstract Pair[] getDangerZones(ChessPiece[][]board);
    public abstract ChessPiece clonePiece();
   
    // Displays the current chesspiece
    public void display() throws IOException
    {
        i.display(pieceImage, 62 + col * 59, (7 - row) * 59);
    }
}
