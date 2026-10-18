//   CSC239:   Lab14a - Beginning JavaFX
//
//   This "starter program" creates a JavaFX
//   application and draws a rectangle.
//   The Lab14a assignment is to enhance this
//   program so that the image on the screen 
//   looks like a 3-dimensional rectangular
//   object.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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

        // new rectangle
        Rectangle r2 = new Rectangle(cornerX + 25, cornerY + 25, width, height);
        r2.setFill(new Color(1, 1, 1, 0));
        r2.setStroke(Color.BLACK);

        // create 4 lines to connect both rectangles
        Line lineUpperLeft = new Line(cornerX, cornerY, cornerX + 25, cornerY + 25);
        Line lineUpperRight = new Line(cornerX + width, cornerY, cornerX + width + 25, cornerY + 25);
        Line lineLowerLeft = new Line(cornerX, cornerY + height, cornerX + 25, cornerY + height + 25);
        Line lineLowerRight = new Line(cornerX + width, cornerY + height, cornerX + width + 25, cornerY + height + 25);

        pane.getChildren().add(r1); 
        pane.getChildren().add(r2);
        pane.getChildren().add(lineUpperLeft);
        pane.getChildren().add(lineUpperRight);
        pane.getChildren().add(lineLowerLeft);
        pane.getChildren().add(lineLowerRight);
        Scene scene = new Scene(pane, paneWidth, paneHeight);

        primaryStage.setTitle("Lab14a");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
