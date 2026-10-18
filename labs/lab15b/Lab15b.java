/*
 * name: beftu sultan
 * due date: 24 nov. 2025
 * description: allow user to add/remove points on a javaFX display. add point
 * using left click (primary mouse button) and remove point by using right click
 * (secondary mouse button).
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Lab15b extends Application {

    @Override // override start method in application class
    public void start (Stage primaryStage) {
        System.out.println("Lab15b execution started.");

        // create a pane
        Pane pane = new Pane();
        primaryStage.setTitle("Point Lab: Lab15a");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

        // for every mouse event
        pane.setOnMouseClicked(e -> {
            Circle point = new Circle();

            // coordinates
            double xPoint = e.getX();
            double yPoint = e.getY();

            if (e.getButton() == MouseButton.PRIMARY){ // when use left clicks

                System.out.println("Left Click: adding point at x = "+ e.getX() + ", y = " + e.getY());

                // add point (circle object) to list
                point = addPoint(xPoint, yPoint);
                pane.getChildren().add(point);
            } else if (e.getButton() == MouseButton.SECONDARY) { // when use right clicks
                System.out.println("Right Click: at mouse position x = "+ e.getX() + ", y = " + e.getY());

                // remove point from list
                removePoint(pane, e.getX(), e.getY());
            };
        });
    }

    // takes coordinates of left click and create circle object to display point
    public Circle addPoint(double xPoint, double yPoint) {
        Circle point = new Circle(xPoint, yPoint, 5);
        point.setFill(Color.ROSYBROWN);

        return point;
    }

    // takes coordinates of right click and check if circle exists there
    public void removePoint(Pane pane, double xPoint, double yPoint) {
        ObservableList<Node> list = pane.getChildren();

        // loop through list to find a node with matching coordinates, if
        // matching node is found remove it from display
        for (int i = 0; i < list.size(); i++) {
            Node nodeFromList = list.get(i);

            if (nodeFromList.contains(xPoint, yPoint)) {
                pane.getChildren().remove(nodeFromList);
                System.out.println("    Removing node " + i + " at x = " + xPoint + ", y = " + yPoint);
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
      }
}