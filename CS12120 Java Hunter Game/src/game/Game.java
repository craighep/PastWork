

/**
 *
 * @author Craig's
 */
public class Game {
     public static int x; // create player x position variable
     public static int y; // create player y position variable     
     public static int score; // create score variable
     public static int strength; // create strength variable
     public static int finalscore; // create finalscore variable
     public static String name; // create name variable    
            
    public static void main(String[] args) {
     
    
    
    Game.x = 0; // set the players x and y positions on the grid to (0,0) so they start in the top left
    Game.y= 0;
    grid mygrid = new grid(); // create a new instance of the grid
    UserInput user = new UserInput (); // create a new instance of the input method
    LevelChoice levels = new LevelChoice(); // create a new instance of the level selector
    PlayAgain again = new PlayAgain(); // create a new instance of the play again selector
    HighestScore hs = new HighestScore(); // create a new instance of the highscore
    

    // print out the titl of the game         
    
System.out.println("  _____                       _______ _          ");
System.out.println(" / ____|                     |__   __| |         ");
System.out.println("| |     _ __ ___  ___ ___       | |  | |__   ___ ");
System.out.println("  |    | '__/ _ |/ __/ __|      | |  | '_ | / _ | ");   
System.out.println("| |____| | | (_) |__ |__ |      | |  | | | |  __/"); 
System.out.println(" |_____|_|  |___/|___/___/      |_|  |_| |_||___|");
System.out.println(" _____                                  _____");                      
System.out.println(" / ____|                                / ____|");                     
System.out.println("| (___   __ _ _   _  __ _ _ __ ___     | |  __  __ _ _ __ ___   ___");  
System.out.println(" |___ | / _` | | | |/ _` | '__/ _ |    | | |_ |/ _` | '_ ` _ | / _ |"); 
System.out.println(" ____) | (_| | |_| | (_| | | |  __/    | |__| | (_| | | | | | |  __/"); 
System.out.println("|_____/ |__, ||__,_||__,_|_|  |___|     |_____||__,_|_| |_| |_||___|"); 
System.out.println("           | |   ");                                                 
System.out.println("           |_|       ");                                                   
    
System.out.println("Welcome to the Cross-The-Square-Game!");
System.out.println("*************************************");
      
      
      levels.levelChoice(levels); // run the level selector
     
System.out.println("");
System.out.println(""); // create spaces
      
System.out.println("Try to avoid the Hunters by moving across the board!");
System.out.println("Directions: g- Left, h- Right, j- Up, k- Down. Type 'i' during the game to display help!");
System.out.println("");
                
               
                strength = 5;  // set strength to start at 5
                String dir = null; //set the direction variable to null
                mygrid.main2(args);      // run the grid method to display grid
                while (strength > 0){        // ask for input as long as strength is not 0
                dir = user.getUserInput("Type your next move:"); //prompt to type player move
                
                
                if ("g".equals(dir)) {  //if input is g, then move left
                    x = (x - 1);}
                
                else if ("j".equals(dir)) { //if input is j, then move up
                    y = (y -1 );}
                
                else if ("k".equals(dir)) { // if input is k, then move down
                    y = (y +1 );}
                   
                else if ("h".equals(dir)) { // if input is h, then move right
                    x = (x + 1);}
                
                else if ("i".equals(dir)) { // if input is i, then give instructions
                    System.out.println("Directions: g- Left, h- Right, j- Up, k- Down"); }
                else {
                    System.out.println ("Invalid Input! Try again:");
              //  if any otehr input is given, then ask user to try again               
                }
                
                if (x < 0) { // if user moves out of the grid, place them back, and tell them they are out of bounds
                x = 0;    
                System.out.println("You have reached the edge of the game board! Please move elsewhere.");
                }
                if (x > 11) {
                x = 11;    
                System.out.println("You have reached the edge of the game board! Please move elsewhere.");
                }
                if (y < 0) {
                y = 0;    
                System.out.println("You have reached the edge of the game board! Please move elsewhere.");
                }
                if (y > 11) {
                y = 11;    
                System.out.println("You have reached the edge of the game board! Please move elsewhere.");
                }
                  if (x == Hunter.xLoc1 && Game.y == Hunter.yLoc1 ){  // check is user and any hunters are in same position,
                      strength--; Hunter.xLoc1 = 12; Hunter.yLoc1 = 12;}  // then minus one strength and reset hunter position back to start.
                  if (x == Hunter.xLoc2 && Game.y == Hunter.yLoc2 ){
                      strength--; Hunter.xLoc2 = 12; Hunter.yLoc2 = 12;}                   
                  if (x == Hunter.xLoc3 && Game.y == Hunter.yLoc3 ){
                      strength--; Hunter.xLoc3 = 12; Hunter.yLoc3 = 12;}
                  if (x == Hunter.xLoc4 && Game.y == Hunter.yLoc4 ){
                      strength--; Hunter.xLoc4 = 12; Hunter.yLoc4 = 12;}
                  if (x == Hunter.xLoc5 && Game.y == Hunter.yLoc5 ){
                      strength--; Hunter.xLoc5 = 12; Hunter.yLoc5 = 12;
                    }  
                   // print out the users score after every move
                    System.out.println("                              "+ "                      Score: "+ score); 
                    System.out.println("                                       Strength Remaining: " + strength);
                    // print out the number of lives remaining after each move
                   
                    mygrid.main2(args); // run the grid display after each move
                   if (LevelChoice.levels == 1 && score == 42){break;} // if user is only playing one level, skip to end.
                   if (score == 42 && LevelChoice.levels == 2){ // if user is playing 2 levels, print instructions
                   System.out.println(" _                       _    ______                _                                   ");
                   System.out.println("| |                     | |  (_____ |      /|      | |                                  ");
                   System.out.println("| |      ____ _   _ ____| |    ____) )    /  |   _ | | ____ _   _ ____ ____   ____ ____ ");
                   System.out.println("| |     / _  ) | | / _  ) |   /_____/    / /| | / || |/ _  | | | / _  |  _ | / ___) _  )");
                   System.out.println("| |____( (/ / | V ( (/ /| |   _______   | |__| ( (_| ( ( | || V ( ( | | | | ( (__( (/ / ");
                   System.out.println("|_______)____) |_/ |____)_|  (_______)  |______||____||_||_| |_/ |_||_|_| |_||____)____)");
                   System.out.println("ADVANCE TO LEVEL 2!");
                   System.out.println("Watch Out! The Hunters Can Now Detect Your Location!!!");                  
                   strength = 5; score++;} // reset the strngth to 5
                   }
                
                
                
                   if (LevelChoice.levels == 1 && score == 42){  // check if player is playing 1 level and has achieved 42 points
                   System.out.println(" _     _              _  _  _             ");
                   System.out.println("| |   | |            | || || |            ");
                   System.out.println("| |___| |__  _   _   | || || | ___  ____  ");
                   System.out.println(" |_____/ _ || | | |  | ||_|| |/ _ ||  _ | ");
                   System.out.println("   ___| |_| | |_| |  | |___| | |_| | | | |");
                   System.out.println("  (___)|___/ |____|   |______||___/|_| |_|");
                   System.out.println("You Have Completed Levlel One."); } // if yes, then print congratulations
                   if (LevelChoice.levels ==1 && score < 42){ // check if player is playing 1 level and score is less than 42
                   System.out.println(" _     _              _                      ");
                   System.out.println("| |   | |            | |                _    ");
                   System.out.println("| |___| |__  _   _   | |      ___   ___| |_  ");
                   System.out.println(" |_____/ _ || | | |  | |     / _ | /___)  _) ");
                   System.out.println("   ___| |_| | |_| |  | |____| |_| |___ | |__ ");
                   System.out.println("  (___)|___/|_____|  |_______)___/(___/ |___)");    
                       System.out.println("You Failed Level One."); // if yes, print out user has lost game
                   }
                   finalscore = score; //set final score to the score achieved by user
                   System.out.println(" _____           _    ____                        ");
                   System.out.println("| ____|_ __   __| |  / ___| __ _ _ __ ___   ___   ");
                   System.out.println("|  _| | '_ | / _` | | |  _ / _` | '_ ` _ | / _ |  ");
                   System.out.println("| |___| | | | (_| | | |_| | (_| | | | | | |  __/_ ");
                   System.out.println("|_____|_| |_||__,_|  |____||__,_|_| |_| |_||___(_)");
                   System.out.println(""); //print out the end game message.
                   if (LevelChoice.levels == 2 && score < 42){
                       System.out.println("You're Out Of Lives.");
                               System.out.println(""); // check if user ran out of lives, if yes, print out the message
                   }
                   System.out.println("Your Final Score is:" + finalscore); // print out final score
                   name = user.getUserInput("Enter Your Name!:"); // ask for name, using prompt from input class
                   hs.main(null); //run the highscore class method to show user name and highscore
                   
              
                                
                   again.playagain(args); //Ask to play again  
                   
                   
    }
 public int getx() //return player position variables
    {
        return Game.x;        
    }
 public int gety()
 {
     return Game.y;
 }
 public static int score(){ //return player score
 return Game.score;
}
public static int strength(){ // return player strength
 return Game.strength;
}
public static int finalscore()  // return player finalscore
        { return Game.finalscore;
        }
public static String name(){ // return player name
        return Game.name;}
}


    
   

