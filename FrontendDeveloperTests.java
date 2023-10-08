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
   *    
   */
  @Test
  public void testLoadFileNoCSV() {
    
    TextUITester tester = new TextUITester("song");                                                 // file input doesn't include ".csv"
    
    FrontendInterface.loadFile();
    
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
    
    TextUITester tester = new TextUITester("song.csv");                                              // file input does include ".csv"
    
    FrontendInterface.loadFile();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("song.csv"), "inputted file withough .csv wasn't converted");
  }
  
  /**
   * Testing average dancibility score
   * 
   * no input required, output should be some words + the score
   *    
   */
  @Test
  public void testAverageDancibilityScore() {
    
    TextUITester tester = new TextUITester("");                                                     // file input does include ".csv"
    
    FrontendInterface.showAvgScore();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("Average Dancibility Score of data: "), 
        "did not display correct average dancibility score message");
  }
  
  /**
   * Testing specific dancibility score
   * 
   * input is a typed number in letters rather than the numeral number
   *    
   */
  @Test
  public void testDancibilityScoreWrittenNumber() {
    
    TextUITester tester = new TextUITester("twenty");                                               // file input does include ".csv"
    
    FrontendInterface.listSongs();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("please input a numerical number, not a written number"), 
        "did not output 'written number' error");
  }
  
  /**
   * Testing specific dancibility score
   * 
   * input is not a usable number
   *    
   */
  @Test
  public void testDancibilityScoreInvalidNumber() {
    
    TextUITester tester = new TextUITester("-25");                                                  // file input does include ".csv"
    
    FrontendInterface.listSongs();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("number must be between X and XXX"), 
        "input was not between max and min values");
  }
  
}