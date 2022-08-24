/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projekat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.data.Driver;
import projekat.data.Team;
import projekat.io.ChampionsIO;

/**
 *
 * @author Milica
 */
public class Championships extends Application {

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: black");
        Label title = new Label();
        title.setText("F1 CHAMPIONSHIP");
        ImageView img = new ImageView(new Image("res/f1.png"));
        img.setFitWidth(100);
        img.setFitHeight(100);
        title.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        HBox hb = new HBox();
        hb.setStyle("-fx-alignment: center;-fx-spacing: 20;");
        Button btnBack = new Button("Back");
        btnBack.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        hb.getChildren().addAll(img, title, btnBack);
        bp.setTop(hb);


        VBox middle = new VBox();
        Button rounds = new Button("Rounds");
        Button cStandings = new Button("Constructors championship standings");
        Button dStandings = new Button("Drivers championship standings");
        Button cChamp = new Button("Constructors champion");
        Button dChamp = new Button("Drivers champion");

        middle.setStyle("-fx-alignment: top-center; -fx-padding: 20; -fx-spacing: 15;");
        rounds.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        cStandings.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        dStandings.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        cChamp.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        dChamp.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");

        middle.getChildren().addAll(rounds, cStandings, dStandings, cChamp, dChamp);

        bp.setCenter(middle);
        

        Scene scene = new Scene(bp, 1200, 1000);

        

      

        //REST
        primaryStage.setTitle("F1 championship");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnBack.setOnAction(e -> {
            primaryStage.close();
            try {
                new HomePage().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
        });
        cChamp.setOnAction(e -> {
            primaryStage.close();
            try {
                new ConsChampion().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
        });
        dChamp.setOnAction(e -> {
            primaryStage.close();
            try {
                new DriversChampion().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
        });
        rounds.setOnAction(e -> {
            primaryStage.close();
            try {
                new Rounds().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
        });
        dStandings.setOnAction(e -> {
            primaryStage.close();
            try {
                new DriversStandings().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
        });
        cStandings.setOnAction(e -> {
            primaryStage.close();
            try {
                new ConstructorsStandings().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
        });
        
    }

}
