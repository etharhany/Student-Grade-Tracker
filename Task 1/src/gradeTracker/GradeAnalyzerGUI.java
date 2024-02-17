package gradeTracker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GradeAnalyzerGUI extends Application {

    private TextField numberOfStudentsField;
    private TextField gradesTextArea;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grade Analyzer");

        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(20));
        mainVBox.setSpacing(20);

        Label titleLabel = new Label("Grade Analyzer");
        titleLabel.setStyle("-fx-font-size: 52; -fx-font-weight: bold;");

        HBox inputHBox = new HBox(10);
        inputHBox.setAlignment(Pos.CENTER);
        numberOfStudentsField = new TextField();
        numberOfStudentsField.setAlignment(Pos.CENTER);
        numberOfStudentsField.setPromptText("Number of Students");
        numberOfStudentsField.setPrefWidth(200);
        gradesTextArea = new TextField();
        gradesTextArea.setAlignment(Pos.CENTER);
        gradesTextArea.setPromptText("Enter grades separated by space");
        gradesTextArea.setPrefWidth(300);
        gradesTextArea.setPrefHeight(70);
        inputHBox.getChildren().addAll(numberOfStudentsField, gradesTextArea);

        Button analyzeButton = new Button("Analyze Grades");
        analyzeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        analyzeButton.setOnAction(e -> analyzeGrades());

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white;");
        resetButton.setOnAction(e -> resetFields());

        resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        mainVBox.getChildren().addAll(titleLabel, inputHBox, analyzeButton, resetButton, resultLabel);

        Scene scene = new Scene(mainVBox, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    private void analyzeGrades() {
        try {
            int numberOfStudents = Integer.parseInt(numberOfStudentsField.getText());
            String[] gradeStrings = gradesTextArea.getText().split("\\s+");
            int[] grades = new int[numberOfStudents];

            for (int i = 0; i < numberOfStudents; i++) {
                grades[i] = Integer.parseInt(gradeStrings[i]);
            }

            double average = calculateAverage(grades);
            int highest = findHighest(grades);
            int lowest = findLowest(grades);

            resultLabel.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold;");
            resultLabel.setText("Average Score: " + average +
                    "\nHighest Score: " + highest +
                    "\nLowest Score: " + lowest);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            resultLabel.setText("Invalid input. Please enter the correct number of students and grades.");
            resultLabel.setStyle("-fx-text-fill: #d9534f; -fx-font-weight: bold;");
            
        }
    }

    private double calculateAverage(int[] arr) {
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return (double) sum / arr.length;
    }

    private int findHighest(int[] arr) {
        int highest = arr[0];
        for (int val : arr) {
            if (val > highest) {
                highest = val;
            }
        }
        return highest;
    }

    private int findLowest(int[] arr) {
        int lowest = arr[0];
        for (int val : arr) {
            if (val < lowest) {
                lowest = val;
            }
        }
        return lowest;
    }

    private void resetFields() {
        numberOfStudentsField.clear();
        gradesTextArea.clear();
        resultLabel.setText("");
    }
}
