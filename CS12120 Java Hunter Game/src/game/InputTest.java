/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Craig's
 */
public class InputTest {
    public static void main(String [] args){
     UserInput input = new UserInput(); // creates and insatnce of the user input class
     String userInput;                  // create a variable to test the class with
     userInput = input.getUserInput(""); // runt the method of the tested class
     System.out.println(userInput);  // print out the result from the class to be checked.
 }
}
