
import java.io.*;
/**
 *
 * @author Craig's
 */
public class UserInput {
    public String getUserInput(String prompt) {
     String inputLine = null; // set input to null before input
     System.out.print(prompt + "  "); // ask for input then allow input
     try {
       BufferedReader is = new BufferedReader(
	 new InputStreamReader(System.in)); // create reader object
       inputLine = is.readLine();  // get input from reader
       if (inputLine.length() == 0 )  return null; // check if input is blank, return null
     } catch (IOException e) {
       System.out.println("IOException: " + e); // error catch, return exception
     }
    return inputLine.toLowerCase();} // convert to lower case before returning.

         }
