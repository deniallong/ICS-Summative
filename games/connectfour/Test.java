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
        int choices[] = {1,2,2,2,2,2,2,3,4,5,6,7,8,9,0};
        int input = 2;

        for(int index = 0; index < Utils.search(choices, input).length; index ++)
        {
            System.out.print(Utils.search(choices, input)[index]);
            System.out.print("bruh");
        }        
    }
}

