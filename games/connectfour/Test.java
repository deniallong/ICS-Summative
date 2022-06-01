package games.connectfour;

import java.io.File;
import java.io.IOException;

import hsa.Console;
import utils.Utils;
import utils.ImageDisplayer;

public class Test
{
    static Console c;
    static ImageDisplayer i;
    static File boardFile;

    public static void display() throws IOException
    {
        c.clear();

        boardFile = new File("resources/images/Connect4_Empty.png");

        i.display(boardFile, 0, 0, c);
    }

    public static void main(String[] args) throws IOException
    {
        c = new Console();
        i = new ImageDisplayer();
        display();
    }   
}
