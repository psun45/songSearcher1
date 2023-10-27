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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implements the functionality of the frontend for the Song Searcher app.
 * 
 * @author Erin Cunningham, Alex Schmitt, Kevin George
 */
public class FrontendClass implements FrontendInterface {
  
  /**
   * Constructor for the FrontendInterface class.
   * 
   * @param backend   A reference to the backend of the app.
   * @param userInput A Scanner instance to read user input.
   */
  // public IndividualFrontendInterface (BackendInterface backend, Scanner userInput);
  
   private IterableMultiKeyRBT<Song> currentPlaylist = new IterableMultiKeyRBT<>();                                        // the current playlist we are on, initialized to be an empty list
  BackendClass backend = new BackendClass(currentPlaylist);
  /**
   * Prompts the user to specify which data file to load and loads the file.
   * 
   * @return String filePath The file path of the loaded file.
   */
  public String loadFile() throws IOException {
    
    boolean repeat = true;                                                                          // will continue until valid input
    String file = "";
    
    while (repeat) {

      Scanner userInput = new Scanner(System.in);
  
      System.out.println("Enter a valid playlist");                                                 // requesting a file to load
      
      file = userInput.nextLine();                                                                  // reads the file from the user
            
      if (file == null) {
        System.out.println("please enter a file");
        break;
      }
      
      else if (file.contains(".csv")) {                                                             // check to see if it contains ".csv"
        currentPlaylist = backend.dataFromFileReader(file);
        repeat = false;                                                                             // terminates the while loop
      }
      
      else if (!file.contains(".csv")) {                                                            // entered file doesn't end in csv
        file = file.concat(".csv");
        currentPlaylist = backend.dataFromFileReader(file);
        repeat = false;                                                                             // terminates the while loop
      }
      
      else {
        System.out.println("please enter a file");                                                  // not acceptable type
      }
    }
        
    System.out.println(file + " file loaded successfully");
    
    return file;
  }

  /**
   * Prompts the user for a danceability score based off the highest and lowest scores in the present
   * song list and lists all songs with the specified score.
   */
  public void listSongs() {
    
    boolean repeat = true;                                                                          // will continue until valid input
    double score;
    while (repeat) {

      Scanner userInput = new Scanner(System.in);
  
      System.out.println("Please enter a Dancebility Score between 0.0 and 100.0");                 // requesting a dancebility level
      
      String input = userInput.nextLine();                                                          // reads the file from the user
            
      try {                                                                                         // this checks to make sure the inputed
        score = Double.parseDouble(input);                                                          // value is a double
        
        IterableMultiKeyRBT<Song> songList = backend.getSongsAboveDanceabilityThreshold(currentPlaylist, score); // backend command to attain songs
        repeat = false;
        // Extracting song titles from songList
        ArrayList<String> songTitles = new ArrayList<>();
        for (Song song : songList) {
          songTitles.add(song.getSongTitle());
        }

        // Displaying the song titles
        System.out.println("Here are the songs: " + songTitles);
        
    } catch (NumberFormatException e) {
        System.out.println("Please enter a number in number form, not written");
        continue;
    }
} 
  }

  /**
   * Shows the average danceability score in the loaded dataset.
   */
  public void showAvgScore() {
    double  averageScore = 0.0;                                                                     // average score variable
    averageScore = backend.calculateAverageDanceabilityScore(currentPlaylist);                                   // Backend Command
    System.out.println("the average dancebility score for the Song Set is " + averageScore);
  }

  /**
   * Exits the Song Searcher app.
   */
  public void exit()  {
    System.out.println("thanks for using Song Searcher");
  }

  /**
   * Prints an invalid input message to the console.
   * 
   * @param message The message to be printed for invalid input.
   */
  public void invalidInput(String message) {
  }

  /**
   * Starts the main command loop for the user by prompting user to select a command relating to a number. 
   * The command loop will then run certain command methods based on input.
   */
  public void mainLoop() throws IOException {
    
    Scanner userInput = new Scanner(System.in);
    
    System.out.println("Welcome to Song Searcher!!!");                                              // welcome message
    
    boolean repeat = true;                                                                          // will continue until valid input
    
    while (repeat) {
  
      System.out.println("Select a command: \n(0) Enter a file to uplaod to Song Searcher \n"
          + "(1) display average dancibility score \n"
          + "(2) list all songs that have a specific danceability score \n"
          + "(3) exit the app");      
      String command = userInput.nextLine();                                                        // reads the command from the user
      
      if (command.equals("0")) {
        String LoadedFile = loadFile();                                                             // backend command
      }
      
      else if (command.equals("1")) {
        showAvgScore();                                                                             // backend command
      }
      
      else if (command.equals("2")) {
        listSongs();                                                                                // backend command
      }
      
      else if (command.equals("3")) {
        exit();
        repeat = false;
      }
      
      else {
        System.out.println("please enter a valid option");
      }
    }    
  }
 
}