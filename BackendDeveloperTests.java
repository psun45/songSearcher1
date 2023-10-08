import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackendDeveloperTests<T extends Comparable<T>> implements BackendInterface, Song{
    /**
     * Tests the calculation of average danceability score.
     * Scenario:
     * - Create a list of songs with different danceability scores.
     * - Calculate the average danceability score using the BackendConstructor.
     * - Assert that the calculated average matches the expected result.
     */
    @Test
    void testCalculateAverageDanceabilityScore() {
        // Description: Tests the calculation of average danceability score.
        // Create a list of songs
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Artist1", "Song1", 2021, "Genre1", 0.8));
        songList.add(new Song("Artist2", "Song2", 2022, "Genre2", 0.6));

        // Calculate the average danceability score
        BackendConstructor backend = new BackendConstructor(songList);
        double averageDanceability = backend.calculateAverageDanceabilityScore(songList);

        // Assert that the average danceability is calculated correctly
        assertEquals(0.7, averageDanceability);
    }
    /**
     * Tests getting songs above a specified danceability threshold.
     *
     * Scenario:
     * - Create a list of songs with varying danceability scores.
     * - Get songs above a specified threshold using the BackendConstructor.
     * - Assert that the correct songs are returned.
     */
    @Test
    void testGetSongsAboveDanceabilityThreshold() {
        // Description: Tests getting songs above a specified danceability threshold.
        // Create a list of songs
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Artist1", "Song1", 2021, "Genre1", 0.8));
        songList.add(new Song("Artist2", "Song2", 2022, "Genre2", 0.6));

        // Get songs above a certain threshold
        BackendConstructor backend = new BackendConstructor(songList);
        List<Song> songsAboveThreshold = backend.getSongsAboveDanceabilityThreshold(songList, 0.7);

        // Assert that the correct songs are returned
        assertEquals(1, songsAboveThreshold.size());
        assertEquals("Song1", songsAboveThreshold.get(0).getSongTitle());
    }
    /**
     * Tests reading data from a file.
     *
     * Scenario:
     * - Assuming there is an implemented file reader, this test checks if data can be read.
     */
    @Test
    void testDataFromFileReader() {
        // Description: Tests reading data from a file.
        // Assuming you have a file reader implementation, you can test it here
        BackendConstructor backend = new BackendConstructor(songList);
        backend.dataFromFileReader("test_file.txt");
    }
    /**
     * Tests calculation of average danceability score when the song list is empty.
     *
     * Scenario:
     * - Create an empty list of songs.
     * - Calculate the average danceability score using the BackendConstructor.
     * - Assert that the average is 0.0 when the list is empty.
     */
    @Test
    void testCalculateAverageDanceabilityScoreEmptyList() {
        // Description: Tests calculation of average danceability score when the song list is empty.
        // Test when the song list is empty
        List<Song> songList = new ArrayList<>();

        BackendConstructor backend = new BackendConstructor(songList);
        double averageDanceability = backend.calculateAverageDanceabilityScore(songList);

        // Assert that the average danceability is 0 when the list is empty
        assertEquals(0.0, averageDanceability);
    }
    /**
     * Tests getting songs above a specified danceability threshold when none match.
     *
     * Scenario:
     * - Create a list of songs with danceability scores below the specified threshold.
     * - Get songs above a specified threshold using the BackendConstructor.
     * - Assert that no songs are returned.
     */
    @Test
    void testGetSongsAboveDanceabilityThresholdNoMatches() {
        // Description: Tests getting songs above a specified danceability threshold when none match.
        // Test when there are no songs above the specified threshold
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Artist1", "Song1", 2021, "Genre1", 0.6));
        songList.add(new Song("Artist2", "Song2", 2022, "Genre2", 0.5));

        BackendConstructor backend = new BackendConstructor(songList);
        List<Song> songsAboveThreshold = backend.getSongsAboveDanceabilityThreshold(songList, 0.7);

        // Assert that no songs are returned
        assertEquals(0, songsAboveThreshold);
    }

    @Override
    public void dataFromFileReader(String filePath) {

    }

    @Override
    public double calculateAverageDanceabilityScore(List<Song> songList) {
        return 0;
    }

    @Override
    public List<Song> getSongsAboveDanceabilityThreshold(List<Song> songList, double threshold) {
        return null;
    }

    @Override
    public String getArtist() {
        return null;
    }

    @Override
    public String getSongTitle() {
        return null;
    }

    @Override
    public int getYear() {
        return 0;
    }

    @Override
    public String getGenre() {
        return null;
    }

    @Override
    public double getDanceabilityScore() {
        return 0;
    }
}
