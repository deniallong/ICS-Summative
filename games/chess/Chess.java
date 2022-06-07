package games.chess;

import hsa.Console;

import games.chess.Structures.*;
import utils.*;

import java.io.IOException;

public class Chess 
{

    private static Board boardObject;

    static Console c;
    
    public static void run() throws IOException
    {
        c = new Console();

        int turn;
        String[] move;
        String fromLetter;
        Pair fromCoords;
        Pair toCoords;
        ChessPiece fromPiece;
        ChessPiece temp;
        boolean turnProgressed;

        turn = 0;

        boardObject = new Board();
        boardObject.display();

        while (true)
        {
            boolean[][] poo = getCheckedSpaces(boardObject.board);

            for (int i = 7; i >= 0; i--) 
            {
                for (int v = 0; v < 8; v++)
                {
                    if (poo[i][v])
                    {
                        System.out.print("T");
                    } else {
                        System.out.print("F");
                    }
                }

                System.out.println();
            }

            turnProgressed = false;
            move = c.readLine().split(" ");

            fromCoords = new Pair(Integer.parseInt(move[0].substring(1, 2)) - 1, Integer.parseInt(move[0].substring(0, 1)) - 1);
            toCoords = new Pair(Integer.parseInt(move[1].substring(1, 2)) - 1, Integer.parseInt(move[1].substring(0, 1)) - 1);

            if (verifyCoordinates(fromCoords) && verifyCoordinates(toCoords))
            {
                if (boardObject.board[fromCoords.a][fromCoords.b] != null)
                {
                    fromPiece = boardObject.board[fromCoords.a][fromCoords.b];

                    if (fromPiece.getPlayer() == turn) 
                    {
                        if (boardObject.isValidMove(fromCoords, toCoords, turn))
                        {
                            boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                            boardObject.board[fromCoords.a][fromCoords.b] = null;

                            fromPiece.row = toCoords.a;
                            fromPiece.col = toCoords.b;
                            turnProgressed = true;
                        }
                        else 
                        {
                            c.println("That piece cannot move there!");
                        }
                    } 
                    else 
                    {
                        c.println("That piece belongs to the opponent!");
                    }
                }
                else 
                {
                    c.println("A piece does not exist there!");
                }
            }   
            else 
            {
                c.println("Invalid move! One of the coordinates given was outside the board!");
            }

            if (turnProgressed)
            {
                boardObject.display();
                
                if (turn == 0)
                {
                    turn = 1;
                } 
                else 
                {
                    turn = 0;
                }
            }

            poo = getCheckedSpaces(boardObject.board);

            for (int i = 7; i >= 0; i--) 
            {
                for (int v = 0; v < 8; v++)
                {
                    if (poo[i][v])
                    {
                        System.out.print("T");
                    } else {
                        System.out.print("F");
                    }
                }

                System.out.println();
            }
        }
    }

    public static boolean verifyCoordinates(Pair pair)
    {
        if (pair.a >= 0 && pair.a < 8 && pair.b >= 0 && pair.b < 8)
        {
            return true;
        }

        return false;
    }

    public static boolean[][] getCheckedSpaces(ChessPiece[][] board)
    {
        boolean[][] ans;

        ans = new boolean[8][8];

        for (int i = 0; i < 8; i++) 
        {
            for (int v = 0; v < 8; v++)
            {
                if (board[i][v] != null)
                {
                    Pair[] moves; 

                    moves = board[i][v].getDangerZones(board);

                    for (int p = 0; p < moves.length; p++) 
                    {
                        
                        if (moves[p].a >= 0 && moves[p].b >= 0 && moves[p].a < 8 && moves[p].b < 8)
                        {
                            System.out.println(moves[p]);
                            ans[moves[p].a][moves[p].b] = true;
                        }
                    }
                }
            }
        }

        return ans;
    }
}
