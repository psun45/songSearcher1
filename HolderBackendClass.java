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
 * Define an interface for the Backend functionality which reads data from a file, calculates the
 * Average Dancability score, and gets a list of songs over a certain danceability threshold.
 */
public class HolderBackendClass implements BackendInterface {
  /**
   * Constructs a BackendConstructor instance with an initial list of songs. Constructor: public
   * BackendConstructor(List<Song> songList)
   * 
   * @param songList The initial list of songs to populate the backend.
   */
  public HolderBackendClass() {
  }

  /**
   * method that reads data from file
   * 
   * @param filePath
   */
  public IterableMultiKeyRBT<Song> dataFromFileReader(String filePath) {
    IterableMultiKeyRBT<Song> loadedPlaylist = null;
    return loadedPlaylist;

  }

  /**
   * Calculates and returns the average danceability score of all songs in the backend.
   * 
   * @param songList
   * @return The average danceability score.
   */
  public static double calculateAverageDanceabilityScore(List<Song> songList) {
    return 50.0;
  }

  /**
   * Getter method for list of songs above dancability threshold
   * 
   * @param threshold
   * @return List of songs above dancability Threshold
   */
  public static List<Song> getSongsAboveDanceabilityThreshold(List<Song> songList,
      double threshold) {
    List<Song> returnList = new ArrayList<Song>();
    return returnList;
  }

  @Override
  public double calculateAverageDanceabilityScore(IterableMultiKeyRBT<Song> songList) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public IterableMultiKeyRBT<Song> getSongsAboveDanceabilityThreshold(
      IterableMultiKeyRBT<Song> songList, double threshold) {
    // TODO Auto-generated method stub
    return null;
  }


}
