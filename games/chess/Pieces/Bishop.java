package games.chess.Pieces;

import java.io.File;
import java.io.IOException;

import games.chess.Structures.*;
import utils.*;

public class Bishop extends ChessPiece 
{

    static File pieceImage;
    static ImageDisplayer i;

    static 
    {
        i = new ImageDisplayer();

        pieceImage = new File("resources/chess/images/bishop.png");
    }
    
    public Bishop(int player)
    {
        super(player);
    }

    public Pair[] getMoves(ChessPiece[][] board, int row, int col)
    {
        // Declaring variables
        Pair[] moves;

        // Initializing variables
        moves = new Pair[0];

        // Processing
        // Checking which moves are valid

        return moves;
    }

    public void display(int row, int col) throws IOException
    {
        i.display(pieceImage, 0, 0);
    }
}
