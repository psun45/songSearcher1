/**
 * The `Song` class represents a musical composition.
 * It implements the `SongInterface` to provide necessary information about a song.
 */
public class Song implements SongInterface{

    // Private attributes to store information about the song
    private String artist;       // The artist or band who performed the song
    private String title;        // The title of the song
    private int year;            // The year the song was released
    private String genre;       // The genre of the song
    private double danceability; // A measure of how suitable the song is for dancing

    /**
     * Constructs a new `Song` object with specified attributes.
     * @param artist The artist or band who performed the song.
     * @param title The title of the song.
     * @param year The year the song was released.
     * @param genre The genre of the song.
     * @param danceability A measure of how suitable the song is for dancing.
     */
    public Song(String artist, String title, int year, String genre, double danceability) {
        this.artist = artist;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.danceability = danceability;
    }

    /**
     * Gets the artist of the song.
     * @return The artist of the song.
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * Gets the title of the song.
     * @return The title of the song.
     */
    public String getSongTitle() {
        return this.title;
    }

    /**
     * Gets the release year of the song.
     * @return The year the song was released.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets the genre of the song.
     * @return The genre of the song.
     */
    public String getGenre() {
        return this.genre;
    }

    /**
     * Gets the danceability score of the song.
     * @return The danceability score of the song.
     */
    public double getDanceabilityScore() {
        return this.danceability;
    }

    /**
     * Compares the danceability score of this song with another song.
     * @param o The song to compare with.
     * @return 1 if this song has a higher danceability score,
     *        -1 if this song has a lower danceability score,
     *         0 if both songs have the same danceability score.
     */
    public int compareTo(Song o) {
        if(this.danceability > o.danceability) {
            return 1;
        }
        else if(this.danceability < o.danceability) {
            return -1;
        }
        else {
            return 0;
        }
    }
}