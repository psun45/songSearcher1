//////////////////////////////// FILE HEADER //////////////////////////////////
//
// Title: Backend Interface for Song Searcher App
// Course: CS 400 Fall 2023
//
// Authors: Kinhkha Tran, Matthew Gibson, Patrick Sun
// Emails: Ktran33@wisc.edu, Mrgibson4@wisc.edu, Psun45@wisc.edu
// Lecturer: Gary Dahl
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.List;

/**
 * Define an interface for the Backend functionality which reads data from a file,
 *             calculates the Average Dancability score, and gets a list of songs over a certain
 *             danceability threshold.
 */
public interface BackendInterface extends IterableMultiKeySortedCollectionInterface {
        /**
         * Constructs a BackendConstructor instance with an initial list of songs.
         * Constructor: public BackendConstructor(List<Song> songList)
         * @param songList The initial list of songs to populate the backend.
         */

        /**
         * method that reads data from file
         * @param filePath
         * @return 
         */
        static List<Song> dataFromFileReader(String filePath) {
          return null;
                  }
        
        /**
         * Calculates and returns the average danceability score of all songs in the backend.
         * @param songList
         * @return The average danceability score.
         */
        static double calculateAverageDanceabilityScore(List<Song> songList) {
          return 0;
        }

        /**
         * Getter method for list of songs above dancability threshold
         * @param threshold
         * @return List of songs above dancability Threshold
         */
        static List<Song> getSongsAboveDanceabilityThreshold(List<Song> songList, double threshold) {
          return null;
        }


}
