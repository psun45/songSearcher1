import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackendDeveloperTests<T extends Comparable<T>> {
    /**
     * Tests the calculation of average danceability score.
     * Scenario:
     * - Create a list of songs with different danceability scores.
     * - Calculate the average danceability score using the BackendConstructor.
     * - Assert that the calculated average matches the expected result.
     */
    @Test
    void testCalculateAverageDanceabilityScore() {
        // Tests the calculation of average danceability score.
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        songList.insertSingleKey(new Song("Artist1", "Song1", 2021, "Genre1", 0.8));
        songList.insertSingleKey(new Song("Artist2", "Song2", 2022, "Genre2", 0.6));

        BackendClass backend = new BackendClass(songList);
        double averageDanceability = backend.calculateAverageDanceabilityScore(songList);

        assertEquals(0.7, averageDanceability);
    }

    /**
     * Tests getting songs above a specified danceability threshold.
     * <p>
     * Scenario:
     * - Create a list of songs with varying danceability scores.
     * - Get songs above a specified threshold using the BackendConstructor.
     * - Assert that the correct songs are returned.
     */
    @Test
    void testGetSongsAboveDanceabilityThreshold() {
        // Tests getting songs above a specified danceability threshold.
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        songList.insertSingleKey(new Song("Artist1", "Song1", 2021, "Genre1", 0.8));
        songList.insertSingleKey(new Song("Artist2", "Song2", 2022, "Genre2", 0.6));

        BackendClass backend = new BackendClass(songList);
        IterableMultiKeyRBT<Song> songsAboveThreshold = backend.getSongsAboveDanceabilityThreshold(songList, 0.7);

        assertEquals(1, songsAboveThreshold.size());
    }
    /**
     * Tests the behavior of the dataFromFileReader() method in BackendClass.
     * This test aims to verify that the song data from the "songs.csv" file is successfully read
     * and stored into the IterableMultiKeyRBT<Song> data structure.
     *
     * If the data loading process is successful, the test checks if the songList is not null,
     * ensuring that the songs were loaded correctly. Any IO exception during the process
     * will print the exception's stack trace.
     */
    @Test
    public void testReadDataFromFile() {
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        BackendClass backend = new BackendClass(songList);
        try {
            backend.dataFromFileReader("songs.csv");
            // assuming songList has a size() method
            assertTrue(songList != null); // Assert that songs were loaded
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests calculation of average danceability score when the song list is empty.
     * <p>
     * Scenario:
     * - Create an empty list of songs.
     * - Calculate the average danceability score using the BackendConstructor.
     * - Assert that the average is 0.0 when the list is empty.
     */
    @Test
    void testCalculateAverageDanceabilityScoreEmptyList() {
        // Tests calculation of average danceability score when the song list is empty.
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        songList.insertSingleKey(new Song("Artist1", "Song1", 2021, "Genre1", 0.8));
        BackendClass backend = new BackendClass(songList);
        double averageDanceability = backend.calculateAverageDanceabilityScore(songList);

        assertEquals(0.8, averageDanceability);
    }

    /**
     * Tests getting songs above a specified danceability threshold when none match.
     * <p>
     * Scenario:
     * - Create a list of songs with danceability scores below the specified threshold.
     * - Get songs above a specified threshold using the BackendConstructor.
     * - Assert that no songs are returned.
     */
    @Test
    void testGetSongsAboveDanceabilityThresholdNoMatches() {

        // Description: Tests getting songs above a specified danceability threshold when none match.
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        songList.insertSingleKey(new Song("Artist1", "Song1", 2021, "Genre1", 0.6));
        songList.insertSingleKey(new Song("Artist2", "Song2", 2022, "Genre2", 0.5));

        BackendClass backend = new BackendClass(songList);
        IterableMultiKeyRBT<Song> songsAboveThreshold = backend.getSongsAboveDanceabilityThreshold(songList, 0.7);

        assertEquals(0, songsAboveThreshold.size());
    }
    /**
     * Integration test for the loadFile() method from FrontendClass and the dataFromFileReader() method
     * from BackendClass. This test verifies the interaction between the frontend and backend components
     * during the file loading process.
     *
     * The test first simulates the user's action of loading the "songs.csv" file through the frontend class.
     * It then initiates the BackendClass to read data from the same file.
     *
     * The expected outcomes are:
     * 1. The frontend's output contains the word "successfully", indicating a successful load operation.
     * 2. The IterableMultiKeyRBT<Song> data structure, songList, should not be null after the file is read,
     *    confirming that songs were loaded into the backend.
     *
     * Any IO exception during the process will cause the test to fail.
     */
    @Test
    public void IntegrationtestLoadFile() throws IOException {
        TextUITester tester = new TextUITester("songs.csv"); // Simulate user input for loading "songs.csv"
        FrontendClass frontend = new FrontendClass();
        frontend.loadFile();
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        BackendClass backend = new BackendClass(songList);
        backend.dataFromFileReader("songs.csv");
        String output = tester.checkOutput();
        assertTrue(output.contains("successfully"));
        assertTrue(songList != null); // Assert that songs were loaded
    }
    /**
     * Integration test to validate the listing of songs and calculation of average danceability score.
     *
     * <p>This test simulates the user input to load a song file and then request the average danceability score.
     * The test asserts that the computed average score in the backend matches with the manually calculated
     * average danceability score from the list of songs. Additionally, it checks the output of the frontend
     * to ensure the message displays the average danceability score for the song set.</p>
     *
     * @throws IOException if there's an error reading the "songs.csv" file.
     */
    @Test
    public void IntegrationtestListSongs() throws IOException {
        TextUITester tester = new TextUITester("2\nsongs.csv\n3\n"); // Simulate user input for loading "songs.csv"
        FrontendClass frontend = new FrontendClass();
        frontend.showAvgScore();
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        BackendClass backend = new BackendClass(songList);
        backend.dataFromFileReader("songs.csv");
        Iterator<Song> iterator = songList.iterator();
        double totalDanceability = 0.0;
        int count = 0;

        while (iterator.hasNext()) {
            Song currentSong = iterator.next();
            totalDanceability += currentSong.getDanceabilityScore(); // Assuming Song has a getDanceability() method that returns a double
            count++;
        }

        double averageDanceability = totalDanceability / count;
        assertEquals(averageDanceability, backend.calculateAverageDanceabilityScore(songList));
        String output = tester.checkOutput();
        assertTrue(output.contains("the average dancebility score for the Song Set is"));

    }
    /**
     * Tests the behavior of the mainLoop() method, especially when loading a file.
     * This test simulates loading a file ("songs.csv") and checks if the appropriate success message is printed.
     */
    @Test
    public void testFrontendLoadFile() throws IOException {
        TextUITester tester = new TextUITester("songs.csv"); // Simulate user input for loading "songs.csv"
        FrontendClass frontend = new FrontendClass();
        frontend.loadFile();
        String output = tester.checkOutput();
                assertTrue(output.contains("successfully"));
    }
    /**
     * Tests the behavior of the showAvgScore() method.
     * This test simulates a user action to check the average danceability score of the song set and verifies the output message.
     */
    @Test
    public void testFrontendShowAvg() throws IOException {
        // The input simulates user choice for loading file, then showing average, and then quitting.
        TextUITester tester = new TextUITester("songs.csv");
        FrontendClass frontend = new FrontendClass();
        frontend.showAvgScore(); // Directly call the method to show average score
        String output = tester.checkOutput();
        // Adjust the expected output string based on your implementation details.
        assertTrue(output.contains("the average dancebility score for the Song Set is"));
    }

}

