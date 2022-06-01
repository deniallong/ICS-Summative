// SummativeGame 
// Stephanie Chan & William Dai
// 10-01-2022
// ICS3UP

import hsa.Console;
import java.awt.*;

public class SummativeGame
{
    final static int width = 12, height = 11;
    
    // map sybolizes what the map looks like
    static String[] [] map = {
            {" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
            {" X ", " . ", " . ", " . ", " . ", " . ", " . ", " = ", " . ", " . ", " . ", " X "},
            {" X ", " . ", " - ", " - ", " - ", " . ", " . ", " | ", " . ", " . ", " . ", " X "},
            {" X ", " = ", " | ", " . ", " | ", " . ", " . ", " | ", " - ", " - ", " - ", " X "},
            {" X ", " . ", " . ", " . ", " | ", " . ", " . ", " . ", " . ", " . ", " . ", " X "},
            {" X ", " . ", " - ", " - ", " | ", " . ", " . ", " . ", " | ", " - ", " - ", " X "},
            {" X ", " = ", " | ", " F ", " | ", " . ", " . ", " . ", " . ", " . ", " . ", " X "},
            {" X ", " . ", " . ", " . ", " | ", " . ", " - ", " . ", " . ", " . ", " . ", " X "},
            {" X ", " - ", " - ", " - ", " | ", " = ", " | ", " . ", " . ", " . ", " . ", " X "},
            {" X ", " . ", " . ", " . ", " . ", " . ", " | ", " . ", " . ", " . ", " . ", " X "},
            {" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "}};
       
    // isAccessible symbolizes whether a square is accessible
    // E.g., isAccessible[0][0] = false means player cant go to square (0, 0). In this case (0, 0) is " X ", a wall
    // What squares can be accessed may change as game progresses
    static boolean[] [] isAccessible = {
            {false, false, false, false, false, false, false, false, false, false, false, false},
            {false, true, true, true, true, true, true, false, true, true, true, false},
            {false, true, false, false, false, true, true, false, true, true, true, false},
            {false, false, false, true, false, true, true, false, false, false, false, false},
            {false, true, true, true, false, true, true, true, true, true, true, false},
            {false, true, false, false, false, true, true, true, false, false, false, false},
            {false, false, false, true, false, true, true, true, true, true, true, false},
            {false, true, true, true, false, true, false, true, true, true, true, false},
            {false, false, false, false, false, false, false, true, true, true, true, false},
            {false, true, true, true, true, true, false, true, true, true, true, false},
            {false, false, false, false, false, false, false, false, false, false, false, false}};
    
    // isVisible symbolizes whether a square is visible
    // E.g., isVisible[0][0] = false means player cant see square (0, 0)
    // What squares can be seen may change as game progresses but all squares start off as invisible
    static boolean[] [] isVisible = new boolean [11] [12];
    
    // squareText symbolizes the information associated with each square
    // E.g., squareText[1][1] = "This is where you started." because (1, 1) is starting square
    static String[] [] squareText = {
            {"", "", "", "", "", "", "", "", "", "", "", ""},
            {"", "This is where you start.", "You see a half-eaten Tim's blueberry muffin sitting on top of a chair.", "A group of friends sit on the ground beside you, singing 'Happy Birthday'.", "You see students chaotically running across from you.", "A trail of dirt lies to your right.", "A teacher who introduces herself as Ms. Y instantly demands to know where your homework is. \"I was talking with your science teacher and she told me about you. She was wondering where your homework from 2 weeks ago is finished now.\", she says. You realize that you left it at home and can't think of anything but to run. Would you like to save this name for later?", "You slap the doorframe as you go in.", "You step into the room and instantly notice a large stack of ungraded tests and a picture of a Star Trek character.", "You're surrounded by rows of desks.", "An among us character stares back at you from the chalkboard", ""},
            {"", "Welcome to the weight room! You'll need a hairtie to enter.", "", "", "", "A foldable chair leans against the wall.", "A group of students angrily rave about a math question.", "", "The room smells like cranberry juice.", "An opened 6-month-old expired carton of juice lies on a desk.", "After long minutes of rummaging around inside the desk, the only thing you can find is a giant Jojo Siwa Bow attached to a flimsy hairtie.", ""},
            {"", "\"This room smells musty\", you think to yourself.", "", "You admire your reflection in the mirror in front of you.", "", "A pair of blue Asics running shoes sit in the 'Lost and Found' shelf.", "You wave to a few of your classmates as you pass by them.", "", "", "", "", ""},
            {"", "You hear some people playing basketball in the gym.", "You stand beside a rusty rack of dumbbells.", "A pullup bar is before you. You want to try a pullup but decide not to since it'll be embarassing if you fail.", "", "You hear screams and laughs coming from the washroom on your right.", "You hear screams and laughs coming from the washroom on your right.", "Crumpled up balls of paper are scattered across the floor.", "You step inside the washroom and notice a group of kids in front of you desperately searching for something.", "You overhear them saying something about a key... A missing key... Something important...", "You start washing your hands to not seem suspicious and notice a rusted key sitting on top of the sink! You take a look at it, but realize that it's labelled 'Janitor's Key to the Trash Can.'", ""},
            {"", "Welcome to the gym! Where are your running shoes?", "", "", "", "As you walk beside the wall, you realize that a lot of the paint is chipping.", "You hear screams and laughs coming from the washroom on your right.", "You pass by a teacher angrily staring at the washroom door.", "", "", "", ""},
            {"", "You walk into a basketball tournament.", "", "You find one of your friends stuck inside a ball cage!", "", "You see a short, curly-haired teacher standing in front of the staff room's door.", "You notice a teacher angrily staring at the washroom door.", "You hear some banging coming from the lockers.", "You hear some banging coming from the lockers.", "You hear some banging coming from the lockers.", "You stand in front of the trash can and resist the urge to vomit due to the putrid smell.", ""},
            {"", "Carefully staying on the sidelines of the game, you make your way to the other side and cheer as the home team scores a three-pointer.", "You walk past the score-keepers, they're playing Pokemon Go, not that it matters. It's 106 - 22.", "Confused faces stare back at you as you reach the storage room.", "", "\"Hi there, I'm Kevin. Who are you? I assume a teacher sent you here?\", the teacher at the door says. \"Yeah. Can I go inside the staff room?\", you ask. \"Only if you tell me which teacher sent you here.\"", "", "The banging is getting louder.", "The banging is getting louder.", "The banging is getting louder.", "The banging is getting louder.", ""},
            {"", "", "", "", "", "You're welcomed by the warm smell of coffee and freshly baked pastries.", "", "Someone seems to be stuck in a locker and they start screaming. Instantly, you recognize it to be your friend's scream.", "Someone dressed in all black stands in front of the locker and tells you that someone did indeed get stuffed into the locker. You ask them for the lock's code but they tell you that the group that stuffed your friend in there threw out the code in the trash.", "Someone seems to be stuck in a locker and they start screaming. Instantly, you recognize it to be your friend's scream.", "Someone dressed in all black stands in front of the locker and tells you that someone did indeed get stuffed into the locker. You ask them for the lock's code but they tell you that the group that stuffed your friend in there threw out the code in the trash.", ""},
            {"", "You notice an open box of donuts on the plastic table beside you. A sign beside it reads \"Take ONE only!\"", "You see a piping hot cup of coffee sitting on the cluttered table. You notice that it has a sticky note that says \"For Ms. Y\"", "A dust bunny sits on top of the desk.", "You hear soft classical music playing and immediately recognize it to be \"Moonlight Sonata - 2 Hours Version\"", "You glance around and realize that it doesn't seem to be any different than any other classroom.", "", "You stand in front of locker #067 and hear the screaming coming from your right.", "You stand in front of locker #068 and hear the screaming coming from your right.", "You stand in front of locker #069 and immediately start fumbling with the lock as the shrieks become more and more desperate.", "You stand in front of locker #070 and hear the screaming coming from your left.", ""},
            {"", "", "", "", "", "", "", "", "", "", "", ""}};

    // if ItemHere[i][j] = 2; then Item with ID 2 is at (i, j);
    // ItemHere[i][j] = 0 then there is no item here
    static int[] [] ItemHere = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    // if UseItemHere[i][j] = 2; then Item with ID 2 can be used at (i, j);
    // UseItemHere[i][j] = 0 then there is no item that can be used here
    static int[] [] UseItemHere = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
            {0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0},
            {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    /*
    objects list:
    1. muffin (no use)
    2. teacher's name - friend 1
    3. coffee - friend 1
    4. donuts (no use)
    5. hair tie - friend 1
    6. running shoes - friend 1
    7. key - friend 2
    8. code - friend 2

    hasItem[3]=true; means that the player has the Changing Room Key
    */
    
    static boolean[] hasItem = new boolean [10];
    final static String[] ItemName = {"", "Blueberry Muffin", "Teacher's Name", "Cup of Coffee", "Donuts", "Hair Tie", "Running Shoes", "Key", "Locker Code", ""};

    static int xCoord = 1, yCoord = 1;
    static boolean foundFriend1 = false, foundFriend2 = false;
    
    // Set visible sets the player's current and surrounding 8 squares visible
    public static void setVisible ()
    {
        // Processing, set the 9 nearest coordinates to visible
        isVisible [yCoord] [xCoord] = true;
        isVisible [yCoord - 1] [xCoord - 1] = true;
        isVisible [yCoord - 1] [xCoord] = true;
        isVisible [yCoord - 1] [xCoord + 1] = true;
        isVisible [yCoord] [xCoord + 1] = true;
        isVisible [yCoord + 1] [xCoord + 1] = true;
        isVisible [yCoord + 1] [xCoord] = true;
        isVisible [yCoord + 1] [xCoord - 1] = true;
        isVisible [yCoord] [xCoord - 1] = true;
    }

    // displayMap prints the map
    public static void displayMap ()
    {
        c.println ("Press \"h\" for help.\n");

        for (int i = 0 ; i < height ; i++) // Loop through each row of map
        {
            for (int j = 0 ; j < width ; j++) // Loop through each column of each row
            {
                if (i == yCoord && j == xCoord) // If handling player position,
                {
                    // Print player icon
                    c.setTextColor (Color.orange);
                    c.print (" 8 ");
                    c.setTextColor (Color.black);
                }
                else if (isVisible [i] [j]) // If handling a visible square
                    c.print (map [i] [j]); // Print that square on map
                else // If handling an invisible square
                    c.print ("   "); // Print blank space
            }
            c.println ();
        }
        c.println ();
        
        // Print the information about player position
        c.setTextColor (Color.red);
        c.print ("About your location:\n");
        c.setTextColor (Color.black);
        c.println (squareText [yCoord] [xCoord] + "\n");
    }

    // useAnItem handles what to do when player uses an item
    public static void useAnItem ()
    {
        // Print player's inventory
        c.println ("Inventory: ");
        for (int i = 1 ; i < 10 ; i++) // Loop through each item
        {
            if (hasItem [i]) // If player has that item
                c.println (i + " - " + ItemName [i]); // Print as part of their inventory
        }
        // Prompt and ask for an item (ID form) to use
        c.print ("\nChoose an Item to Use (Enter Item ID): ");
        int itemToUse = c.readInt ();
        c.println ();
        
        // If player has the item they want to use and the item can be used at players location
        if (hasItem [itemToUse] && UseItemHere [yCoord] [xCoord] == itemToUse) 
        {
            // Have to implement effects for each item here. For example you can set some squares to be accessible
            
            // Standard format:
            // 
            // if(itemToUse == [whatever item])
            // {
            //     Print message for using that item
            //     Set hasItem[whatever item] to false as user has used the item, no longer has it
            //     Execute whatever effects come from using that item
            // }
            
            if (itemToUse == 2)
            {
                c.println ("You tell Mr. Pat that the teacher who sent you here is Ms. Y. \n\"Alright, welcome in!\" he says.");
                hasItem [itemToUse] = false;
                isAccessible [8] [5] = true; // Door at (5, 8) opens
            }
            else if (itemToUse == 3)
            {
                c.println ("You hand Ms. Y the cup of coffee, and reluctantly, she says \"Ok fine, I'll let it pass this time.\"");
                hasItem [itemToUse] = false;
                isAccessible [1] [7] = true; // Door at (7, 1) opens
            }
            else if (itemToUse == 5)
            {
                c.println ("Your hair is now tied! Despite people shooting you weird glares, you proudly walk into the weight room, sporting a huge sparkly rainbow coloured bow.");
                hasItem [itemToUse] = false;
                isAccessible [3] [1] = true; // Door at (1, 3) opens
            }
            else if (itemToUse == 6)
            {
                c.println ("You slip on the pair of running shoes, only a bit too big.");
                hasItem [itemToUse] = false;
                isAccessible [6] [1] = true; // Door at (1, 6) opens
            }
            else if (itemToUse == 7)
            {
                c.println ("You reulctantly unlock the trash can as mounds of rotting garbage spills over the sides. \n\nA sparkling box labelled \"Locker 069's lock code\" falls to the ground.");
                hasItem [itemToUse] = false;
                ItemHere [6] [10] = 8; // Because key unlocked trash can, the LockerCode (item ID 8) is not at square (10, 6)
            }
            else if (itemToUse == 8)
            {
                c.println ("You dial the code into the lock and it unlocks. Your friend stares at you in disbelief as a smile quickly grows across their face.");
                hasItem [itemToUse] = false;
                map [9] [9] = " F "; // Because the LockerCode is user square (9, 9) is now friend
                squareText [9] [9] = "Your friend is struggling to get out of the locker. You ask if they need help, but they tell you that they're fine."; //Changes the original text for the position 9, 9 to this message
                foundFriend2 = true; // Found friend so foundFriend2 = true
            }
        }
        else if (!hasItem [itemToUse]) // If player doesn't have the item they want to use
        {
            c.println ("You do not have this Item"); // Inform player he doesnt have item
        }
        else // If player has the item, but item is not meant to be used at players location
        {
            c.println (ItemName [itemToUse] + " cannot be used here"); // Inform player that item is not meant to be used there
        }
    }

    // printInstructions prints instructions
    public static void printInstructions ()
    {
        // Print the instructions
        c.setTextColor (Color.red);
        c.println ("Instructions:");
        c.setTextColor (Color.black);
        c.println ("You are the '8'.\n");
        c.println ("Use keys w (up), a (left), s (down), and d (right) to move.");
        c.println ("Use 'p' to pick something up and 'u' to open your inventory.");
        c.println ("\nYour surroundings will become visible as you explore the map.");
        c.println ("\n'X' represents the border, '.' represents spaces where you can move, '=' represents a door or blockage, and '|' or '-' are walls.\n");

        c.println ("Your goal is to find your two lost friends by venturing into rooms and collecting objects, which will unlock new pathways and open up doors!");

        c.setTextColor (Color.red);
        c.print ("\nReady? Press enter to clear the instructions and to continue! ");
        c.setTextColor (Color.black);
        
        // Let user enter key to clear instructions
        c.getChar ();
        c.clear ();
        
        // Rerender the map that was cleared
        setVisible ();
        displayMap ();
    }

    // gameFinished checks if the game is completed
    public static boolean gameFinished ()
    {
        if (yCoord == 6 && xCoord == 3) // If player is at friend1 positions
        {
            foundFriend1 = true; // Player has found friend 1
        }

        if (foundFriend1 && foundFriend2) // If player has found both friends
        {
            return true; // return game has finished
        }
        else // Otherwise
        {
            return false; // return game has not finished
        }
    }


    static Console c = new Console ();
    // main acts as the driver in this game
    public static void main (String[] args)
    {
        // print instructions when game starts
        printInstructions ();

        while (!gameFinished()) // as long as game is not finished
        {
        
            c.setTextColor (Color.red);
            c.print ("\nGame progress:\n");
            c.setTextColor (Color.black);
            
            if (!foundFriend1 && !foundFriend2) 
            { // If player has not found any friends
                c.println("You haven't found any friends yet.");
            } else if (foundFriend1 || foundFriend2) 
            { // If player has found one friend
                c.println("You've found one friend!");
            }
        
            // get a command
            char command = c.getChar ();
            c.clear ();

            if (command == 'h') // if player wants help
            {
                printInstructions (); // print instructions
                continue; // skip rest of loop and go back to waiting for a command
            }
            
            // If player enters a movement command
            // Change player location
            if (command == 'w' && isAccessible [yCoord - 1] [xCoord])
            {
                yCoord--;
            }
            if (command == 'a' && isAccessible [yCoord] [xCoord - 1])
            {
                xCoord--;
            }
            if (command == 's' && isAccessible [yCoord + 1] [xCoord])
            {
                yCoord++;
            }
            if (command == 'd' && isAccessible [yCoord] [xCoord + 1])
            {
                xCoord++;
            }
            
            // Set nearby squares to visible and render the map
            setVisible ();
            displayMap ();
            
            // If player wants to pick up an item
            if (command == 'p' && ItemHere [yCoord] [xCoord] != 0) // And there is an item here
            {
                hasItem [ItemHere [yCoord] [xCoord]] = true; // Player now has that item
                c.println ("You pick up " + ItemName [ItemHere [yCoord] [xCoord]] + "."); // Inform player he picked up [whatever item]
                ItemHere [yCoord] [xCoord] = 0; // Item is no longer at that square as it has been picked up
            }
            else if (command == 'p') // If player wants to pick something up but there is nothing to pick up
            {
                c.println ("Nothing to pick up here."); // Inform player that square is empty
            }
            if (command == 'u') // If player wants to use and item
            {
                useAnItem (); // call useAnItem to handle all of that
            }
            
        }
        
        // Out of while loop means both friends are found, inform player they've won
        c.clear();
        c.setTextColor (Color.magenta);
        c.println("Congratulations!");
        c.setTextColor (Color.pink);
        c.println("\nYou've found both of your friends! You beat the game!");
    }
}