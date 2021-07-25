package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));

            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch(IOException e){
            System.out.println("Problem z utworzeniem okna glownego");
        }


    }


    public static void main(String[] args) {

        launch(args);

    }
}
