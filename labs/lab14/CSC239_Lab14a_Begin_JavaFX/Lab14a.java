//   CSC239:   Lab14a - Beginning JavaFX
//
//   This "starter program" creates a JavaFX
//   application and draws a rectangle.
//   The Lab14a assignment is to enhance this
//   program so that the image on the screen 
//   looks like a 3-dimensional rectangular
//   object.

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Lab14a extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        double paneWidth = 400;
        double paneHeight = 300;
        double width = paneWidth * 0.90 - 30;   // rectangle width
        double height = paneHeight * 0.90 - 60; // rectangle height
        double cornerX = 10;                // rectangle corner X
        double cornerY = 60;                // rectangle corner Y
 
        // Draw a rectangle:  
        //      The "upper-left" corner is at cornerX, cornerY.
        //      The other corner positions can be calculated,
        //      based on cornerX, cornerY, width, and height.
        Rectangle r1 = new Rectangle(cornerX, cornerY, width, height);
        r1.setFill(new Color(1, 1, 1, 0));  // fill color = white
        r1.setStroke(Color.BLACK);          // edge color = black
    
        pane.getChildren().add(r1); 
        Scene scene = new Scene(pane, paneWidth, paneHeight);
        primaryStage.setTitle("Lab14a");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
