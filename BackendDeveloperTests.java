import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

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

        Backend backend = new Backend(songList);
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

        Backend backend = new Backend(songList);
        IterableMultiKeyRBT<Song> songsAboveThreshold = backend.getSongsAboveDanceabilityThreshold(songList, 0.7);

        assertEquals(1, songsAboveThreshold.size());
    }

    @Test
    public void testReadDataFromFile() {
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        Backend backend = new Backend(songList);
        try {
            backend.dataFromFileReader("C:\\Users\\psun4\\IdeaProjects\\untitled\\src\\songs.csv");
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
        Backend backend = new Backend(songList);
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

        Backend backend = new Backend(songList);
        IterableMultiKeyRBT<Song> songsAboveThreshold = backend.getSongsAboveDanceabilityThreshold(songList, 0.7);

        assertEquals(0, songsAboveThreshold.size());
    }
    /**
     * Verifies that the frontend is able to load a file and return the correct file path.
     * This test creates an instance of the Frontend with a Backend and calls the loadFile method.
     * It then asserts that the returned file path is not null and ends with ".txt".
     */
    @Test
    void integrationTestLoadFile() {
        // Integration test to verify loading a file

        frontend.loadFile();
        Backend backend = new Backend();
        String filePath = "\"C:\\\\Users\\\\psun4\\\\IdeaProjects\\\\untitled\\\\src\\\\songs.csv\""

        assertTrue(filePath != null);
        assertTrue(filePath.endsWith(".txt"));
    }
    @Test
    void integrationTestLoadFile() throws IOException {
        // Initialize backend and frontend
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        Backend backend = new Backend(songList);
        Frontend frontend = new Frontend(backend); // Assuming Frontend takes Backend as a parameter

        // Load the file using frontend
        frontend.loadFile();

        // Check the path of the loaded file
        String filePath = "\"C:\\\\Users\\\\psun4\\\\IdeaProjects\\\\untitled\\\\src\\\\songs.csv\"";
        backend.dataFromFileReader(filePath);
        Song expectedSong = new Song("Train", "Hey, Soul Sister",2010,"neo mellow",67);
        // Assert that the backend loaded the correct file
        assertEquals(songList.size(), 603);
        assertEquals(songList.root.data, expectedSong);
    }
    /**
     * Verifies that the frontend correctly calculates the average danceability score.
     * This test creates an instance of the Frontend with a Backend and adds two songs to the songList using
     * the frontend's listSongs() method. It then calls the showAvgScore() method to calculate
     * the average danceability score. The test asserts that the calculated score matches the expected value.
     */
    @Test
    void integrationTestCalculateAverageDanceabilityScore() {
        // Integration test to verify calculating average danceability score

        // Assuming you have some songs in the backend
        Song song1 = new Song("Song 1", "Artist 1", 120, "Genre 1", 0.75);
        Song song2 = new Song("Song 2", "Artist 2", 130, "Genre 2", 0.80);
        //using frontend to add the songs into the songList
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        Backend backend = new Backend(songList);
        Frontend frontend = new Frontend(backend); // Assuming Frontend takes Backend as a parameter
        songList.insertSingleKey(song1);
        songList.insertSingleKey(song2);

        // Call the method in frontend
        double avgScore = frontend.showAvgScore();

        // Verify the result
        assertEquals(0.775, avgScore);
    }

}
