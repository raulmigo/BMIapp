/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmiapp;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class BMIapp extends Application {

    private TextField weightTextField = new TextField();
    private TextField heightTextField = new TextField();
    private DecimalFormat df = new DecimalFormat("#,##0.0");
    private Label result = new Label("The body mass index (BMI) is : 0");
    public static final String DEFAULT_OUTPUT_LABEL = "The body mass index (BMI) is : 0";
    public static final double UNDERWEIGHT_BMI = 18.5;
    public static final double OVERWEIGHT_BMI = 25;
    private double weight;
    private double height;
    private double bmi;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label wLabel = new Label("Enter weight (lbs)");
        Label hLabel = new Label("Enter height (inch)");

        Button calculateButton = new Button("Calculate");
        calculateButton.setStyle("-fx-background-color: linear-gradient(#dc9656, #ab4642)");
        calculateButton.setOnAction(event -> {
            evaluateWeight();
            evaluateHeight();
            evaluateBMI();
        });

        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-background-color: linear-gradient(#dc9656, #ab4642)");
        clearButton.setOnAction(event -> {
            clearAllFields();
        });
        
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            System.exit(0);
    });
       
        exitButton.setStyle("-fx-background-color: linear-gradient(#dc9656, #ab4642)");
//        exitButton.setOnAction(CalculateBMI::handle);
//        exitButton.setOnAction

        BorderPane thePane = new BorderPane();
        GridPane gridPane = new GridPane();
        FlowPane flowPane = new FlowPane();

        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(25);
        gridPane.setVgap(15);
        gridPane.add(wLabel, 0, 0, 6, 1);
        gridPane.add(hLabel, 0, 1, 6, 1);
        gridPane.add(weightTextField, 6, 0);
        gridPane.add(heightTextField, 6, 1);
        gridPane.add(result, 0, 2, 10, 1);
        gridPane.autosize();


        flowPane.getChildren().addAll(calculateButton, clearButton, exitButton);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setHgap(20);
        flowPane.setPadding(new Insets(20));

        thePane.setBottom(flowPane);
        thePane.setCenter(gridPane);

        Scene scene = new Scene(thePane, 425, 225);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("BMI");
    }

    public void evaluateBMI() {
        if (weight > 0 && height > 0) {
            setBmi((weight * 703)/(height * height)); // (weight / (height * height) ) * 703;
            if (bmi <= OVERWEIGHT_BMI && bmi >= UNDERWEIGHT_BMI) {
                result.setText("The body mass index (BMI) is : " + df.format(getBmi()) + " (normal weight)");
                result.setTextFill(Color.BLACK);
            } else if (bmi < UNDERWEIGHT_BMI) {
                result.setText("The body mass index (BMI) is : " + df.format(getBmi()) + " (under weight)");
                result.setTextFill(Color.RED);
            } else if (bmi > OVERWEIGHT_BMI) {
                result.setText("The body mass index (BMI) is : " + df.format(getBmi()) + " (over weight)");
                result.setTextFill(Color.RED);
            }
        } else {
            result.setText(DEFAULT_OUTPUT_LABEL);
            result.setTextFill(Color.BLACK);
            setHeight(0);
            setWeight(0);
        }
    }

    public void evaluateHeight() {
        try {
            setHeight(Double.parseDouble(heightTextField.getText()));
            if (height < 1) {
                heightTextField.setText("Height must be > 0");
                result.setText(DEFAULT_OUTPUT_LABEL);
                result.setTextFill(Color.BLACK);
                setHeight(0);
            }
        } catch (NumberFormatException e) {
            heightTextField.setText("Input must be numeric");
            result.setText(DEFAULT_OUTPUT_LABEL);
            result.setTextFill(Color.BLACK);
            setHeight(0);
        }
    }

    public void evaluateWeight() {
        try {
            setWeight(Double.parseDouble(weightTextField.getText()));
            if (weight < 1) {
                weightTextField.setText("Weight must be > 0");
                result.setText(DEFAULT_OUTPUT_LABEL);
                result.setTextFill(Color.BLACK);
                setWeight(0);
            }
        } catch (NumberFormatException e) {
            weightTextField.setText("Input must be numeric");
            result.setText(DEFAULT_OUTPUT_LABEL);
            result.setTextFill(Color.BLACK);
            setWeight(0);
        }
    }

    public void clearAllFields() {
        setHeight(0);
        setWeight(0);
        setBmi(0);
        weightTextField.setText("");
        heightTextField.setText("");
        result.setText(DEFAULT_OUTPUT_LABEL);
        result.setTextFill(Color.BLACK);
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    private static void handle(ActionEvent event) {
        Platform.exit();
    }

}