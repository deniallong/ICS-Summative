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
        boolean[][] checkedSquares;
        String fromLetter;
        Pair fromCoords;
        Pair toCoords;
        ChessPiece blackKing; 
        ChessPiece whiteKing;
        ChessPiece fromPiece;
        ChessPiece temp;
        boolean turnProgressed;

        turn = 0;

        boardObject = new Board();
        boardObject.display();

        whiteKing = boardObject.board[0][4];
        blackKing = boardObject.board[7][4];

        while (true)
        {
            boolean[][] poo = getCheckedSpaces(boardObject.board, 0);

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
                            checkedSquares = getCheckedSpaces(boardObject.board, turn);

                            System.out.println(turn);

                            // Checking if a king is in check
                            if (turn == 0 && checkedSquares[whiteKing.row][whiteKing.col])
                            {
                                if (avoidCheckValid(boardObject.board, fromCoords, toCoords, whiteKing))
                                {
                                    boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                    boardObject.board[fromCoords.a][fromCoords.b] = null;

                                    fromPiece.row = toCoords.a;
                                    fromPiece.col = toCoords.b;
                                    turnProgressed = true;
                                }
                                else 
                                {
                                    c.println("That move is not valid, as the king would still be in check!");
                                }
                            }
                            else if (turn == 1 && checkedSquares[blackKing.row][blackKing.col])
                            {
                                if (avoidCheckValid(boardObject.board, fromCoords, toCoords, blackKing))
                                {
                                    boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                    boardObject.board[fromCoords.a][fromCoords.b] = null;

                                    fromPiece.row = toCoords.a;
                                    fromPiece.col = toCoords.b;
                                    turnProgressed = true;
                                }
                                else 
                                {
                                    c.println("That move is not valid, as the king would still be in check!");
                                }
                            }
                            else 
                            {
                                if (fromPiece.name == "king")
                                {
                                    if (!checkedSquares[toCoords.a][toCoords.b])
                                    {
                                        boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                        boardObject.board[fromCoords.a][fromCoords.b] = null;

                                        fromPiece.row = toCoords.a;
                                        fromPiece.col = toCoords.b;
                                        turnProgressed = true;
                                    } 
                                    else 
                                    {
                                        c.println("The king cannot go there, as it will be in check!");
                                    }
                                } 
                                else 
                                {   
                                    if (turn == 0 && avoidCheckValid(boardObject.board, fromCoords, toCoords, whiteKing) || turn == 1 && avoidCheckValid(boardObject.board, fromCoords, toCoords, blackKing))
                                    {
                                        boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                        boardObject.board[fromCoords.a][fromCoords.b] = null;

                                        fromPiece.row = toCoords.a;
                                        fromPiece.col = toCoords.b;
                                        turnProgressed = true;
                                    }
                                    else 
                                    {
                                        c.println("You cannot make that move, as it puts the king in check!");
                                    }
                                }
                            }    
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

            poo = getCheckedSpaces(boardObject.board, 0);

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

            checkedSquares = getCheckedSpaces(boardObject.board, turn);

            if (turnProgressed)
            {
                if (turn == 0)
                {
                    if (checkedSquares[whiteKing.row][whiteKing.col])
                    {
                        c.println("The white king is in check!");
                    }

                    c.println("It is now white's turn!");
                }
                else 
                {
                    if (checkedSquares[blackKing.row][blackKing.col])
                    {
                        c.println("The black king is in check!");
                    }

                    c.println("It is now black's turn!");
                }
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

    public static boolean[][] getCheckedSpaces(ChessPiece[][] board, int player)
    {
        boolean[][] ans;

        ans = new boolean[8][8];

        for (int i = 0; i < 8; i++) 
        {
            for (int v = 0; v < 8; v++)
            {
                if (board[i][v] != null && board[i][v].getPlayer() != player)
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

    public static boolean avoidCheckValid(ChessPiece[][] board, Pair from, Pair to, ChessPiece king)
    {
        ChessPiece[][] boardClone;
        ChessPiece fromPiece;

        boardClone = new ChessPiece[8][8]; 

        // Cloning the board
        for (int i = 0; i < board.length; i++)
        {
            for (int v = 0; v < board[i].length; v++)
            {
                if (board[i][v] != null)
                {
                    boardClone[i][v] = board[i][v].clonePiece();
                }
            }
        }

        if (boardClone[king.row][king.col].name.equals("king")) 
        {
            king = boardClone[king.row][king.col];
        }

        // Simulating the piece moving
        fromPiece = boardClone[from.a][from.b];

        boardClone[to.a][to.b] = fromPiece;
        boardClone[from.a][from.b] = null;

        boardClone[to.a][to.b].row = to.a;
        boardClone[to.a][to.b].col = to.b;

        boolean[][] checkedSquares = getCheckedSpaces(boardClone, board[from.a][from.b].getPlayer());

        return !checkedSquares[king.row][king.col];
    }
}
