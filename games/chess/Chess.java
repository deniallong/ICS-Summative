package games.chess;

import java.io.IOException;

public class Chess 
{

    public static Board board;
    
    public static void run() throws IOException
    {
        board = new Board();
        board.display();
    }
}
