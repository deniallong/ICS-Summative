package games.connectfour;

import utils.Utils;

public class Test {

    static Board b;
    public static void main(String[] args)
    {
        b = new Board();
        
        b.print();
        int array [] = Utils.getColumn(b.board, 3);
        for(int index = 0; index < array.length; index ++)
        {
            System.out.print(array[index] + "b");
        }
    }
}
