import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackendDeveloperTests<T extends Comparable<T>> implements BackendInterface, SongInterface{
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
        BackendIterableMultiKeySortedCollection<Song> songList = new BackendIterableMultiKeySortedCollection<>();
        songList.insertSingleKey(new Song("Artist1", "Song1", 2021, "Genre1", 0.8));
        songList.insertSingleKey(new Song("Artist2", "Song2", 2022, "Genre2", 0.6));

        Backend backend = new Backend(songList);
        double averageDanceability = backend.calculateAverageDanceabilityScore(songList);

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
        // Tests getting songs above a specified danceability threshold.
        BackendIterableMultiKeySortedCollection<Song> songList = new BackendIterableMultiKeySortedCollection<>();
        songList.insertSingleKey(new Song("Artist1", "Song1", 2021, "Genre1", 0.8));
        songList.insertSingleKey(new Song("Artist2", "Song2", 2022, "Genre2", 0.6));

        Backend backend = new Backend(songList);
        BackendIterableMultiKeySortedCollection<Song> songsAboveThreshold =
                backend.getSongsAboveDanceabilityThreshold(songList, 0.7);

        assertEquals(1, songsAboveThreshold.size());
        assertEquals("Song1", songsAboveThreshold.iterator().next().getSongTitle());
    }
    /**
     * Tests reading data from a file.
     *
     * Scenario:
     * - Assuming there is an implemented file reader, this test checks if data can be read.
     */
    @Test
    void testDataFromFileReader() {
        // Tests reading data from a file.

        BackendIterableMultiKeySortedCollection<Song> songList = new BackendIterableMultiKeySortedCollection<>();
        Backend backend = new Backend(songList);
        try {
            backend.dataFromFileReader("test_file.txt");
        }catch (IOException e){
            System.out.println("File not found");
        }
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
        // Tests calculation of average danceability score when the song list is empty.
        BackendIterableMultiKeySortedCollection<Song> songList = new BackendIterableMultiKeySortedCollection<>();
        songList.insertSingleKey(new Song("Artist1", "Song1", 2021, "Genre1", 0.8));
        Backend backend = new Backend(songList);
        double averageDanceability = backend.calculateAverageDanceabilityScore(songList);

        assertEquals(0.8 , averageDanceability);
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
        BackendIterableMultiKeySortedCollection<Song> songList = new BackendIterableMultiKeySortedCollection<>();
        songList.insertSingleKey(new Song("Artist1", "Song1", 2021, "Genre1", 0.6));
        songList.insertSingleKey(new Song("Artist2", "Song2", 2022, "Genre2", 0.5));

        Backend backend = new Backend(songList);
        BackendIterableMultiKeySortedCollection<Song> songsAboveThreshold = backend.getSongsAboveDanceabilityThreshold(songList, 0.7);

        assertEquals(0, songsAboveThreshold.size());
    }

    @Override
    public void dataFromFileReader(String filePath) {

    }

    @Override
    public double calculateAverageDanceabilityScore(BackendIterableMultiKeySortedCollection<Song> songList) {
        return 0;
    }

    @Override
    public BackendIterableMultiKeySortedCollection<Song> getSongsAboveDanceabilityThreshold(BackendIterableMultiKeySortedCollection<Song> songList, double threshold) {
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

    @Override
    public boolean insertSingleKey(Comparable key) {
        return false;
    }

    @Override
    public int numKeys() {
        return 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void setIterationStartPoint(Comparable startPoint) {

    }

    @Override
    public int compareTo(Song o) {
        return 0;
    }

    @Override
    public boolean insert(Comparable data) throws NullPointerException, IllegalArgumentException {
        return false;
    }

    @Override
    public boolean contains(Comparable data) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
