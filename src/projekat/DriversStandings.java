/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.data.Driver;
import projekat.data.DriverChampionship;
import projekat.data.Round;
import projekat.data.Team;
import projekat.io.ChampionsIO;
import projekat.io.DriverIO;
import projekat.io.RoundIO;
import projekat.io.TeamIO;
import projekat.util.RoundUtil;

/**
 *
 * @author Milica
 */
public class DriversStandings extends Application {

    List<Round> champRounds = new ArrayList<>();
    DriverChampionship dc;
    List<Driver> driverList = DriverIO.loadDrivers();
    List<Team> teamList = TeamIO.loadTeams();
    String standings = "";
    int champId = 0; //drivers champ
    int cha_champId = 0; //cons champ
    int season = 0;
    int roundID = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        TableView tableView = new TableView();
        VBox vb = new VBox();
        HBox hb = new HBox();
        HBox hbBottom = new HBox();
        bp.setStyle("-fx-background-color: black");

        Label title = new Label();
        title.setText("F1 CHAMPIONSHIP");
        ImageView img = new ImageView(new Image("res/f1.png"));
        img.setFitWidth(100);
        img.setFitHeight(100);
        title.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        Button btnBack = new Button("Back");
        btnBack.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");

        hb.getChildren().addAll(img, title, btnBack);
        bp.setTop(hb);

        hb.setStyle("-fx-alignment: center;-fx-spacing: 20;");
        hbBottom.setStyle("-fx-alignment: center;-fx-spacing: 20;");
        vb.setStyle("-fx-alignment: top-right; -fx-padding: 20; -fx-spacing: 15;");

        Label lblenter = new Label("Enter season");
        Label lblseason = new Label("Season: ");
        TextField tfseason = new TextField();
        tfseason.setAlignment(Pos.CENTER);
        Button btnEnter = new Button();
        btnEnter.setText("Enter");
        btnEnter.setStyle("-fx-background-color: #ff0100;-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        lblenter.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblseason.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");

        VBox middle2 = new VBox();
        middle2.setStyle("-fx-alignment: top-center; -fx-padding: 20; -fx-spacing: 15;");
        middle2.getChildren().addAll(lblenter, lblseason, tfseason, btnEnter);
        bp.setCenter(middle2);

        TextArea ta = new TextArea();
        ta.setEditable(false);
        vb.getChildren().add(ta);

        Button declare = new Button("DECLARE CHAMPION");
        declare.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        hbBottom.getChildren().add(declare);

        //rest
        Scene scene = new Scene(bp, 1200, 1000);

        primaryStage.setTitle("F1 championship");
        primaryStage.setScene(scene);
        primaryStage.show();

        btnBack.setOnAction(e -> {
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

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    dc = RoundIO.loadDChamp(season);
                    champRounds = RoundIO.loadRounds(season);
                    dc.setChampionshipRounds(champRounds);
                    dc.setStandings(RoundIO.loadDrChampionshipPoints(dc.getChampionshipRounds()));
                    champId = RoundIO.loadDChampId(season);
                    cha_champId = RoundIO.loadConChampId(season);
                    standings = RoundUtil.printDriversPoints(dc.getStandings());
                    ta.setText(standings);
                    bp.setCenter(vb);
                    bp.setBottom(hbBottom);
                }
            });
        });
        declare.setOnAction(e -> {
            Driver tmpDriver = ChampionsIO.loadDriversChamp(season);
            System.out.println(tmpDriver.toString());
            if (tmpDriver.getSLN() == 0) {
                dc.declareChampion();
                RoundIO.saveDChamp(dc);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success!");
                    alert.setHeaderText("Champion declared!");
                    alert.setContentText("Choose different season!");
                    alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Champion already declared!");
                    alert.setContentText("Choose different season!");
                    alert.showAndWait();
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

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
