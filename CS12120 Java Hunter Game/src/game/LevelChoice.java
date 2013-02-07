

import java.util.InputMismatchException; // load the exception libraray

public class LevelChoice {
    public static int levels; // create variable levels ton store number of levels chosen
    public void levelChoice(LevelChoice levelc){
     String level = null; // create variable for user to input into choosing level amount
     
    UserInput user = new UserInput (); // create instance of user input class
   do {
   level = user.getUserInput("How Many Levels Would You Like To Play? 1/2");  // use input method to create prompt to ask for 
    try {                                                                       // levels, then take this in using the Int created.
   if ("2".equals(level)){ // if the amount entered is 2, then set levels to 2
        levels = 2;
        break;}
        
    if ("1".equals(level)){ // vice versa
        levels = 1;
        break;}  // break if found, this will end the try loop.
    
    else
    {System.out.println("Invalid level input!"); // if answer is not 1 or 2, then tell user input is invalid.
    continue;}
    } catch (final InputMismatchException e) { // create exception to stop invalid inputs creating errors.
        System.out.println("Invalid input");
        
        continue;
    }
   } while (true);  
    }
 public static int getL() // return levels to be used in Game class etc.
 {
      return LevelChoice.levels;
 }}

