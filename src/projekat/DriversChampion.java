/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.data.Driver;
import projekat.data.Team;
import projekat.data.Track;
import projekat.data.TrackDirection;
import projekat.data.TrackType;
import projekat.io.ChampionsIO;
import projekat.io.TrackIO;

/**
 *
 * @author Milica
 */
public class DriversChampion extends Application {

    int season;
    String tmpStringDriver = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane bp3 = new BorderPane();
        bp3.setStyle("-fx-background-color: black");
        Label title3 = new Label();
        title3.setText("F1 CHAMPIONSHIP");
        ImageView img3 = new ImageView(new Image("res/f1.png"));
        img3.setFitWidth(100);
        img3.setFitHeight(100);
        title3.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        HBox hb3 = new HBox();
        hb3.setStyle("-fx-alignment: center;-fx-spacing: 20;");
        Button btnBack3 = new Button("Back");
        btnBack3.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        hb3.getChildren().addAll(img3, title3, btnBack3);
        bp3.setTop(hb3);

        Label update = new Label("Enter season");
        Label lblseason = new Label("Season: ");
        TextField tfseason = new TextField();
        tfseason.setAlignment(Pos.CENTER);
        Button btnEnter = new Button();
        btnEnter.setText("Enter");

        btnEnter.setStyle("-fx-background-color: #ff0100;-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        update.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblseason.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");

        VBox middle2 = new VBox();
        middle2.setStyle("-fx-alignment: top-center; -fx-padding: 20; -fx-spacing: 15;");
        middle2.getChildren().addAll(update, lblseason, tfseason, btnEnter);
        bp3.setCenter(middle2);

        Label lblChamp3 = new Label("Champ for season " + season);
        System.out.println(season);

        Label champ3 = new Label(tmpStringDriver);
        lblChamp3.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        champ3.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        VBox middle3 = new VBox();
        middle3.setStyle("-fx-alignment: top-center; -fx-padding: 20; -fx-spacing: 15;");
        middle3.getChildren().addAll(lblChamp3, champ3);
        //bp3.setCenter(middle3);

        Scene scene3 = new Scene(bp3, 1200, 1000);

        primaryStage.setTitle("F1 championship");
        primaryStage.setScene(scene3);
        primaryStage.show();

        btnBack3.setOnAction(e -> {
            primaryStage.close();
            try {
                new Championships().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
        });

        btnEnter.setOnAction(e -> {
            String tmp = tfseason.getText();
            System.out.println(tmp);
            season = Integer.parseInt(tmp);
            System.out.println(season);
            Driver tmpDriver = ChampionsIO.loadDriversChamp(season);
            System.out.println(tmpDriver.toString());
            if (tmpDriver.getSLN() == 0) {
                tmpStringDriver = "No champion";
            } else {
                tmpStringDriver = tmpDriver.toString();
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lblChamp3.setText("Champ for season " + season);
                    champ3.setText(tmpStringDriver);
                    bp3.setCenter(middle3);
                }
            });
        });

        tfseason.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfseason.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

}
