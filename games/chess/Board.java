package games.chess;

import java.io.File;
import java.io.IOException;

import utils.ImageDisplayer;
import hsa.Console;

public class Board 
{

    static File boardImage;
    static ImageDisplayer i;

    private int rows;
    private int cols;

    public Board() 
    {

        this.rows = 8;
        this.cols = 8;
 
    }

    public static void initialize() 
    {
        i = new ImageDisplayer();

        boardImage = new File("resources/chess/images/chessboard.png");
    }

    public void display() throws IOException
    {

        i.display(boardImage, 0, 0);
    }
}