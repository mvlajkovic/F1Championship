/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package projekat;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Milica
 */
public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       Label title = new Label();
        title.setText("F1 CHAMPIONSHIP");
        ImageView img = new ImageView(new Image("res/f1.png"));
        img.setFitWidth(300);
        img.setFitHeight(300);
        
        title.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        
        Button btn = new Button("START");
        btn.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:33; -fx-font-weight:bold");

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        
        root.add(img, 0, 0);
        root.add(title, 0, 1);
        root.add(btn, 0, 2);
        
        GridPane.setHalignment(img, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setHalignment(btn, HPos.CENTER);
        
        root.setStyle("-fx-background-color: black");

        
        btn.setOnAction(e -> { 
            primaryStage.close();
           try {
               new HomePage().start(primaryStage);
           } catch (Exception ex) {
               System.out.println("Error loading HomePage");
               ex.printStackTrace();
           }
        });

        Scene scene = new Scene(root, 1200, 800);
        
        primaryStage.setTitle("F1 championship");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
