package games.connectfour;

import java.awt.*;
import hsa.Console;

public class DrillSet7
{
    static Console c;
    
    public static int getInt(String prompt, int low, int high) 
    {
		//Variable Declaration
		int input;
	
		//Prompts user to enter an integer.
		c.print(prompt);
		input = c.readInt();
	
		//Checks if integer is within the valid range.
		if(input >= low && input <= high)
			//Returns the valid input.
			return input;
			
		else
		{
			//Outputs the valid range and prompts user to try again.
			c.println("Your input is invalid. Inputs must be an integer between " + low + " and " + high + ", inclusive.");
			c.print("Try a valid input: ");
			
			//Returns the method.
			return getInt("", low, high);
		}
    }

    public static int[] fill(int size)
    {
		//Declares an integer array of length size.
		int array[] = new int[size];
		
		//Puts a random integer between 0 and size in every index in the array.
		for(int index = 0; index < size; index++)
		{
			array[index] = (int) (Math.random() * size) + 1;
		}
		
		//Returns the array.
		return array;
    }
    
    public static int[] enter() 
    {
		//Variable Declaration
		int index, input;
		int array[] = new int[1000];
		
		//Variable Initialisation
		index = 0;

		//Tells user to input positive integers.
		c.println("Enter positive integers. Enter '-1' to quit.");
		
		//Loops until user inputs "-1".
		do
		{
			//Prompts user to enter integers.
			input = getInt("Enter a positive integer: ", -1, 2147483647);

			//Puts user input into corresponding array index.
			array[index] = input;
			index++;
		}
		while(input != -1);

		//Declares array containing all inputs size.
		int returnArray[] = new int[index - 1];

		//Enters inputs into array.
		for(index = 0; index < returnArray.length; index++)
			returnArray[index] = array[index];

		//Returns the array.
		return returnArray;
    }
    
    public static void print(int[] array) 
    {
		//Prints every element of the array with a space.
		for(int index = 0; index < array.length; index++)
			c.print(array[index] + " ");
		
		//Prints newline.
		c.println();
    }
    
    public static int[] search(int[] searchArray , int target) 
    {
		//Variable Declaration
		int found, returnIndex;

		//Variable Initialisation
		found = 0;
		returnIndex = 0;
		
		//Notes down how many instances the array contains the target.
		for(int index = 0; index < searchArray.length; index++)
		{            
			if(searchArray[index] == target)
				found++;
		}
		
		if(found == 0)
			c.println("Not found.");

		//Declares an array with corresponding length.
		int returnArray[] = new int[found];

		//Enters the indices where the target is found in the original array in the new array.
		for(int index = 0; index < searchArray.length; index++)
		{
			if(searchArray[index] == target)
			{
				returnArray[returnIndex] = index;
				returnIndex++;
			}
		}

		//Returns the array of indices.
		return returnArray;
    }
    
    public static int[] add(int[] array, int addition)
	{
		//Declares the array.
		int returnArray[] = new int[array.length + 1];

		//Enters all the elements of the old array into the new array.
		for(int index = 0; index < array.length; index++)
			returnArray[index] = array[index];
		
		//Adds the new element to the end of the array.
		returnArray[array.length] = addition;

		//Returns the new array.
		return returnArray;
	}
	
	public static int[] remove(int[] array, int removalIndex)
	{
		//Declares the array.
		int returnArray[] = new int[array.length - 1];

		//Enters all the elements before the removal into the new array.
		for(int index = 0; index < removalIndex; index++)
			returnArray[index] = array[index];
		
		//Enters all the elements after the removal into the new array.
		for(int index = removalIndex; index < returnArray.length; index++)
			returnArray[index] = array[index + 1];

		//Returns the array.
		return returnArray;
	}

	public static int[] sort(int[] array)
	{               
		//Variable Declaration.
		int a, b, t;

		//Loops therough the array, switching elements that are out of order until the array is sorted.
		for(a = 1; a < array.length; a++) 
			for(b = array.length - 1; b >= a; b--) 
				if(array[b - 1] > array[b])
				{
					t = array[b - 1];
					array[b - 1] = array[b];
					array[b] = t;
				}
		
		//Returns the array.
		return array;
	}
    
    public static void main (String[] args)
    {
		c = new Console ();

		char choice;
		int[] list;

		//Displays the menu and prompts the user to enter the programs.
		do
		{
			c.clear();
			c.println("Problem Set 7");
			c.println("--------------");
			c.println("1. Fill");
			c.println("2. Enter");
			c.println("3. Add");
			c.println("4. Remove");
			c.println("5. Search");
			c.println("6. Sort");
			
			c.println("\nPress any number for the any of the options above. Press anything other than the listed options to quit.");
			choice = c.getChar();
			
			c.clear();
			
			if(choice == '1')
			{
				//Variable Declaration
				int size;
				
				//Variable Initialisation
				size = getInt("Enter the size of your array: ", 0, 100);

				//Processing
				list = fill(size); // create array of 20 random values from 0 to 20
				print(list);
			}

			if(choice == '2')
			{
				//Processing
				list = enter(); // read values from user
				print(list);
			}

			if(choice == '3')
			{
				//Variable Declaration
				int addition;

				//Array Initialisation
				list = enter();

				//Variable Initialisation
				addition = getInt("Enter your addition to the array: ", 0, 2147483647);

				//Processing
				list = add(list, addition); // add 99 to end of list
				print(list);
			}
			if(choice == '4')
			{
				
				//Variable Declaration
				int removalIndex;

				//Array Initialisation
				list = enter();

				//Variable Initialisation
				removalIndex = getInt("Enter the position of the element you wish to remove (First position is 0 and so on): ", 0, list.length - 1);

				//Processing
				list = remove(list, removalIndex); // remove item 3 from list (test for other values)
				print(list);
			}

			if(choice == '5')
			{
				//Variable Declaration
				int searchTarget;

				//Array Initialisation
				list = enter();

				//Variable Initialisation
				searchTarget = getInt("Enter the element you wish to find: ", 0, 2147483647);

				//Processing
				int positions[] = search(list, searchTarget); // search for zeroes in list
				print(positions); // print positions of all zeroes
			}
			
			if(choice == '6')
			{
				//Array Initialisation
				list = enter();

				//Processing
				sort(list); // sort from low to high
				print(list);
			}
			
			c.println("\nPress any key to go back.");
			c.getChar();
		}
		while(choice == '1' || choice == '2' || choice == '3' || choice == '4' || choice == '5' || choice == '6');
	}
}