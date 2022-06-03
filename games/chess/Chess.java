package games.chess;

import hsa.Console;

import java.io.IOException;

public class Chess 
{

    private static Board board;

    static Console c;
    
    public static void run() throws IOException
    {
        c = new Console();

        int turn;
        String[] move;
        String from;
        String to;

        turn = 0;

        board = new Board();
        board.display();

        while (true)
        {
            move = c.readLine().split(" ");

            
        }
    }
}
