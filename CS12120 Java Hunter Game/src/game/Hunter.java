
/** *
 * @author Craig's
 */
import java.util.Random;

public class Hunter {
  public static int xLoc1 = 12; // declare each hunters x and y posiitions,
  public static int yLoc1 = 12; // place each off of the board so the appear one by one.
  public static int xLoc2 = 13;
  public static int yLoc2 = 13;
  public static int xLoc3 = 14;
  public static int yLoc3 = 14;
  public static int xLoc4 = 15;
  public static int yLoc4 = 15;
  public static int xLoc5 = 16;
  public static int yLoc5 = 16;
  

    void hunter(Hunter hunter) {
      Random randomnumber = new Random();  //Create new instance of random number
    for (int i = 0; i <= 1; ++i){
      int randomInt = randomnumber.nextInt(2); //create random number from two values, either o or 1

      if (yLoc1 <= 12 && yLoc1 >11 ) { // start the hunter away from the board, then move onto the board.
          xLoc1--; yLoc1--; break;
      }
      if (xLoc1 == 0 && yLoc1 ==0) // make hunters dissapear off the board before they go back to start.
      { xLoc1--; break;}
      if (xLoc1 == -1 && yLoc1 == 0){ // after they have moved off board, add one to score and place back at start.
         xLoc1 = 11;
         yLoc1 = 11;  Game.score++;
         break;
      }
      if (Game.x <= xLoc1 && Game.y <= yLoc1){ // check if hunter has not passed player
      if (LevelChoice.levels == 2 && Game.score > 42 && xLoc1 > 0 && yLoc1 > 0) {
          int x = xLoc1 - Game.x; // if level is 2, then create two ints, and use 
          int y = yLoc1 - Game.y; // to compare distance between hunter x and player x, etc.
          if (x< y){ // which ever distance is smaller, move towards he player that way.
              yLoc1--; break;
          } else xLoc1--; break;
      }}
          
      if  (randomInt == 1 && yLoc1 ==0){
        
          xLoc1--; break;} //if the random number is 1,and the hunter is against y axis, move along.
          
       if  (randomInt == 1 && xLoc1 ==0){ // vice versa
        
          yLoc1--; break;}
              
      if (randomInt == 0 && yLoc1 == 0){
    
         xLoc1--; break;} 
      
      if  (randomInt == 0 && xLoc1 ==0){
        
          yLoc1--; break;}
     
      if (randomInt == 0 && xLoc1 >0){ // if random int is 0 and its x position is more than 0, move left.
    
          xLoc1--; break;  }
      if (randomInt == 1 && yLoc1 >0){ // vice versa
    
          yLoc1--; break;} // break after every move to ensure it only does one move
      
     }}
    public int getxl1() // return each int to be used in the grid etc.
    {
       return this.xLoc1;
        
    }
    public int getyl1()
    {
     return this.yLoc1;
    }
    
    
    void hunter2(Hunter hunter2) {
            Random randomnumber = new Random();
    for (int i = 0; i <= 1; ++i){
      int randomInt = randomnumber.nextInt(2);
      if (yLoc2 <= 13 && yLoc2 >11 ) {
          xLoc2--; yLoc2--; break;
      }
            if (xLoc2 == 0 && yLoc2 ==0)
      { xLoc2--; break;}
      if (xLoc2 == -1 && yLoc2 == 0){
         xLoc2 = 11;
         yLoc2 = 11;  Game.score++;
         break;
      }
   if (Game.x <= xLoc2 && Game.y <= yLoc2){
      if (LevelChoice.levels == 2 && Game.score > 42 && xLoc2 > 0 && yLoc2 > 0) {
          int x = xLoc2 - Game.x;
          int y = yLoc2 - Game.y;
          if (x< y){
              yLoc2--; break;
          } else xLoc2--; break;
      }}
      if  (randomInt == 1 && yLoc2 ==0){
        
          xLoc2--; break;}
          
       if  (randomInt == 1 && xLoc2 ==0){
        
          yLoc2--; break;}
              
      if (randomInt == 0 && yLoc2 == 0){
    
         xLoc2--; break;} 
      
      if  (randomInt == 0 && xLoc2 ==0){
        
          yLoc2--; break;}
     
      if (randomInt == 0 && xLoc2 >0){
    
          xLoc2--; break;  }
      if (randomInt == 1 && yLoc2 >0){
    
          yLoc2--; break;}
           
     
     }}
    public int getxl2()
    {
       return this.xLoc2;
        
    }
    public int getyl2()
    {
     return this.yLoc2;
    }
    void hunter3(Hunter hunter3) {
           Random randomnumber = new Random();
    for (int i = 0; i <= 1; ++i){
      int randomInt = randomnumber.nextInt(2);
      if (yLoc3 <= 14 && yLoc3 >11 ) {
          xLoc3--; yLoc3--; break;
      }
            if (xLoc3 == 0 && yLoc3 ==0)
      { xLoc3--; break;}
      if (xLoc3 == -1 && yLoc3 == 0){
         xLoc3 = 11;
         yLoc3 = 11;
          Game.score++;
         break;
      }
          if (Game.x <= xLoc3 && Game.y <= yLoc3){
      if (LevelChoice.levels == 2 && Game.score > 42 && xLoc3 > 0 && yLoc3 > 0) {
          int x = xLoc3 - Game.x;
          int y = yLoc3 - Game.y;
          if (x< y){
              yLoc3--; break;
          } else xLoc3--; break;
      }}
      if  (randomInt == 1 && yLoc3 ==0){
        
          xLoc3--; break;}
          
       if  (randomInt == 1 && xLoc3 ==0){
        
          yLoc3--; break;}
              
      if (randomInt == 0 && yLoc3 == 0){
    
         xLoc3--; break;} 
      
      if  (randomInt == 0 && xLoc3 ==0){
        
          yLoc3--; break;}
     
      if (randomInt == 0 && xLoc3 >0){
    
          xLoc3--; break;  }
      if (randomInt == 1 && yLoc3 >0){
    
          yLoc3--; break;}
            
     }}
    public int getxl3()
    {
       return this.xLoc3;
        
    }
    public int getyl3()
    {
     return this.yLoc3;
    }
    void hunter4(Hunter hunter4) {
           Random randomnumber = new Random();
    for (int i = 0; i <= 1; ++i){
      int randomInt = randomnumber.nextInt(2);
      if (yLoc4 <= 15 && yLoc4 > 11) {
          xLoc4--; yLoc4--; break;
      }
            if (xLoc4 == 0 && yLoc4 ==0)
      { xLoc4--; break;}
      if (xLoc4 == -1 && yLoc4 == 0){
         xLoc4 = 11;
         yLoc4 = 11;
         Game.score++ ;
         break;
      }
          if (Game.x <= xLoc4 && Game.y <= yLoc4){
      if (LevelChoice.levels == 2 && Game.score > 42 && xLoc4 > 0 && yLoc4 > 0) {
          int x = xLoc4 - Game.x;
          int y = yLoc4 - Game.y;
          if (x< y){
              yLoc4--; break;
          } else xLoc4--; break;
      }}
      if  (randomInt == 1 && yLoc4 ==0){
        
          xLoc4--; break;}
          
       if  (randomInt == 1 && xLoc4 ==0){
        
          yLoc4--; break;}
              
      if (randomInt == 0 && yLoc4 == 0){
    
         xLoc4--; break;} 
      
      if  (randomInt == 0 && xLoc4 ==0){
        
          yLoc4--; break;}
     
      if (randomInt == 0 && xLoc4 >0){
    
          xLoc4--; break;  }
      if (randomInt == 1 && yLoc4 >0){
    
          yLoc4--; break;}
            
     }}
    public int getxl4()
    {
       return this.xLoc4;
        
    }
    public int getyl4()
    {
     return this.yLoc4;
    }
    void hunter5(Hunter hunter5) {
           Random randomnumber = new Random();
    for (int i = 0; i <= 1; ++i){
      int randomInt = randomnumber.nextInt(2);
      if (yLoc5 <= 16 && yLoc5 >11 ) {
          xLoc5--; yLoc5--; break;
      }
            if (xLoc5 == 0 && yLoc5 ==0)
      { xLoc5--; break;}
      if (xLoc5 == -1 && yLoc5 == 0){
         xLoc5 = 11;
         yLoc5 = 11;  Game.score++;
         break;
      }
          if (Game.x <= xLoc5 && Game.y <= yLoc5){
      if (LevelChoice.levels == 2 && Game.score > 42 && xLoc5 > 0 && yLoc5 > 0) {
          int x = xLoc5 - Game.x;
          int y = yLoc5 - Game.y;
          if (x< y){
              yLoc5--; break;
          } else xLoc5--; break;
      }}
      if  (randomInt == 1 && yLoc5 ==0){
        
          xLoc5--; break;}
          
       if  (randomInt == 1 && xLoc5 ==0){
        
          yLoc5--; break;}
              
      if (randomInt == 0 && yLoc5 == 0){
    
         xLoc5--; break;} 
      
      if  (randomInt == 0 && xLoc5 ==0){
        
          yLoc5--; break;}
     
      if (randomInt == 0 && xLoc5 >0){
    
          xLoc5--; break;  }
      if (randomInt == 1 && yLoc5 >0){
    
          yLoc5--; break;}
            
     }}
    public int getxl5()
    {
       return this.xLoc5;
        
    }
    public int getyl5()
    {
     return this.yLoc5;
    }
 }
  
  
 
  


