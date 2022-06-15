package games.chess;

import hsa.Console;

import games.chess.Structures.*;
import utils.*;

import java.io.IOException;

public class Chess 
{

    // Declaring variables
    private static Board boardObject;

    static Console c;
    
    public static void run() throws IOException
    {
        c = new Console();

        // Declaring variables
        int turn;
        int outcome;

        String[] move;

        boolean[][] checkedSquares;

        Pair fromCoords;
        Pair toCoords;

        ChessPiece blackKing; 
        ChessPiece whiteKing;
        ChessPiece kingOfInterest;
        ChessPiece fromPiece;
        ChessPiece temp;

        boolean turnProgressed;
        boolean gameContinue;
        boolean exit;

        // Initializing Variables
        gameContinue = true;
        exit = false;

        turn = 0;

        boardObject = new Board();
        boardObject.display();

        whiteKing = boardObject.board[0][4];
        blackKing = boardObject.board[7][4];

        // Displaying instructions
        c.println("INSTRUCTIONS:");

        c.println("Moves should be entered in the following format:");
        c.println("ab cd");
        c.println();
        c.println("'a' denotes the column of the piece you want to move");
        c.println("'b' denotes the row of the piece you want to move");
        c.println("'c' denotes the column of the destination");
        c.println("'d' denotes the row of the destination");
        c.println("Enter 0 if you want to quit the game.");

        c.println();
        c.println("Press any key to continue:");
        c.getChar();
        c.clear();

        // While game is still ongoing
        while (gameContinue && !exit)
        {

            // Getting the outcome of the game
            outcome = getGameOutcome(boardObject.board, whiteKing, blackKing, turn);

            // Checking if the game is over, and then displaying the corresponding message
            if (outcome != 0)
            {

                if (outcome == -2)
                {
                    c.println("Checkmate! Black loses!");
                    Board.displayWin(0);
                }
                else if (outcome == -1 || outcome == 1) 
                {
                    c.println("Stalemate!");
                    Board.displayTie();
                }
                else if (outcome == 2)
                {
                    c.println("Checkmate! White loses!");
                    Board.displayWin(1);
                }

                gameContinue = false;
            }
            else 
            {
                turnProgressed = false;
                c.print("Enter a move: ");
                move = c.readLine().split(" ");

                // Checking if user wants exit
                if (move.length == 1) 
                {
                    if (move[0].equals("0"))
                    {
                        gameContinue = false;
                        exit = true;

                        Board.i.close();
                        c.close();
                    }
                }
                else 
                {

                    // Getting the from coordinates and to coordinates
                    fromCoords = new Pair(Integer.parseInt(move[0].substring(1, 2)) - 1, Integer.parseInt(move[0].substring(0, 1)) - 1);
                    toCoords = new Pair(Integer.parseInt(move[1].substring(1, 2)) - 1, Integer.parseInt(move[1].substring(0, 1)) - 1);

                    // Checking if the move is valid
                    if (verifyCoordinates(fromCoords) && verifyCoordinates(toCoords))
                    {
                        if (boardObject.board[fromCoords.a][fromCoords.b] != null)
                        {
                            fromPiece = boardObject.board[fromCoords.a][fromCoords.b];

                            if (fromPiece.getPlayer() == turn) 
                            {
                                if (turn == 0) 
                                {
                                    kingOfInterest = whiteKing;
                                }
                                else 
                                {
                                    kingOfInterest = blackKing;
                                }

                                if (boardObject.isValidMove(fromCoords, toCoords, turn) || !kingOfInterest.moved && (kingOfInterest.row == 0 && kingOfInterest.col == 4 && ((toCoords.a == 0 && toCoords.b == 0) || (toCoords.a == 0 && toCoords.b == 7))) || (kingOfInterest.row == 7 && kingOfInterest.col == 4 && ((toCoords.a == 7 && toCoords.b == 0) || (toCoords.a == 7 && toCoords.b == 7))))
                                {
                                    // Getting squares in check
                                    checkedSquares = getCheckedSpaces(boardObject.board, turn);

                                    // Checking if a king is in check
                                    if (checkedSquares[kingOfInterest.row][kingOfInterest.col])
                                    {
                                        // If king is not in check, make the move. Otherwise, display that the move is invalid
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
                                        // If the piece is not in check and is a king, we need to make sure the square they're moving to is also not in checks
                                        if (fromPiece.name.equals("king"))
                                        {
                                            if (!checkedSquares[toCoords.a][toCoords.b])
                                            {

                                                // Checking if a move is a castle
                                                if (!kingOfInterest.moved && 
                                                ((kingOfInterest.row == 0 && kingOfInterest.col == 4 && ((toCoords.a == 0 && toCoords.b == 0) || (toCoords.a == 0 && toCoords.b == 7))) || 
                                                (kingOfInterest.row == 7 && kingOfInterest.col == 4 && ((toCoords.a == 7 && toCoords.b == 0) || (toCoords.a == 7 && toCoords.b == 7)))))
                                                {

                                                    if (turn == 0)
                                                    {
                                                        // Left castle
                                                        if (toCoords.a == 0 && toCoords.b == 0)
                                                        {
                                                            if (boardObject.board[0][3] == null && boardObject.board[0][2] == null && boardObject.board[0][1] == null && (boardObject.board[0][0] == null || boardObject.board[0][0].name.equals("rook")))
                                                            {
                                                                swap(fromPiece, fromCoords, toCoords, boardObject);
                                                                turnProgressed = true;
                                                            }
                                                            else 
                                                            {
                                                                c.println("That piece cannot move there!");
                                                            }
                                                        }
                                                        // Right Castle
                                                        else if (toCoords.a == 0 && toCoords.b == 7)
                                                        {
                                                            if (boardObject.board[0][5] == null && boardObject.board[0][6] == null && (boardObject.board[0][7] == null || boardObject.board[0][7].name.equals("rook")))
                                                            {
                                                                swap(fromPiece, fromCoords, toCoords, boardObject);

                                                                turnProgressed = true;
                                                            }
                                                            else 
                                                            {
                                                                c.println("That piece cannot move there!");
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
                                                            if (boardObject.board[7][3] == null && boardObject.board[7][2] == null && boardObject.board[7][1] == null && (boardObject.board[7][0] == null || boardObject.board[7][0].name.equals("rook")))
                                                            {
                                                                swap(fromPiece, fromCoords, toCoords, boardObject);

                                                                turnProgressed = true;
                                                            }
                                                            else 
                                                            {
                                                                c.println("That piece cannot move there!");
                                                            }
                                                        }
                                                        // Right Castle
                                                        else if (toCoords.a == 7 && toCoords.b == 7)
                                                        {
                                                            if (boardObject.board[7][5] == null && boardObject.board[7][6] == null && (boardObject.board[7][7] == null || boardObject.board[7][7].name.equals("rook")))
                                                            {
                                                                swap(fromPiece, fromCoords, toCoords, boardObject);

                                                                turnProgressed = true;
                                                            }
                                                            else 
                                                            {
                                                                c.println("That piece cannot move there!");
                                                            }
                                                        } 
                                                        else 
                                                        {
                                                            c.println("That piece cannot move there!");
                                                        }
                                                    }
                                                }
                                                // If the move is not a castle, just move the piece regularly
                                                else if (boardObject.isValidMove(fromCoords, toCoords, turn))
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
                                                    c.println("That piece cannot go there!");
                                                }
                                            } 
                                            else 
                                            {
                                                c.println("The king cannot go there, as it will be in check!");
                                            }
                                        } 
                                        // If the piece is not a king, move it like a regular piece
                                        else 
                                        {   
                                            // If the piece is not a king, we need to make sure that 
                                            // moving the piece out of the way will not cause the king to be in check
                                            if (turn == 0 && avoidCheckValid(boardObject.board, fromCoords, toCoords, whiteKing) || turn == 1 && avoidCheckValid(boardObject.board, fromCoords, toCoords, blackKing))
                                            {
                                                if (boardObject.isValidMove(fromCoords, toCoords, turn))
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
                                                    c.println("That piece cannot go there!");
                                                }
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

                    // If the user entered a valid move, turnProgressed would be true and then it would progress
                    // onto the next player
                    // If the user did not enter a valid move, do not update the board and do not update the current player
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

                    // Displaying the next move message, along with if the next player is in check
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

        // Prompting the user to return to the menu
        if (!exit)
        {
            c.println();
            c.println("Press any key to continue:");
            c.getChar();

            Board.i.close();
            c.close();
        }
    }

    // Swaps two chess pieces
    public static void swap(ChessPiece fromPiece, Pair fromCoords, Pair toCoords, Board boardObject) 
    {
        // Declaring variables
        ChessPiece temp; 

        // Swapping the two pieces
        temp = boardObject.board[toCoords.a][toCoords.b];

        fromPiece.moved = true;
        temp.moved = true;

        boardObject.board[toCoords.a][toCoords.b] = fromPiece;
        boardObject.board[fromCoords.a][fromCoords.b] = temp;

        fromPiece.row = toCoords.a;
        fromPiece.col = toCoords.b;
        temp.row = fromCoords.a;
        temp.col = fromCoords.b;
    }

    // Checking if a row in the board is clear
    public static boolean checkRowClear(ChessPiece[][] board, int row, int start, int end)
    {
        // Iterating from the start to end and checking if those indices have any pieces
        for (int i = start; i < end; i++)
        {
            if (board[row][i] != null)
            {
                return false; 
            }
        }

        return true;
    }

    // Verifying that these coordinates are within the board's boundaries
    public static boolean verifyCoordinates(Pair pair)
    {
        // Checking if the coordinate is within the board
        if (pair.a >= 0 && pair.a < 8 && pair.b >= 0 && pair.b < 8)
        {
            return true;
        }

        return false;
    }

    // Getting which spaces on the board are in check (are "attacked" by other pieces)
    public static boolean[][] getCheckedSpaces(ChessPiece[][] board, int player)
    {
        // Declaring variables
        boolean[][] ans;

        // Initializing variables
        ans = new boolean[8][8];

        // Iterating through all the moves of each piece and marking the squares those pieces can reach as "checked"
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

    // Checking if a move causes the king to go OUT of check (therefore it is valid)
    public static boolean avoidCheckValid(ChessPiece[][] board, Pair from, Pair to, ChessPiece king)
    {
        // Declaring variables
        ChessPiece[][] boardClone;
        ChessPiece fromPiece;
        boolean[][] checkedSquares;

        // Initializing variables
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

        checkedSquares = getCheckedSpaces(boardClone, board[from.a][from.b].getPlayer());

        return !checkedSquares[king.row][king.col];
    }

    // Checking if the current state of the game is a checkmate, stalemate, or a regular position
    public static int getGameOutcome(ChessPiece[][] board, ChessPiece whiteKing, ChessPiece blackKing, int turn)
    {
        // Declaring variables
        int validWhiteMoves; 
        int validBlackMoves; 
        boolean whiteCheckmate;
        boolean blackCheckmate;
        ChessPiece kingOfInterest;

        // Initializing variables
        validWhiteMoves = 0;
        validBlackMoves = 0;
        whiteCheckmate = getCheckedSpaces(board, 0)[whiteKing.row][whiteKing.col];
        blackCheckmate = getCheckedSpaces(board, 1)[blackKing.row][blackKing.col];


        // Iterating the moves for each piece on the chessboard, and seeing if checkmate/stalemate is the only outcome possible
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