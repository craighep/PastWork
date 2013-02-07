package uk.ac.aber.dcs.cs21120.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class StepLadder
{
	public Map<String,List<String>> nearbyWords; //create an empty map of Strings and lists of strings

	public void run() throws IOException
    {
        double start, end;
        double time;
        
        Data data = new Data();
        List<String> words = data.getDict();
        System.out.println( "Program Recieved " + words.size( ) + " Words..");
        System.out.println("LOADING...");
        System.out.println("\n" ); 
        start = System.currentTimeMillis( );
        nearbyWords = computeAdjacentWords( words ); //get near by adjacent words (words that link together)
        end = System.currentTimeMillis( );
        time = (end-start)/1000;	//Calculate time taken to compute and link together Adjacent words
        
        System.out.println( "Elapsed time to compute: " + time + " seconds");
        go(words);
        
    }
     public void go(List<String> words) throws IOException{   
    	 
    	    
            BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
            while(true){
            	System.out.println("\n" ); 
            System.out.println("To exit to menu, type 'x' at any time.");
            System.out.println("\n" ); 
            System.out.println( "Enter the first word: " );
            String w1 = in.readLine( ); 
            if(w1.equals("x")){
            	clrScr('\n');
            	AltStart start = new AltStart();
            	start.start();
            }
            if (!words.contains(w1)){clrScr('\n');
            System.out.println("Word '"+w1+"' Not In Dictionary!");}
            else{
            if(w1.equals("x")){
            	clrScr('\n');
            	AltStart start = new AltStart();
            	start.start();   
            }
            System.out.println("Enter desired ladder length: ");
            String sLength = in.readLine();
            if(sLength.equals("x")){
            	clrScr('\n');
            	AltStart start = new AltStart();
            	start.start();
            }
            int length = Integer.parseInt (sLength);
            
            //Data data = new Data();
            
           // w1 = data.getWord1();
            //w2 = data.getWord2();
           
            
            List<String> reqPath = nearbyWords.get(w1);
            if (reqPath.size()< length){
            	System.out.println("Ladder could not be made long enough!");
            }
			 
			else{
            //data.setPath(path);
				List<String> path =reqPath.subList(0, length-1);
				
				clrScr('\n');
				System.out.print(w1);
            for( String word : path )
                System.out.print( " " + word );
            
            }}}
    }


 
    
    private static <KeyType> void update( Map<KeyType,List<String>> m, KeyType key, String value )
    {
        List<String> lst = m.get( key );
        if( lst == null )
        {
            lst = new ArrayList<String>( );
            m.put( key, lst );
        }
        
        lst.add( value );
    }
    
    
    // Computes a map in which the keys are words and values are Lists of words
    // that differ in only one character from the corresponding key.
    // Uses an efficient algorithm that is O(N log N) with a TreeMap, or
    // O(N) if a HashMap is used.
    public static Map<String,List<String>> computeAdjacentWords( List<String> words )
    {
        Map<String,List<String>> adjWords = new HashMap<String,List<String>>( );
        Map<Integer,List<String>> wordsLenghth = new HashMap<Integer,List<String>>( );
    
          // Group the words by their length
        for( String w : words ){
            update( wordsLenghth, w.length( ), w );}

          // Work on each group separately
        for( Map.Entry<Integer,List<String>> entry : wordsLenghth.entrySet( ) )
        {    
            List<String> groupsWords = entry.getValue( );
            int groupNum = entry.getKey( );
            
            // Work on each position in each group
            for( int i = 0; i < groupNum; i++ )
            {
                // Remove one character in specified position, computing representative.
                // Words with same representative are adjacent, so first popular
                // a map
                Map<String,List<String>> repToWord = new HashMap<String,List<String>>( );
                
                for( String str : groupsWords )
                {
                    String rep = str.substring( 0, i ) + str.substring( i + 1 );
                    update( repToWord, rep, str );
                }
                
                // and then look for map values with more than one string
                for( List<String> wordClique : repToWord.values( ) )
                    if( wordClique.size( ) >= 2 )
                        for( String s1 : wordClique )
                            for( String s2 : wordClique )
                                if( s1 != s2 )
                                    update( adjWords, s1, s2 );
            }
        }
        
        return adjWords;
    }
                 
    public void clrScr(char c){
    	int length = 25;
    	char[] chars = new char[length];
    	Arrays.fill(chars, c);
    	System.out.print(String.valueOf(chars));
    }
    
   
}
