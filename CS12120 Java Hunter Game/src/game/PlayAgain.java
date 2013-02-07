
import java.util.InputMismatchException;
/**
 *
 * @author Craig's
 */
public class PlayAgain {
void playagain(String [] args){ 
    UserInput user = new UserInput (); // create new user input instance
    do {
   String playagain = user.getUserInput("Would You Like To Play Again? y/n"); 
    try { // use user input class to generate prompt and allow user to chose if they want to play again.
   if ("y".equals(playagain)){ 
       
        Game.score = 0;
        Game.strength = 5;
        Game.x = 0;                     // if answer is yes, then reset scores, strength, location etc.
        Game.y = 0;
        Hunter hunter = new Hunter();
        Hunter.xLoc1 = 12; Hunter.yLoc1 = 12;
        Hunter.xLoc2 = 13; Hunter.yLoc2 = 13;
        Hunter.xLoc3 = 14; Hunter.yLoc3 = 14;
        Hunter.xLoc4 = 15; Hunter.yLoc4 = 15;
        Hunter.xLoc5 = 16; Hunter.yLoc5 = 16;
        Game.main(args); // Play the whole game again
        break;} // break out of the do statement
        
    if ("n".equals(playagain)){
        System.out.println("Thanks for playing!");
        break;}       // if answer is no, then end the java application
    
    else
    {System.out.println("Invalid Reply!"); // if answer is not y or n, then tell user input is incorrect
    continue;} 
    } catch (final InputMismatchException e) { // create an exception which makes user input again.
        System.out.println("Invalid input");
        
        continue;
    }
   } while (true);     
}}
