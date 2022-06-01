package games.chess;

import java.io.IOException;

public class Chess 
{

    public static Board board;
    
    public static void run() throws IOException
    {
        Board.initialize();

        board = new Board();
        board.display();
    }
}
