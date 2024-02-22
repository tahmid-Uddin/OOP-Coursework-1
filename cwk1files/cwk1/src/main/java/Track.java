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
import java.time.temporal.ChronoUnit;
 


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
    pointsList.clear();

    Path path = Paths.get(fileName);
    BufferedReader input = Files.newBufferedReader(path);
    
    String line = input.readLine();
    line = input.readLine();

    while (line != null) {

      if (line.split(",").length != 4) {
        input.close();
        throw new GPSException("Invalid number of variables");
      }

      Point point = new Point(ZonedDateTime.parse(line.split(",")[0]), Double.parseDouble(line.split(",")[1]), Double.parseDouble(line.split(",")[2]), Double.parseDouble(line.split(",")[3]));
      add(point);
      line = input.readLine();
    }

    input.close();
  }

  // TODO: Create a stub for add()
  public void add(Point point) {
    pointsList.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index) {
    if (index > pointsList.size() - 1 || index < 0) {
      throw new GPSException("Invalid index");
    }

    if (pointsList.size() == 0) {
      throw new GPSException("Empty List");
    }

    return pointsList.get(index);
  }

  // TODO: Create a stub for size()
  public int size() {
    return pointsList.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() { 
    if (pointsList.size() == 0) {
      throw new GPSException("Empty List");
    }
    
    double lowestelevation = get(0).getElevation();
    Point lowesPoint = get(0);

    for (int i = 1; i < pointsList.size(); i++) {
      if (get(i).getElevation() < lowestelevation) {
        lowestelevation = get(i).getElevation();
        lowesPoint = get(i);
      }
    }

    return lowesPoint;
  }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint() {
    if (pointsList.size() == 0) {
      throw new GPSException("Empty List");
    } 

    double highestelevation = get(0).getElevation();
    Point highestPoint = get(0);

    for (int i = 1; i < pointsList.size(); i++) {
      if (get(i).getElevation() > highestelevation) {
        highestelevation = get(i).getElevation();
        highestPoint = get(i);
      }
    }

    return highestPoint;
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance() {
    if (pointsList.size() < 2) {
      throw new GPSException("Empty List");
    }
    double distance = Point.greatCircleDistance(pointsList.get(0), pointsList.get(1));

    for (int i = 1; i < pointsList.size() - 1; i++) {
      distance = distance + Point.greatCircleDistance(pointsList.get(i), pointsList.get(i + 1));
    }

    return distance;
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
    if (pointsList.size() < 2) {
      throw new GPSException("Empty List");
    }

    double totalDistance = totalDistance();
    double totalTime = ChronoUnit.SECONDS.between(pointsList.get(0).getTime(), pointsList.get(1).getTime());

    for (int i = 1; i < pointsList.size() - 1; i++) {
      totalTime = totalTime + ChronoUnit.SECONDS.between(pointsList.get(i).getTime(), pointsList.get(i + 1).getTime());
    }
    return totalDistance / totalTime;

  }
}
