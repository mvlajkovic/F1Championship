/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projekat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Milica
 */
public class HomePage extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        HBox hb = new HBox();
        
        Button drivers = new Button("DRIVERS");
        drivers.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:33; -fx-font-weight:bold");
        Button teams = new Button("TEAMS");
        teams.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:33; -fx-font-weight:bold");
        
        Button tracks = new Button("TRACKS");
        tracks.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:33; -fx-font-weight:bold");
               
        Button championship = new Button("CHAMPIONSHIP");
        championship.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:33; -fx-font-weight:bold");
        
        Button news = new Button("LATEST NEWS");
        news.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:33; -fx-font-weight:bold");
        Button calendar = new Button("REAL CALENDAR");
        calendar.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:33; -fx-font-weight:bold");
        Button technical = new Button("TECHNICAL");
        technical.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:33; -fx-font-weight:bold");
                
        bp.setStyle("-fx-background-color: black");
        
        Label title = new Label();
        title.setText("F1 CHAMPIONSHIP");
        ImageView img = new ImageView(new Image("res/f1.png"));
        img.setFitWidth(300);
        img.setFitHeight(300);
        
        title.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        
        hb.getChildren().addAll(img, title);
        bp.setTop(hb);
        gp.add(drivers, 0, 0);
        gp.add(teams, 0, 1);
        gp.add(tracks, 0, 2);
        gp.add(championship, 1, 0);
        gp.add(news, 2, 0);
        gp.add(calendar, 2 , 1);
        gp.add(technical, 2 , 2);
        bp.setCenter(gp);
        
        hb.setStyle("-fx-alignment: center; -fx-padding: 20");
        gp.setStyle("-fx-hgap:20; -fx-vgap:20; -fx-alignment: center; -fx-padding: 20;");
        Scene scene = new Scene(bp, 1200, 800);
        
        primaryStage.setTitle("F1 championship");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        
        drivers.setOnAction(e -> { 
            primaryStage.close();
           try {
               new Drivers().start(primaryStage);
           } catch (Exception ex) {
               System.out.println("Error loading Drivers page");
               ex.printStackTrace();
           }
        });
        
        teams.setOnAction(e -> { 
            primaryStage.close();
           try {
               new Teams().start(primaryStage);
           } catch (Exception ex) {
               System.out.println("Error loading Teams page");
               ex.printStackTrace();
           }
        });
        technical.setOnAction(e -> { 
            primaryStage.close();
           try {
               new Techical().start(primaryStage);
           } catch (Exception ex) {
               System.out.println("Error loading Teams page");
               ex.printStackTrace();
           }
        });
        
        news.setOnAction(e -> { 
            primaryStage.close();
           try {
               new LatestNews().start(primaryStage);
           } catch (Exception ex) {
               System.out.println("Error loading Teams page");
               ex.printStackTrace();
           }
        });
        
        calendar.setOnAction(e -> { 
            primaryStage.close();
           try {
               new Calendar().start(primaryStage);
           } catch (Exception ex) {
               System.out.println("Error loading Teams page");
               ex.printStackTrace();
           }
        });
        tracks.setOnAction(e -> { 
            primaryStage.close();
           try {
               new Tracks().start(primaryStage);
           } catch (Exception ex) {
               System.out.println("Error loading Teams page");
               ex.printStackTrace();
           }
        });
        championship.setOnAction(e -> { 
            primaryStage.close();
           try {
               new Championships().start(primaryStage);
           } catch (Exception ex) {
               System.out.println("Error loading Teams page");
               ex.printStackTrace();
           }
        });

    }
    
}
