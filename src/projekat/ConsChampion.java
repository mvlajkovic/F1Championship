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
import projekat.data.Team;
import projekat.io.ChampionsIO;

/**
 *
 * @author Milica
 */
public class ConsChampion extends Application {

    int season = 0;
    String tmpStringTeam = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane bp2 = new BorderPane();
        bp2.setStyle("-fx-background-color: black");
        Label title2 = new Label();
        title2.setText("F1 CHAMPIONSHIP");
        ImageView img2 = new ImageView(new Image("res/f1.png"));
        img2.setFitWidth(100);
        img2.setFitHeight(100);
        title2.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        HBox hb2 = new HBox();
        hb2.setStyle("-fx-alignment: center;-fx-spacing: 20;");
        Button btnBack2 = new Button("Back");
        btnBack2.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        hb2.getChildren().addAll(img2, title2, btnBack2);
        bp2.setTop(hb2);

        Label update = new Label("Enter season");
        Label lblseason = new Label("Season: ");
        TextField tfseason = new TextField();
        tfseason.setAlignment(Pos.BASELINE_RIGHT);
        Button btnEnter = new Button();
        btnEnter.setText("Enter");

        btnEnter.setStyle("\"-fx-background-color: #ff0100;-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        update.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblseason.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");

        VBox middle = new VBox();
        middle.setStyle("-fx-alignment: top-center; -fx-padding: 20; -fx-spacing: 15;");
        middle.getChildren().addAll(update, lblseason, tfseason, btnEnter);
        bp2.setCenter(middle);

        Label lblChamp2 = new Label("Champ for season " + season);
        System.out.println(season);

        Label champ2 = new Label(tmpStringTeam);
        lblChamp2.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        champ2.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        VBox middle2 = new VBox();
        middle2.setStyle("-fx-alignment: top-center; -fx-padding: 20; -fx-spacing: 15;");
        middle2.getChildren().addAll(lblChamp2, champ2);
        //bp2.setCenter(middle2);

        Scene scene2 = new Scene(bp2, 1200, 1000);
        primaryStage.setTitle("F1 championship");
        primaryStage.setScene(scene2);
        primaryStage.show();

        btnBack2.setOnAction(e -> {
            primaryStage.close();
            try {
                new Championships().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
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

        btnEnter.setOnAction(e -> {
            String tmp = tfseason.getText();
            System.out.println(tmp);
            season = Integer.parseInt(tmp);
            System.out.println(season);
            Team tmpTeam = ChampionsIO.loadConsChamp(season);
            if (tmpTeam.getTeamCode() == 0) {
                tmpStringTeam = "No champion";
            } else {
                tmpStringTeam = tmpTeam.toString();
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lblChamp2.setText("Champ for season " + season);
                    champ2.setText(tmpStringTeam);
                    bp2.setCenter(middle2);
                }
            });
        });
    }

}
