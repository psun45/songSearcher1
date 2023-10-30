//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Title: Frontend Interface for Song Searcher App
// Course: CS 400 Fall 2023
//
// Authors: Erin Cunningham, Alex Schmitt, Kevin George
// Emails: eecunningha2@wisc.edu, atschmitt2@wisc.edu, shinegeorge@wisc.edu
// Lecturer: Gary Dahl
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

/**
 * Implements the functionality of the frontend for the Song Searcher app.
 * 
 * @author Erin Cunningham, Alex Schmitt, Kevin George
 */
public interface FrontendInterface {
  
  /**
   * Constructor for the FrontendInterface class.
   * 
   * @param backend   A reference to the backend of the app.
   * @param userInput A Scanner instance to read user input.
   */
  // public IndividualFrontendInterface (BackendInterface backend, Scanner userInput);

  /**
   * Prompts the user to specify which data file to load and loads the file.
   * 
   * @return String filePath The file path of the loaded file.
   */
  public static String loadFile() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Prompts the user for a danceability score and lists all songs with the specified score.
   */
  public static void listSongs() {
    // TODO Auto-generated method stub
    
  }

  /**
   * Shows the average danceability score in the loaded dataset.
   */
  public static void showAvgScore() {
    // TODO Auto-generated method stub
    
  }

  /**
   * Exits the Song Searcher app.
   */
  public static void exit() {
  }

  /**
   * Prints an invalid input message to the console.
   * 
   * @param message The message to be printed for invalid input.
   */
  public void invalidInput(String message);
  
  
  
  /**
   * Starts the main command loop for the user by prompting user to select a command. The command
   * loop is broken down into separate sub menu methods below this method.
   */
  public static void mainLoop() {
  }

}