// PROGRAM DESCRIPTION
// This program is a collection of two popular games: Chess, and Connect 4, where you may
// play both games by selecting them through a menu. 
// In Chess, you must strategically move your pieces in order to bring a checkmate to your opponent.
// In Connect 4, you must drop pieces into columns in order to link 4 up pieces horizontally, vertically, or diagonally.

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
            c.println("0. Exit");
            c.println("1. Chess");
            c.println("2. Connect 4");

            choice = c.getChar();

            if (choice == '1') 
            {
                ChessDriver();
            }
            else if (choice == '2')
            {
                ConnectFourDriver();
            }

            c.clear();
        }
        while(choice != '0');

        c.clear();
        c.println("Goodbye!");
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
