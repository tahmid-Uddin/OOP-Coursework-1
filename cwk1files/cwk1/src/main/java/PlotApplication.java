/**
 * JavaFX application to plot elevations of a GPS track, for the
 * Advanced task of COMP1721 Coursework 1.
 *
 * @author Tahmid Fahim Uddin
 */

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.chart.LineChart; 
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.XYChart; 
import java.io.IOException;


public class PlotApplication extends javafx.application.Application {

  // If attempting the Advanced task, implement your plotting code here.
  // You will need to modify this class definition so that it extends
  // javafx.application.Application

  private static Track track;
  private static Double[] elevationList;
  private static Double[] distanceList;
  private static String fileName;

  @Override 
   public void start(Stage stage) {

      // Initialising X and Y axis
      NumberAxis yAxis = new NumberAxis(0, Math.round(track.highestPoint().getElevation()) + 5, 5); 
      yAxis.setLabel("Elevation (m)"); 
         
      NumberAxis xAxis = new NumberAxis(0, Math.round(track.totalDistance()) + 50, 200); 
      xAxis.setLabel("Distance (m)"); 

      XYChart.Series series = new XYChart.Series(); 
      series.setName(fileName); 

      // Adding x and y values to the
      double x; double y = 0;
      series.getData().add(new XYChart.Data(y, track.get(0).getElevation()));

      for (int i = 1; i < track.size(); i++) { 
        x = track.get(i).getElevation(); 
        y = y + Point.greatCircleDistance(track.get(i-1), track.get(i));

        series.getData().add(new XYChart.Data(y, x));
      }

      // Creates the linechart and draws it into a new window.
      LineChart linechart = new LineChart(xAxis, yAxis); 
      linechart.getData().add(series);        
      Group root = new Group(linechart); 
      Scene scene = new Scene(root, 600, 400);  
      stage.setTitle("Elevation Plot"); 
      stage.setScene(scene);
      stage.show();         
   } 

  public static void main(String[] args) {
    // If attempting the Advanced task, uncomment the line below
    try {
      track = new Track(args[0]);
      elevationList = new Double[track.size()];
      distanceList = new Double[track.size()];
      fileName = args[0];
    }

    catch (Exception e) {
      System.exit(0);
    }
    
    launch(args);
  }
}
