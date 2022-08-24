/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekat;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.data.Driver;
import projekat.data.Qualification;
import projekat.data.QualificationType;
import projekat.data.Race;
import projekat.data.Round;
import projekat.data.Team;
import projekat.data.Track;
import projekat.data.TrackDirection;
import projekat.data.TrackType;
import projekat.io.ChampionsIO;
import projekat.io.DriverIO;
import projekat.io.RoundIO;
import projekat.io.TeamIO;
import projekat.io.TrackIO;
import projekat.util.GeneralUtil;

/**
 *
 * @author Milica
 */
public class Rounds extends Application {

    List<Round> roundList = new ArrayList<>();
    List<Driver> driverList = DriverIO.loadDrivers();
    List<Team> teamList = TeamIO.loadTeams();
    int champId = 0; //drivers champ
    int cha_champId = 0; //cons champ
    int season = 0;
    int roundID = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        TableView tableView = new TableView();
        VBox vb = new VBox();
        VBox vbLeft = new VBox();
        VBox vbRight = new VBox();
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
        vbLeft.setStyle("-fx-alignment: top-left; -fx-padding: 20;-fx-spacing:15;");
        vbRight.setStyle("-fx-alignment: top-left; -fx-padding: 20;-fx-spacing:15;");

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

        //middle
        TableColumn<Round, String> roundIdColumn
                = new TableColumn<>("Round ID");
        roundIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("roundId"));
        tableView.getColumns().add(roundIdColumn);
        TableColumn<Round, String> gpColumn
                = new TableColumn<>("Grand Prix Name");
        gpColumn.setCellValueFactory(
                new PropertyValueFactory<>("grandPrixName"));
        tableView.getColumns().add(gpColumn);

        tableView.setPlaceholder(new Label("No rows to display"));
        TableView.TableViewSelectionModel<Round> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        ObservableList<Round> selectedItems = selectionModel.getSelectedItems();
        //selectionModel.clearSelection();

        //tableView.getItems().addAll(roundList);
        TextArea taQuali = new TextArea();
        taQuali.setPrefHeight(50);
        taQuali.setEditable(false);

        TextArea taQualiType = new TextArea();
        taQualiType.setPrefHeight(100);
        taQualiType.setEditable(false);
        taQualiType.setText(RoundIO.loadQualiTypeToString());

        TextArea taRace = new TextArea();
        taRace.setPrefHeight(50);
        taRace.setEditable(false);

        TextArea taTrack = new TextArea();
        taTrack.setPrefHeight(50);
        taTrack.setEditable(false);

        Label heading1 = new Label("Qualification");
        Label heading3 = new Label("Qualification Type");
        Label heading2 = new Label("Race");
        Label heading4 = new Label("Track");

        heading1.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        heading2.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        heading3.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        heading4.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");

        vb.getChildren().addAll(heading1, taQuali, heading3, taQualiType, heading2, taRace, heading4, taTrack, tableView);

        //bottom
        Button btnDel = new Button();
        btnDel.setText("Delete");
        btnDel.setStyle("-fx-background-color: #ff0100;-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");

        Button raceRes = new Button();
        raceRes.setText("RACE RESULTS");
        raceRes.setStyle("-fx-background-color: #ff0100;-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");

        Button qualiRes = new Button();
        qualiRes.setText("QUALI RESULTS");
        qualiRes.setStyle("-fx-background-color: #ff0100;-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");

        hbBottom.getChildren().addAll(raceRes, qualiRes, btnDel);

        //left
        Label add = new Label("Add Round");
        Label lblAddTrack = new Label("Track ID: ");
        TextField addTrack = new TextField();
        addTrack.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddGP = new Label("Grand Prix Name: ");
        TextField addGP = new TextField();
        addGP.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddQualiType = new Label("Quali Type: ");
        TextField addQualiType = new TextField();
        addQualiType.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddQualiDate = new Label("Quali date: ");
        DatePicker addQualiDatePick = new DatePicker();
        //TextField addQualiDate = new TextField();
        //addQualiDate.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddRaceLaps = new Label("Race laps: ");
        TextField addRaceLaps = new TextField();
        addRaceLaps.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddRaceDate = new Label("Race date: ");
        DatePicker addRaceDatePick = new DatePicker();
        //TextField addRaceDate = new TextField();
        //addRaceDate.setAlignment(Pos.BASELINE_LEFT);
        Button btnAdd = new Button();
        btnAdd.setText("Add");

        add.setStyle("-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        btnAdd.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        lblAddTrack.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddGP.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddQualiType.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddQualiDate.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddRaceLaps.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddRaceDate.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");

        vbLeft.getChildren().addAll(add, lblAddTrack, addTrack, lblAddGP, addGP, lblAddQualiType, addQualiType, lblAddQualiDate, addQualiDatePick, lblAddRaceLaps, addRaceLaps, lblAddRaceDate, addRaceDatePick, btnAdd);

        //right
        Label update = new Label("Update Round");
        Label lblUpdateGP = new Label("Grand Prix Name: ");
        TextField updateGP = new TextField();
        updateGP.setAlignment(Pos.BASELINE_LEFT);
        Label lblUpdateQualiType = new Label("Quali Type: ");
        TextField updateQualiType = new TextField();
        updateQualiType.setAlignment(Pos.BASELINE_LEFT);
        Label lblUpdateQualiDate = new Label("Quali date: ");
        DatePicker updateQualiDatePick = new DatePicker();
        //TextField updateQualiDate = new TextField();
        //updateQualiDate.setAlignment(Pos.BASELINE_LEFT);
        Label lblUpdateRaceLaps = new Label("Race laps: ");
        TextField updateRaceLaps = new TextField();
        updateRaceLaps.setAlignment(Pos.BASELINE_LEFT);
        Label lblUpdateCompletedLaps = new Label("Completed laps: ");
        TextField updateCompletedLaps = new TextField();
        updateCompletedLaps.setAlignment(Pos.BASELINE_LEFT);
        Label lblUpdateRaceDate = new Label("Race date: ");
        DatePicker updateRaceDatePick = new DatePicker();
        //TextField updateRaceDate = new TextField();
        //updateRaceDate.setAlignment(Pos.BASELINE_LEFT);
        Button btnUpdate = new Button();
        btnUpdate.setText("Update");

        update.setStyle("-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        btnUpdate.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");

        lblUpdateGP.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblUpdateQualiType.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblUpdateQualiDate.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblUpdateRaceLaps.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblUpdateRaceDate.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblUpdateCompletedLaps.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");

        vbRight.getChildren().addAll(update, lblUpdateGP, updateGP, lblUpdateQualiType, updateQualiType, lblUpdateQualiDate, updateQualiDatePick, lblUpdateRaceLaps, updateRaceLaps, lblUpdateCompletedLaps, updateCompletedLaps, lblUpdateRaceDate, updateRaceDatePick, btnUpdate);

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
                    roundList = RoundIO.loadRounds(season);
                    //OVDE
                    champId = RoundIO.loadDChampId(season);
                    cha_champId = RoundIO.loadConChampId(season);
                    tableView.getItems().addAll(roundList);
                    bp.setCenter(vb);
                    bp.setLeft(vbLeft);
                    bp.setRight(vbRight);
                    bp.setBottom(hbBottom);
                }
            });
        });
        raceRes.setOnAction(e -> {
            if (!selectedItems.isEmpty()) {
                Round r = selectedItems.get(0);
                if (r.getRace().getResults().isEmpty()) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Round r = selectedItems.get(0);
                            r.getRace().addResults(driverList);
                            r.getRace().calculateDriverPoints(r.getRace().getResults());
                            r.getRace().calculateTeamPoints(r.getRace().getAwardedPoints(), teamList);
                            RoundIO.addRaceRes(r);
                            RoundIO.saveRacePointsTeam(r);
                            RoundIO.saveRacePointsDriver(r);

                        }
                    });
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Rezultati već postoje");
                    alert.setContentText("Izaberite drugu rundu!");
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Niste izabrali rundu!");
                    alert.setContentText("Izaberite rundu!");
                    alert.showAndWait();
            }
            
        });

        selectedItems.addListener(
                new ListChangeListener<Round>() {
            @Override
            public void onChanged(
                    ListChangeListener.Change<? extends Round> change) {
                System.out.println(
                        "Selection changed: " + change.getList());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        taQuali.setText(selectedItems.get(0).getQualification().toString());
                        taRace.setText(selectedItems.get(0).getRace().toString());
                        taTrack.setText(selectedItems.get(0).getTrack().toString());
                        updateGP.setText(selectedItems.get(0).getGrandPrixName());
                        updateQualiType.setText("" + selectedItems.get(0).getQualification().getType().getValue());
                        updateQualiDatePick.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(selectedItems.get(0).getQualification().getQualiDate())));
                        updateRaceLaps.setText("" + selectedItems.get(0).getRace().getRaceLaps());
                        updateCompletedLaps.setText("" + selectedItems.get(0).getRace().getCompletedLaps());
                        updateRaceDatePick.setValue(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(selectedItems.get(0).getRace().getRaceDate())));
                    }
                });
            }
        });

        btnUpdate.setOnAction(e -> {

            selectedItems.get(0).setGrandPrixName(updateGP.getText());
            selectedItems.get(0).getQualification().setType(QualificationType.valueOf(Integer.parseInt(updateQualiType.getText())));
            selectedItems.get(0).getQualification().setQualiDate(Date.valueOf(updateQualiDatePick.getValue()));
            selectedItems.get(0).getRace().setRaceLaps(Integer.parseInt(updateRaceLaps.getText()));
            selectedItems.get(0).getRace().setCompletedLaps(Integer.parseInt(updateCompletedLaps.getText()));
            selectedItems.get(0).getRace().setRaceDate(Date.valueOf(updateRaceDatePick.getValue()));
            RoundIO.updateRound(selectedItems.get(0));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tableView.refresh();
                    updateGP.setText("");
                    updateQualiType.setText("");
                    updateQualiDatePick.setValue(LocalDate.now());
                    updateRaceLaps.setText("");
                    updateCompletedLaps.setText("");
                    updateRaceDatePick.setValue(LocalDate.now());
                }
            });
        });

        btnAdd.setOnAction(e -> {
            Race r = new Race();
            Qualification q = new Qualification();
            Track t = TrackIO.loadTrack(Integer.parseInt(addTrack.getText()));
            r.setCompletedLaps(0);
            r.setRaceLaps(Integer.parseInt(addRaceLaps.getText()));
            r.setRaceDate(Date.valueOf(addRaceDatePick.getValue()));
            q.setType(QualificationType.valueOf(Integer.parseInt(addQualiType.getText())));
            q.setQualiDate(Date.valueOf(addQualiDatePick.getValue()));
            Round round = new Round();
            round.setGrandPrixName(addGP.getText());
            round.setTrack(t);
            round.setQualification(q);
            round.setRace(r);
            RoundIO.addRound(round, champId, cha_champId);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    roundList = RoundIO.loadRounds(season);
                    //OVDE
                    tableView.getItems().clear();
                    tableView.getItems().addAll(roundList);
                    addTrack.setText("");
                    addGP.setText("");
                    addQualiType.setText("");
                    addQualiDatePick.setValue(LocalDate.now());
                    addRaceLaps.setText("");
                    addRaceDatePick.setValue(LocalDate.now());
                }
            });
        });

        btnDel.setOnAction(e -> {
            RoundIO.deleteRound(selectedItems.get(0));
            selectionModel.clearSelection();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    roundList = RoundIO.loadRounds(season);
                    //OVDE
                    tableView.getItems().clear();
                    tableView.getItems().addAll(roundList);
                    addTrack.setText("");
                    addGP.setText("");
                    addQualiType.setText("");
                    addQualiDatePick.setValue(LocalDate.now());
                    addRaceLaps.setText("");
                    addRaceDatePick.setValue(LocalDate.now());
                }

            });

        });

        updateQualiType.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    updateQualiType.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        updateRaceLaps.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    updateRaceLaps.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        updateCompletedLaps.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    updateCompletedLaps.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addQualiType.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    addQualiType.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addRaceLaps.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    addRaceLaps.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addTrack.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    addTrack.setText(newValue.replaceAll("[^\\d]", ""));
                }
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
