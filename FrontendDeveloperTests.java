import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

// --== CS400 Fall 2023 File Header Information ==--
// Name: ALEX SCHMITT
// Email: ATSCHMITT2@wisc.edu
// Group: B25
// TA: ZHEYANG XIONG
// Lecturer: GARY DAHL
// Notes to Grader: <optional extra notes>
// Description: 5 JUnit tests to test the implementation of the frontend development


public class FrontendDeveloperTests {
  
  
  /**
   * Loading File Tester Method
   * 
   * input doesn't include the ".csv" found in the file name
   * code should check to see if this is included and if not, it will add it to the end
   * inputed code is as follows
   *    "song"      enters a file to load without the .csv at the end
   *    
   */
  @Test
  public void testLoadFileNoCSV() {
    
    
    TextUITester tester = new TextUITester("song");                                                 // file input doesn't include ".csv"
    
    FrontendClass.loadFile();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("song.csv"), "inputted file withough .csv wasn't converted");
  }
  
  /**
   * Loading File Tester Method
   * 
   * input does include the ".csv" found in the file name
   *    
   */
  @Test
  public void testLoadFilePerfectInput() {
    
    TextUITester tester = new TextUITester("song.csv");                                             // file input does include ".csv"
    
    FrontendClass.loadFile();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("song.csv"), "inputted file .csv wasn't loaded correctly");
  }
  
  /**
   * Testing average dancibility score
   * 
   * getting to Dancibility Score and then exitting the program
   * 
   * input:
   *    none
   */
  @Test
  public void testAverageDancibilityScore() {
    
    TextUITester tester = new TextUITester("");                                                     // file input does include ".csv"
    
    FrontendClass.showAvgScore();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("50.0"),
        "did not display correct average dancibility score message");
    
    /**assertTrue(output.contains("the average dancebility score for the Song Set is"), 
        "did not display correct average dancibility score message"); **/
  }
  
  /**
   * Testing specific dancibility score
   * 
   * input is a typed number in letters rather than the numeral number
   *    
   */
  @Test
  public void testExitMethod() {
    
    TextUITester tester = new TextUITester("3");                                                    // 3 should displya exit message
    
    FrontendClass.mainLoop();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("thanks for using Song Searcher"), 
        "failed to exit");

  }
  
  /**
   * Testing specific dancibility score
   * 
   * input is a correct input
   *    
   */
  @Test
  public void testDancibilityScoreInvalidNumber() {
    
    TextUITester tester = new TextUITester("");                                                     // file input does include ".csv"
    
    FrontendClass.showAvgScore();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("50.0"),
        "did not display correct average dancibility score message");
    
    /**assertTrue(output.contains("the average dancebility score for the Song Set is"), 
        "did not display correct average dancibility score message"); **/
  }
  
  // P16 TESTS
  
  /**
   * testing full capabilities of the app with different input
   * 
   * input is a correct input
   *    
   */
  @Test
  public void testFullMainLoop01() {
    
    TextUITester tester = new TextUITester("0"
        + "/n"
        + "song.csv"
        + "1"
        + "3");                                                                                     // good input 
    
    FrontendClass.listSongs();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("the average dancebility score for the Song Set is"), 
        "did not get to the right point in the app");
  }
  
  
  /**
   * testing full capabilities of the app with different input
   * 
   * input is a correct input
   *    
   */
  @Test
  public void testFullMainLoop02() {
    
    TextUITester tester = new TextUITester("0"
        + "/n"
        + "song.csv"
        + "2"
        + "3");                                                                                     // good input 
    
    FrontendClass.listSongs();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("Here are the songs: "), 
        "did not get to the right point in the app");
  }
  
  
}