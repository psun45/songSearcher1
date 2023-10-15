import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the backend logic for managing a collection of songs.
 * It implements both the BackendInterface and SongInterface.
 */
public class Backend implements BackendInterface, SongInterface {
    private BackendIterableMultiKeySortedCollection<Song> songList;

    /**
     * Constructs a Backend object with the provided songList.
     *
     * @param songList The collection of songs to be managed by this backend.
     */
    public Backend(BackendIterableMultiKeySortedCollection<Song> songList) {
        this.songList = songList;
    }

    /**
     * Reads data from a file and populates the songList with Song objects.
     *
     * @param filePath The path to the file containing song data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    @Override
    public void dataFromFileReader(String filePath) throws IOException {
        //how to write the method dataFromFileReader so that the data is read from the file
        List<String> lines = Files.readAllLines(Paths.get(filePath));


        for (int i = 0; i < lines.size(); i++) {
            String[] songData = lines.get(i).split(",");
            Song song = new Song(songData[0], songData[1], Integer.parseInt(songData[2]),
                    songData[3], Double.parseDouble(songData[4]));
            songList.insert(song);
        }

    }

    /**
     * Calculates the average danceability score of the songs in the provided songList.
     *
     * @param songList The collection of songs to calculate the average danceability score for.
     * @return The average danceability score.
     * @throws ArithmeticException If the songList is empty.
     */
    @Override
    public double calculateAverageDanceabilityScore(BackendIterableMultiKeySortedCollection<Song> songList)
            throws ArithmeticException {
        double sum = 0;
        Iterator<Song> iterator = songList.iterator(); // Assuming you have an iterator method

        while (iterator.hasNext()) {
            Song song = iterator.next();
            sum += song.getDanceabilityScore();
        }

        return sum / songList.size();
    }

    /**
     * Retrieves songs from the provided songList with a danceability score above the threshold.
     *
     * @param songList  The collection of songs to filter.
     * @param threshold The minimum danceability score for a song to be included.
     * @return A collection of songs with danceability scores above the threshold.
     */
    @Override
    public BackendIterableMultiKeySortedCollection<Song> getSongsAboveDanceabilityThreshold(
            BackendIterableMultiKeySortedCollection<Song> songList, double threshold) {
        BackendIterableMultiKeySortedCollection<Song> songsAboveThreshold =
                new BackendIterableMultiKeySortedCollection<>();

        Iterator<Song> iterator = songList.iterator(); // Iterator for the songList

        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getDanceabilityScore() > threshold) {
                songsAboveThreshold.insertSingleKey(song);
            }
        }

        return songsAboveThreshold;
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
    public int compareTo(Song o) {
        return 0;
    }
}
