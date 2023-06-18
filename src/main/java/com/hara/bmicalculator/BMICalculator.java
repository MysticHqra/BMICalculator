package com.hara.bmicalculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class BMICalculator extends Application {
    Label l1;
    Label l2;
    Label l3;
    Label l4;
    TextField tf1;
    TextField tf2;
    ComboBox cb;
    ToggleGroup tg;
    RadioButton rb1;
    RadioButton rb2;

    @Override
    public void start(Stage stage) throws Exception {
        Stage primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane);


        l1 = new Label("Weight (lb): ");
        tf1 = new TextField();

        l2 = new Label("Height (inches): ");
        tf2 = new TextField();

        l3 = new Label("Age");
        cb = new ComboBox();
        cb.getItems().add("2-17");
        cb.getItems().add("18-30");
        cb.getItems().add("31-50");
        cb.getItems().add("51-100");

        l4 = new Label("Gender: ");
        tg = new ToggleGroup();
        rb1 = new RadioButton("Male");
        rb2 = new RadioButton("Female");
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);

        Button b1 = new Button("Calculate");
        Button b2 = new Button("Clear");

        gridPane.add(l1, 0, 0);
        gridPane.add(tf1, 1, 0);
        gridPane.add(l2, 0, 1);
        gridPane.add(tf2, 1, 1);
        gridPane.add(l3, 0, 2);
        gridPane.add(cb, 1, 2);
        gridPane.add(l4, 0, 3);
        gridPane.add(rb1, 1, 3);
        gridPane.add(rb2, 2, 3);
        gridPane.add(b1, 0, 4);
        gridPane.add(b2, 1, 4);

        b1.setOnAction(action -> calculateBMI());
        b2.setOnAction(action -> clearFields());


        stage.setTitle("BMI Calculator");
        stage.setScene(scene);
        stage.show();

    }

    public void calculateBMI() {
        double weight = 0;
        double height = 0;
        int age = 0;
        String category = "";
        boolean flag = true;
        try {
            weight = Double.parseDouble(tf1.getText());
            height = Double.parseDouble(tf2.getText());

            if (cb.getSelectionModel().getSelectedItem().equals("2-17")) {
                age = 1;
            } else if (cb.getSelectionModel().getSelectedItem().equals("18-30")) {
                age = 2;
            } else if (cb.getSelectionModel().getSelectedItem().equals("31-50")) {
                age = 3;
            } else if (cb.getSelectionModel().getSelectedItem().equals("51-100")) {
                age = 4;
            } else {
                age = 0;
            }
        } catch (Exception e) {
            flag = false;
            Alert alertBox = new Alert(Alert.AlertType.ERROR);
            alertBox.setTitle("Invalid Values");
            alertBox.setContentText("Please provide a legal value");
            alertBox.show();
        }

        double bmi = (weight / (height * height)) * 703;

        if (bmi >= 16.0) {
            if (bmi >= 16.0 && bmi <= 18.4) {
                category = "Underweight";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                category = "Normal";
            } else if (bmi >= 25.0 && bmi <= 29.9) {
                category = "Overweight";
            } else {
                category = "Obese";
            }
        } else {
            category = "Critical";
        }
        if (flag) {
            Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
            alertBox.setContentText("Your BMI is :" + bmi + "\nCategory: " + category);
            alertBox.show();
        }
    }

    public void clearFields() {
        tf1.setText("");
        tf2.setText("");
        cb.getSelectionModel().clearSelection();
        rb1.setSelected(false);
        rb2.setSelected(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}