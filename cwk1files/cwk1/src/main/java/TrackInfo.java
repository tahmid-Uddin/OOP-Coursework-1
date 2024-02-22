/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Tahmid Fahim Uddin
 */

import java.io.IOException;

public class TrackInfo {
  public static void main(String[] args) {
    // TODO: Implementation TrackInfo application here
    if (args.length == 0) {
      System.out.println("No filename has been provided.");
      System.exit(0);
    }

    try {
      Track track = new Track(args[0]);
      System.out.println(String.format("%d points in track", track.size()));
      System.out.println(String.format("Lowest point is %s", track.lowestPoint().toString()));
      System.out.println(String.format("Highest point is %s", track.highestPoint().toString()));
      System.out.println(String.format("Total distance = %.3f km", track.totalDistance() / 1000.0));
      System.out.println(String.format("Average speed = %.3f m/s", track.averageSpeed()));
    }

    catch (Exception e) { 
      System.out.println(e);
      System.exit(1);
    }
  }
}