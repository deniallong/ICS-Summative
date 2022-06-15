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
        c = new Console(); 
        
        char choice; 

        do 
        {

            c.println("Choose a game:");
            c.println("1. Chess");
            c.println("2. Connect 4");

            choice = c.getChar();

            if (choice == '1') 
            {
                ChessDriver();
            }
            else 
            {
                ConnectFourDriver();
            }

            c.clear();
        }
        while(choice != '0');
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
