package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    //starts the program
    public void start(Stage primaryStage) throws Exception{
        Dimension specs = Toolkit.getDefaultToolkit().getScreenSize();
        double width = specs.getWidth();
        double height = specs.getHeight();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Country Simulation");
        primaryStage.setScene(new Scene(root, width, height-70));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
