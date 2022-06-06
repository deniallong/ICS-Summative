package games.connectfour;

import hsa.Console;
import utils.Utils;

public class Test {

    static Board b;
    static Console c;

    public static void main(String[] args)
    {
        /* b = new Board();
        
        b.print();
        int array [] = Utils.getColumn(b.board, 3);
        for(int index = 0; index < array.length; index ++)
        {
            System.out.print(array[index] + "b");
        } */
        c = new Console();
        int choices[] = {1,2,3,4,5};
        
        int input = Utils.input("bruh: ", choices, c);

        c.println(input);
    }
}

