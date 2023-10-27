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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Define an interface for the Backend functionality which reads data from a file,
 *             calculates the Average Dancability score, and gets a list of songs over a certain
 *             danceability threshold.
 */
public class BackendClass implements BackendInterface {
        /**
         * Constructs a BackendConstructor instance with an initial list of songs.
         * Constructor: public BackendConstructor(List<Song> songList)
         * @param songList The initial list of songs to populate the backend.
         */

        /**
         * method that reads data from file
         * @param filePath
         */
        public static List<Song> dataFromFileReader(String filePath) {
          List<Song> loadedPlaylist = new ArrayList<Song>();
          return loadedPlaylist;
          
        }
        /**
         * Calculates and returns the average danceability score of all songs in the backend.
         * @param songList
         * @return The average danceability score.
         */
        public static double calculateAverageDanceabilityScore(List<Song> songList) {
          return 50.0;
        }

        /**
         * Getter method for list of songs above dancability threshold
         * @param threshold
         * @return List of songs above dancability Threshold
         */
        public static List<Song> getSongsAboveDanceabilityThreshold(List<Song> songList, double threshold){
          List<Song> returnList = new ArrayList<Song>();
          return returnList;
        }
        @Override
        public boolean insertSingleKey(Comparable key) {
          // TODO Auto-generated method stub
          return false;
        }
        @Override
        public int numKeys() {
          // TODO Auto-generated method stub
          return 0;
        }
        @Override
        public Iterator iterator() {
          // TODO Auto-generated method stub
          return null;
        }
        @Override
        public void setIterationStartPoint(Comparable startPoint) {
          // TODO Auto-generated method stub
          
        }
        @Override
        public boolean insert(Comparable data)
            throws NullPointerException, IllegalArgumentException {
          // TODO Auto-generated method stub
          return false;
        }
        @Override
        public boolean contains(Comparable data) {
          // TODO Auto-generated method stub
          return false;
        }
        @Override
        public int size() {
          // TODO Auto-generated method stub
          return 0;
        }
        @Override
        public boolean isEmpty() {
          // TODO Auto-generated method stub
          return false;
        }
        @Override
        public void clear() {
          // TODO Auto-generated method stub
          
        }


}