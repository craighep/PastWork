package uk.ac.aber.dcs.cs21120.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class WordLadder
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
            	System.out.println("Word '"+w1+"' not in dictionary!");}
            else{
            System.out.println( "Enter the second word: " );
            String w2 = in.readLine( );
            if(w2.equals("x")){
            	clrScr('\n');
            	AltStart start = new AltStart();
            	start.start();   
            }if (w1.length() != w2.length()){
            	clrScr('\n');
            	System.out.println("Words not same length!");
            }
            else{
            if (!words.contains(w2)){clrScr('\n');
            System.out.println("Word '"+w2+"' not in dictionary!");}
            else{
          
        
            //Data data = new Data();
            
           // w1 = data.getWord1();
            //w2 = data.getWord2();
            
            if (!words.contains(w2)){System.out.println("Word '"+w2+"' not in dictionary!");}

            else{
            List<String> path = findChain( nearbyWords, w1, w2 );
            //data.setPath(path);
            clrScr('\n');
            if(path.size() ==0){
            	System.out.println("Path could not be found");
            }
            System.out.println( "PathSize was " +path.size( ) + "..." );
            for( String word : path )
                System.out.print( " " + word );
            System.out.println("\n" );    
            }}}}}
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
                 
    
    
    
    // After the shortest path calculation has run, computes the List that
    // contains the sequence of word changes to get from first to second.
    public static List<String> getChainFromPreviousMap( Map<String,String> prev,
                                                        String first, String second )
    {
        LinkedList<String> result = new LinkedList<String>( );
        
        if( prev.get( second ) != null ){
            for( String str = second; str != null; str = prev.get( str ) ){
                result.addFirst( str );}
        }
        return result;
    }
    
    // Runs the shortest path calculation from the adjacency map, returning a List
    // that contains the sequence of words changes to get from first to second.
    public static List<String> findChain( Map<String,List<String>> adjacentWords, String first, String second )            
    {
        Map<String,String> previousWord = new HashMap<String,String>( );
        Queue<String> q = new LinkedList<String>( );
        
        q.add( first );
        while( !q.isEmpty( ) )
        {
            String current = q.element( ); q.remove( );
            List<String> adj = adjacentWords.get( current );
            
            if( adj != null )
                for( String adjWord : adj )
                    if( previousWord.get( adjWord ) == null )
                    {
                        previousWord.put( adjWord, current );
                        q.add( adjWord );
                    }                                                
        }
        
        previousWord.put( first, null );
        
        return getChainFromPreviousMap( previousWord, first, second );
    }
    
    // Runs the shortest path calculation from the original list of words, returning
    // a List that contains the sequence of word changes to get from first to
    // second. Since this calls computeAdjacentWords, it is recommended that the
    // user instead call computeAdjacentWords once and then call other findChar for
    // each word pair.
    public static List<String> findChain( List<String> words, String first, String second )
    {
        Map<String,List<String>> adjacentWords = computeAdjacentWords( words );
        return findChain( adjacentWords, first, second );
    }
    
    public void clrScr(char c){
    	int length = 25;
    	char[] chars = new char[length];
    	Arrays.fill(chars, c);
    	System.out.print(String.valueOf(chars));
    }
}
