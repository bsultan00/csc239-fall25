/*
 *  name: beftu sultan
 *  due date: 12 nov 2025
 *  description: a javafx calculator application performing basic operations
 *  allowing user to input floating point values.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Lab15a extends Application {
    // calculator elements
    private Label lNum1 = new Label(" Number 1: ");
    private Label lNum2 = new Label(" Number 2: ");
    private Label lResult = new Label(" Result: ");

    private TextField tfNum1 = new TextField("");
    private TextField tfNum2 = new TextField("");
    private TextField tfResult = new TextField("");

    private Button btAdd = new Button("Add");
    private Button btSub = new Button("Subtract");
    private Button btMultiply = new Button("Multiply");
    private Button btDiv = new Button("Divide");

    @Override
    public void start (Stage primaryStage) {

        // ui setup
        FlowPane flowPane = new FlowPane();
        HBox hBox = new HBox();
        flowPane.getChildren().addAll(lNum1, tfNum1, lNum2, tfNum2, lResult, tfResult);
        hBox.getChildren().addAll(btAdd, btSub, btMultiply, btDiv);
        
        // ui properties
        flowPane.setAlignment(Pos.TOP_LEFT);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        tfNum1.setPrefSize(80, 25);
        tfNum2.setPrefSize(80, 25);

        // process events (add, subtract, multiply, divide)
        btAdd.setOnAction(e -> {
            tfResult.setText("" + calculateAddition(tfNum1, tfNum2));
        });

        btSub.setOnAction(e -> {
            tfResult.setText("" + calculateSubtraction(tfNum1, tfNum2));
        });

        btMultiply.setOnAction(e -> {
            tfResult.setText("" + calculateMultiplication(tfNum1, tfNum2));
        });

        btDiv.setOnAction(e -> {
            tfResult.setText("" + calculateDivision(tfNum1, tfNum2));
        });

        // overall display
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(flowPane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 600, 100);
        primaryStage.setTitle("Lab15a");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // perform calculating operations
    public static double calculateAddition(TextField tfNum1, TextField tfNum2) {
        return Double.parseDouble(tfNum1.getText()) + Double.parseDouble(tfNum2.getText());
    }

    public static double calculateSubtraction(TextField tfNum1, TextField tfNum2) {
        return Double.parseDouble(tfNum1.getText()) - Double.parseDouble(tfNum2.getText());
    }

    public static double calculateMultiplication(TextField tfNum1, TextField tfNum2) {
        return Double.parseDouble(tfNum1.getText()) * Double.parseDouble(tfNum2.getText());
    }

    public static double calculateDivision(TextField tfNum1, TextField tfNum2) {
        return Double.parseDouble(tfNum1.getText()) / Double.parseDouble(tfNum2.getText());
    }

    public static void main(String[] args) {
        launch(args);
    }
}