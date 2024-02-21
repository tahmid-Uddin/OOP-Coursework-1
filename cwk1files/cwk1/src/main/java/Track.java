/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Tahmid Fahim Uddin
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
 


public class Track {
  // TODO: Create a stub for the constructor
  private List<Point> pointsList = new ArrayList<Point>();

  public Track() {
    
  }

  public Track(String filename) throws IOException {
    readFile(filename);
  }

  // TODO: Create a stub for readFile()
  public void readFile(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    BufferedReader input = Files.newBufferedReader(path);

    String line = input.readLine();
    line = input.readLine();

    while (line != null) {

      if (line.split(",").length != 4) {
        throw new GPSException("Invalid number of variables");
      }
      Point point = new Point(ZonedDateTime.parse(line.split(",")[0]), Double.parseDouble(line.split(",")[1]), Double.parseDouble(line.split(",")[2]), Double.parseDouble(line.split(",")[3]));
      pointsList.add(point);
      line = input.readLine();
    }

    input.close();
  }

  // TODO: Create a stub for add()
  public void add(Point point) {

  }

  // TODO: Create a stub for get()
  public Point get(int index) {
    if (index > pointsList.size() || index < 0) {
      throw new GPSException("Invalid index");
    }
    return pointsList.get(index);
  }

  // TODO: Create a stub for size()
  public int size() {
    return pointsList.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() { 
    return null;
  }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint() { 
    return null;
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance() {
    return 0.0;
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
    return 0.0;
  }

}
