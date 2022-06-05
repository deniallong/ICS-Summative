import hsa.Console;

import java.io.IOException;

import utils.*;

import games.chess.Chess;
import games.connectfour.ConnectFour;

public class Main
{
    static Console c;
    static ImageDisplayer displayer;
    
    public static void main(String[] args) throws IOException
    {
        // Main code
        //ChessDriver();
        ConnectFourDriver();
    }

    public static void ChessDriver() throws IOException 
    {
        Chess.run();
    }

    public static void ConnectFourDriver() throws IOException 
    {
        ConnectFour.run();
    }
}
