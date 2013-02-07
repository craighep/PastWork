

/**
 *
 * @author Craig's
 */
public class grid {
    
public void main2(String[] argv)
    {
        //the board for the game
        String[][] grid = new String[12][12];
        //creating the players
        displayGrid(grid);
    }


    
    public static void displayGrid(String[][] array)
            
    {
    String z;
 // create string where positions of players and hunters be displayed on grid.
    Game player = new Game();  // create instance of player
    int playerx = player.getx(); // get player x position
    int playery = player.gety (); // get player y position

   Hunter hunter = new Hunter(); // create new instance of Hunter
   hunter.hunter(hunter);        // run hunter method
   int hunterx = hunter.getxl1(); // get 1st hunter position x
   int huntery = hunter.getyl1 (); // get 1st hunter position y
   Hunter hunter2 = new Hunter();//create new instance for second hunter. etc.
   hunter2.hunter2(hunter2);
   int hunter2x = hunter2.getxl2();
   int hunter2y = hunter2.getyl2 ();
   Hunter hunter3 = new Hunter();
   hunter3.hunter3(hunter3);
   int hunter3x = hunter3.getxl3();
   int hunter3y = hunter3.getyl3 ();
   Hunter hunter4 = new Hunter();
   hunter4.hunter4(hunter4);
   int hunter4x = hunter4.getxl4();
   int hunter4y = hunter4.getyl4 ();
   Hunter hunter5 = new Hunter();
   hunter5.hunter5(hunter5);
   int hunter5x = hunter5.getxl5();
   int hunter5y = hunter5.getyl5 ();
    System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _");
    // prints out top line of grid
    
 int countery; // create counter y to set number of verticle columns in grid
 int counterx; // create counter x to set number of verticle columns in grid
       for (countery= 0; countery < 12; countery++){ 
         // for loop set to print ot verticles
            for (counterx = 0; counterx < 12; counterx++){ 
         // another for loop within to print out each x value within each y
               // find each hunter position on grid and print "H" whhere nessecary. 
               if (hunterx == 0 & huntery == 0){ //
                   z = "H";   }
               if (hunter2x == 0 & hunter2y == 0){
                 z = "H";               }
               if (hunter3x == 0 & hunter3y == 0){
                 z = "H";               }
               if (hunter4x == 0 & hunter4y == 0){
                 z = "H";               }
               if (hunter5x == 0 & hunter5y == 0){
                 z = "H";               }
               if (countery == playery && counterx == playerx){ 
                   // find player position and print 'P' on grid
                z = "P";}
              else if (countery == huntery && counterx == hunterx){
                z = "H";} 
              else if (countery == hunter2y && counterx == hunter2x){
                z = "H";} 
              else if (countery == hunter3y && counterx == hunter3x){
                z = "H";} 
              else if (countery == hunter4y && counterx == hunter4x){
                z = "H";} 
              else if (countery == hunter5y && counterx == hunter5x){
                z = "H";} 
              else z = "_"; // if hunter or player is not in this location, print blank space.
            
              
            System.out.print("|" + z ); // print the grid including the positions of objects.
            } System.out.println("|");}} // add the egde of the grid at end of every line.
}