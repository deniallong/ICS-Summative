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
        int outcome;
        String[] move;
        boolean[][] checkedSquares;
        String fromLetter;
        Pair fromCoords;
        Pair toCoords;
        ChessPiece blackKing; 
        ChessPiece whiteKing;
        ChessPiece kingOfInterest;
        ChessPiece fromPiece;
        ChessPiece temp;
        boolean turnProgressed;
        boolean gameContinue;

        gameContinue = true;

        turn = 0;

        boardObject = new Board();
        boardObject.display();

        whiteKing = boardObject.board[0][4];
        blackKing = boardObject.board[7][4];

        while (gameContinue)
        {

            outcome = getGameOutcome(boardObject.board, whiteKing, blackKing, turn);

            if (outcome != 0)
            {
                gameContinue = false;

                if (outcome == -2)
                {
                    c.println("Checkmate! Black loses!");
                }
                else if (outcome == -1 || outcome == 1) 
                {
                    c.println("Stalemate!");
                }
                else if (outcome == 2)
                {
                    c.println("Checkmate! White loses!");
                }
            }
            else 
            {
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
                            
                            if (turn == 0) 
                            {
                                kingOfInterest = whiteKing;
                            }
                            else 
                            {
                                kingOfInterest = blackKing;
                            }

                            // Checking if a king is in check
                            if (checkedSquares[kingOfInterest.row][kingOfInterest.col])
                            {
                                if (avoidCheckValid(boardObject.board, fromCoords, toCoords, kingOfInterest))
                                {
                                    fromPiece.moved = true;
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
                                        // Castling
                                        if (!kingOfInterest.moved && kingOfInterest.row == 0 && kingOfInterest.col == 4 && ((toCoords.a == 0 && toCoords.b == 0) || (toCoords.a == 0 && toCoords.b == 7)))
                                        {
                                            if (turn == 0)
                                            {
                                                // Left castle
                                                if (toCoords.a == 0 && toCoords.b == 0)
                                                {
                                                    if (boardObject.board[0][3] == null && boardObject.board[0][2] == null && boardObject.board[0][1] == null && (boardObject.board[0][0] == null || boardObject.board[0][0].name == "rook"))
                                                    {
                                                        temp = boardObject.board[toCoords.a][toCoords.b];

                                                        fromPiece.moved = true;
                                                        temp.moved = true;

                                                        boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                                        boardObject.board[fromCoords.a][fromCoords.b] = temp;

                                                        fromPiece.row = toCoords.a;
                                                        fromPiece.col = toCoords.b;
                                                        temp.row = fromCoords.a;
                                                        temp.col = fromCoords.b;

                                                        turnProgressed = true;
                                                    }
                                                }
                                                // Right Castle
                                                else if (toCoords.a == 0 && toCoords.b == 7)
                                                {
                                                    if (boardObject.board[0][5] == null && boardObject.board[0][6] == null && (boardObject.board[0][7] == null || boardObject.board[0][7].name == "rook"))
                                                    {
                                                        temp = boardObject.board[toCoords.a][toCoords.b];

                                                        fromPiece.moved = true;
                                                        temp.moved = true;

                                                        boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                                        boardObject.board[fromCoords.a][fromCoords.b] = temp;

                                                        fromPiece.row = toCoords.a;
                                                        fromPiece.col = toCoords.b;
                                                        temp.row = fromCoords.a;
                                                        temp.col = fromCoords.b;

                                                        turnProgressed = true;
                                                    }
                                                } 
                                                else 
                                                {
                                                    c.println("That piece cannot move there!");
                                                }
                                            }
                                            else 
                                            {
                                                // Left castle
                                                if (toCoords.a == 7 && toCoords.b == 0)
                                                {
                                                    if (boardObject.board[7][3] == null && boardObject.board[7][2] == null && boardObject.board[7][1] == null && (boardObject.board[7][0] == null || boardObject.board[7][0].name == "rook"))
                                                    {
                                                        temp = boardObject.board[toCoords.a][toCoords.b];

                                                        fromPiece.moved = true;
                                                        temp.moved = true;

                                                        boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                                        boardObject.board[fromCoords.a][fromCoords.b] = temp;

                                                        fromPiece.row = toCoords.a;
                                                        fromPiece.col = toCoords.b;
                                                        temp.row = fromCoords.a;
                                                        temp.col = fromCoords.b;

                                                        turnProgressed = true;
                                                    }
                                                }
                                                // Right Castle
                                                else if (toCoords.a == 7 && toCoords.b == 7)
                                                {
                                                    if (boardObject.board[7][5] == null && boardObject.board[7][6] == null && (boardObject.board[7][7] == null || boardObject.board[7][7].name == "rook"))
                                                    {
                                                        temp = boardObject.board[toCoords.a][toCoords.b];

                                                        fromPiece.moved = true;
                                                        temp.moved = true;

                                                        boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                                        boardObject.board[fromCoords.a][fromCoords.b] = temp;

                                                        fromPiece.row = toCoords.a;
                                                        fromPiece.col = toCoords.b;
                                                        temp.row = fromCoords.a;
                                                        temp.col = fromCoords.b;

                                                        turnProgressed = true;
                                                    }
                                                } 
                                                else 
                                                {
                                                    c.println("That piece cannot move there!");
                                                }
                                            }
                                        }
                                        else 
                                        {
                                            fromPiece.moved = true;
                                            boardObject.board[toCoords.a][toCoords.b] = fromPiece;
                                            boardObject.board[fromCoords.a][fromCoords.b] = null;
    
                                            fromPiece.row = toCoords.a;
                                            fromPiece.col = toCoords.b;
                                            turnProgressed = true;
                                        } 
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
                                        fromPiece.moved = true;
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
    }

    public static boolean checkRowClear(ChessPiece[][] board, int row, int start, int end)
    {
        for (int i = start; i < end; i++)
        {
            if (board[row][i] != null)
            {
                return false; 
            }
        }

        return true;
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


    public static int getGameOutcome(ChessPiece[][] board, ChessPiece whiteKing, ChessPiece blackKing, int turn)
    {
        int validWhiteMoves; 
        int validBlackMoves; 
        boolean whiteCheckmate;
        boolean blackCheckmate;
        ChessPiece kingOfInterest;

        validWhiteMoves = 0;
        validBlackMoves = 0;
        whiteCheckmate = getCheckedSpaces(board, 0)[whiteKing.row][whiteKing.col];
        blackCheckmate = getCheckedSpaces(board, 1)[blackKing.row][blackKing.col];

        for (int i = 0; i < 8; i++) 
        {
            for (int v = 0; v < 8; v++)
            {
                if (board[i][v] != null)
                {
                    Pair[] moves; 
                    
                    if (board[i][v].getPlayer() == 0)
                    {
                        kingOfInterest = whiteKing;
                    }
                    else 
                    {
                        kingOfInterest = blackKing;
                    }

                    moves = board[i][v].getMoves(board);

                    for (int p = 0; p < moves.length; p++) 
                    {

                        if (avoidCheckValid(board, new Pair(board[i][v].row, board[i][v].col), new Pair(moves[p].a, moves[p].b), kingOfInterest))
                        {
                            if (board[i][v].getPlayer() == 0)
                            {
                                validWhiteMoves ++;
                                whiteCheckmate = false;
                            }
                            else 
                            {
                                validBlackMoves ++;
                                blackCheckmate = false;
                            }
                        }
                    }
                }
            }
        }

        if (whiteCheckmate)
        {
            return 2;
        }
        else if (blackCheckmate)
        {
            return -2;
        }
        else if (turn == 0 && validWhiteMoves == 0)
        {
            return 1;
        }
        else if (turn == 1 && validBlackMoves == 0)
        {
            return -1;
        }

        return 0;
    }
}
