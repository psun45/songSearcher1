import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

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
   * @throws IOException 
   *    
   */
  @Test
  public void testLoadFileNoCSV() throws IOException {
    
    FrontendClass frontend = new FrontendClass();

    TextUITester tester = new TextUITester("song");                                                 // file input doesn't include ".csv"
    
    frontend.loadFile();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("song.csv"), "inputted file withough .csv wasn't converted");
  }
  
  /**
   * Loading File Tester Method
   * 
   * input does include the ".csv" found in the file name
   * @throws IOException 
   *    
   */
  @Test
  public void testLoadFilePerfectInput() throws IOException {
    
    TextUITester tester = new TextUITester("song.csv");                                             // file input does include ".csv"
    FrontendClass frontend = new FrontendClass();

    frontend.loadFile();
    
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
    FrontendClass frontend = new FrontendClass();

    frontend.showAvgScore();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("the average dancebility score for the Song Set is"),
        "did not display correct average dancibility score message");
    
    /**assertTrue(output.contains("the average dancebility score for the Song Set is"), 
        "did not display correct average dancibility score message"); **/
  }
  
  /**
   * Testing specific dancibility score
   * 
   * input is a typed number in letters rather than the numeral number
   * @throws IOException 
   *    
   */
  @Test
  public void testExitMethod() throws IOException {
    
    TextUITester tester = new TextUITester("3");                                                    // 3 should displya exit message
    FrontendClass frontend = new FrontendClass();

    frontend.mainLoop();
    
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
    FrontendClass frontend = new FrontendClass();

    frontend.showAvgScore();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("the average dancebility score for the Song Set is"),
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
        + "/n"
        + "1"
        + "/n"
        + "3");                                                                                     // good input 
    frontend frontend = new frontend();
    
    frontend.showAvgScore();
    
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
    
    TextUITester tester = new TextUITester("");                                                     // good input 
    frontend frontend = new frontend();

    frontend.showAvgScore();
    
    String output = tester.checkOutput();                                                           // simulated output?
    
    assertTrue(output.contains("the average dancebility score for the Song Set is"),
        "did not display correct average dancibility score message");
  }
  
  /**
   * Testing some of the capabilities of the Backend Class
   * 
   * Checking the getting of songs above a dancibility level method
   * 3/4 songs should be added to the new list
   *    
   */
  @Test
  void BackendTests01() {

      // Description: Tests getting songs above a specified danceability threshold when none match.
      IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
      songList.insertSingleKey(new Song("Bob", "Bob's Song", 2000, "Genre", 0.6));
      songList.insertSingleKey(new Song("Kirk", "Kirk's Song", 2022, "Genre", 0.45));
      songList.insertSingleKey(new Song("Dillon", "Dillon's Song", 1999, "Genre", 0.3));
      songList.insertSingleKey(new Song("Dude", "Dude's Song", 2015, "Genre", 0.9));


      Backend backend = new Backend(songList);
      IterableMultiKeyRBT<Song> songsAboveThreshold = backend.getSongsAboveDanceabilityThreshold(songList, 0.4);

      assertEquals(3, songsAboveThreshold.size());
  }
  
  /**
   * Testing some of the capabilities of the Backend Class
   * 
   * Checking the average of a few songs is correect
   *    
   */
  @Test
  public void BackendTests02() {
    
    // Tests calculation of average danceability score when the song list is empty.
    IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
    songList.insertSingleKey(new Song("Bob", "Bob's Song", 2000, "Genre", 0.6));
    songList.insertSingleKey(new Song("Dillon", "Dillon's Song", 1999, "Genre", 0.3));
    songList.insertSingleKey(new Song("Dude", "Dude's Song", 2015, "Genre", 0.9));    
    
    
    Backend backend = new Backend(songList);
    double averageDanceability = backend.calculateAverageDanceabilityScore(songList);

    assertEquals(0.6, averageDanceability);
}
  
  
}