import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the backend logic for managing a collection of songs.
 * It implements both the BackendInterface and SongInterface.
 */
public class BackendClass implements BackendInterface {
    public IterableMultiKeyRBT<Song> songList = null;

    /**
     * Constructs a Backend object with the provided songList.
     *
     * @param songList The collection of songs to be managed by this backend.
     */
    public BackendClass(IterableMultiKeyRBT<Song> songList) {
        this.songList = songList;
    }

    /**
     * Reads data from a file and populates the songList with Song objects.
     *
     * @param fileName The path to the file containing song data.
     * @return
     * @throws IOException If an I/O error occurs while reading the file.
     */
    @Override
    public IterableMultiKeyRBT<Song> dataFromFileReader(String fileName) throws IOException {
        String filePath ="." + File.separator + fileName;
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (int i = 1; i < lines.size(); i++) {  // starting from 1 to skip the header
            try {
                // Check if the line starts with a quote, indicating that the title is in quotes
                if (lines.get(i).startsWith("\"")) {
                    int closingQuoteIndex = lines.get(i).indexOf("\",");
                    // Check if the line contains a closing quote
                    if (closingQuoteIndex != -1) {
                        String title = lines.get(i).substring(1, closingQuoteIndex);  // Extract title without quotes
                        String restOfLine = lines.get(i).substring(closingQuoteIndex + 2);  // Extract data after title
                        String[] songData = restOfLine.split(",");
                        if(title.contains("\"\"")){
                            title.replace("\"\"", "\"");
                        }
                        Song song = new Song(
                                songData[0].trim(),     // artist
                                title,     // title
                                Integer.parseInt(songData[2].trim()),  // year
                                songData[1].trim(),     // top genre
                                Double.parseDouble(songData[5].trim()) // assuming danceability score is from the dnce column
                        );

                        songList.insertSingleKey(song);

                    } else {
                        System.out.println("Error parsing line " + i + ": Missing closing quote.");
                    }
                } else {
                    // If the line does not start with a quote, the title is not in quotes
                    String[] songData = lines.get(i).split(",");
                    String title = songData[0].trim();
                    if(title.contains("\"\"")){
                        title.replace("\"\"", "\"");
                    }
                    Song song = new Song(
                            songData[1].trim(),     // artist
                            title,     // title
                            Integer.parseInt(songData[3].trim()),  // year
                            songData[2].trim(),     // top genre
                            Double.parseDouble(songData[6].trim()) // assuming danceability score is from the dnce column
                    );
                    songList.insertSingleKey(song);
                }

            } catch (Exception e) {
                System.out.println("Error parsing line " + i + ": " + lines.get(i));
                e.printStackTrace();
            }
        }
        return songList;
    }

    /**
     * Calculates the average danceability score of the songs in the provided songList.
     *
     * @param songList The collection of songs to calculate the average danceability score for.
     * @return The average danceability score.
     * @throws ArithmeticException If the songList is empty.
     */
    @Override
    public double calculateAverageDanceabilityScore(IterableMultiKeyRBT<Song> songList)
            throws ArithmeticException {
        double sum = 0;
        int numberOfSongs = 0;
        Iterator<Song> iterator = songList.iterator(); // Assuming you have an iterator method
        // for the songList
        while (iterator.hasNext()) {
            Song song = iterator.next();
            sum += song.getDanceabilityScore();
            numberOfSongs++;
        }
        return sum / numberOfSongs;
    }

    /**
     * Retrieves songs from the provided songList with a danceability score above the threshold.
     *
     * @param songList  The collection of songs to filter.
     * @param threshold The minimum danceability score for a song to be included.
     * @return A collection of songs with danceability scores above the threshold.
     */
    @Override
    public IterableMultiKeyRBT<Song> getSongsAboveDanceabilityThreshold(
            IterableMultiKeyRBT<Song> songList, double threshold) {
        IterableMultiKeyRBT<Song> songsAboveThreshold =
                new IterableMultiKeyRBT<>();

        Iterator<Song> iterator = songList.iterator(); // Iterator for the songList

        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getDanceabilityScore() > threshold) {
                songsAboveThreshold.insertSingleKey(song);
            }
        }

        return songsAboveThreshold;
    }

    public static void main(String[] args) throws IOException {
        IterableMultiKeyRBT<Song> songList = new IterableMultiKeyRBT<>();
        BackendClass backend = new BackendClass(songList);
        FrontendClass frontend = new FrontendClass();
        frontend.mainLoop();
    }

}
