/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projekat;

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
import javafx.scene.control.Button;
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
import projekat.data.Team;
import projekat.data.Track;
import projekat.data.TrackDirection;
import projekat.data.TrackType;
import projekat.io.TeamIO;
import projekat.io.TrackIO;

/**
 *
 * @author Milica
 */
public class Tracks extends Application {

    List<Track> trackList = TrackIO.loadTracks();

    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        TableView tableView = new TableView();
        VBox vb = new VBox();
        VBox vb1 = new VBox();
        VBox vb2 = new VBox();
        
        TextArea ta = new TextArea();
        ta.setText(TrackIO.loadTrackTypesToString());
        ta.setPrefHeight(100);
        ta.setEditable(false);
        TextArea ta1 = new TextArea();
        ta1.setText(TrackIO.loadTrackDirToString());
        ta1.setPrefHeight(100);
        ta1.setEditable(false);
        
        Label heading1 = new Label("Track types");
        Label heading2 = new Label("Track directions");
        
        bp.setStyle("-fx-background-color: black");

        Label title = new Label();
        title.setText("F1 CHAMPIONSHIP");
        ImageView img = new ImageView(new Image("res/f1.png"));
        img.setFitWidth(100);
        img.setFitHeight(100);

        title.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");

        TableColumn<Track, String> trackIdColumn
                = new TableColumn<>("Track ID");
        trackIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("trackId"));

        TableColumn<Track, String> nameColumn
                = new TableColumn<>("Track Name");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Track, String> locationColumn
                = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));

        TableColumn<Track, String> lengthColumn
                = new TableColumn<>("kmLength");
        lengthColumn.setCellValueFactory(
                new PropertyValueFactory<>("kmLength"));

        TableColumn<Track, String> typeColumn
                = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        TableColumn<Track, String> directionColumn
                = new TableColumn<>("Direction");
        directionColumn.setCellValueFactory(
                new PropertyValueFactory<>("direction"));

        tableView.setPlaceholder(new Label("No rows to display"));
        TableView.TableViewSelectionModel<Track> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        tableView.getColumns().add(trackIdColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(locationColumn);
        tableView.getColumns().add(lengthColumn);
        tableView.getColumns().add(typeColumn);
        tableView.getColumns().add(directionColumn);

        tableView.getItems().addAll(trackList);

        ObservableList<Track> selectedItems = selectionModel.getSelectedItems();
        //selectionModel.clearSelection();

        Label update = new Label("Update Track");
        Label lblLocation = new Label("Location: ");
        TextField location = new TextField();
        location.setAlignment(Pos.BASELINE_RIGHT);
        Label lblName = new Label("Name: ");
        TextField name = new TextField();
        name.setAlignment(Pos.BASELINE_RIGHT);

        Label lblLength = new Label("Length in km: ");
        TextField length = new TextField();
        length.setAlignment(Pos.BASELINE_RIGHT);

        Label lblType = new Label("Track type: ");
        TextField type = new TextField();
        type.setAlignment(Pos.BASELINE_RIGHT);

        Label lblDir = new Label("Track dir: ");
        TextField dir = new TextField();
        dir.setAlignment(Pos.BASELINE_RIGHT);

        Button btnUpdate = new Button();
        btnUpdate.setText("Update");

        Label add = new Label("Add Track");
        Label lblAddLocation = new Label("Location: ");
        TextField addLocation = new TextField();
        addLocation.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddName = new Label("Name: ");
        TextField addName = new TextField();
        addName.setAlignment(Pos.BASELINE_LEFT);
        Button btnAdd = new Button();

        Label addlblLength = new Label("Length in km: ");
        TextField addlength = new TextField();
        addlength.setAlignment(Pos.BASELINE_LEFT);

        Label addlblType = new Label("Track type: ");
        TextField addtype = new TextField();
        addtype.setAlignment(Pos.BASELINE_LEFT);

        Label addlblDir = new Label("Track direction: ");
        TextField adddir = new TextField();
        dir.setAlignment(Pos.BASELINE_LEFT);

        btnAdd.setText("Add");

        Button btnDel = new Button();
        btnDel.setText("Delete");

        vb.getChildren().addAll(update, lblName, name, lblLocation, location, lblLength, length, lblType, type, lblDir, dir, btnUpdate);
        vb1.getChildren().addAll(add, lblAddName, addName, lblAddLocation, addLocation, addlblLength, addlength, addlblType, addtype, addlblDir, adddir, btnAdd);

        HBox hb = new HBox();
        HBox hb1 = new HBox();

        Button btnBack = new Button("Back");

        add.setStyle("-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        update.setStyle("-fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        btnBack.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        btnAdd.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        btnDel.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        btnUpdate.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        hb.setStyle("-fx-alignment: center;-fx-spacing: 20;");
        hb1.setStyle("-fx-alignment: center; -fx-padding: 20;-fx-spacing: 20;");
        vb.setStyle("-fx-alignment: top-right; -fx-padding: 20; -fx-spacing: 15;");
        vb1.setStyle("-fx-alignment: top-left; -fx-padding: 20;-fx-spacing:15;");
        vb2.setStyle("-fx-alignment: top-left; -fx-padding: 20;-fx-spacing:15;");
        lblName.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblLocation.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddName.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddLocation.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblLength.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblType.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblDir.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        addlblLength.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        addlblType.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        addlblDir.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        heading1.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        heading2.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");

        hb.getChildren().addAll(img, title, btnBack);
        hb1.getChildren().add(btnDel);
        vb2.getChildren().addAll(heading1, ta, heading2, ta1, tableView);
        bp.setTop(hb);
        bp.setCenter(vb2);
        bp.setRight(vb);
        bp.setLeft(vb1);
        bp.setBottom(hb1);

        Scene scene = new Scene(bp, 1200, 1000);

        primaryStage.setTitle("F1 championship");
        primaryStage.setScene(scene);
        primaryStage.show();

        selectedItems.addListener(
                new ListChangeListener<Track>() {
            @Override
            public void onChanged(
                    ListChangeListener.Change<? extends Track> change) {
                System.out.println(
                        "Selection changed: " + change.getList());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(selectedItems.get(0).getName());
                        location.setText(selectedItems.get(0).getLocation());
                        length.setText("" + selectedItems.get(0).getKmLength());
                        type.setText("" + selectedItems.get(0).getType().getValue());
                        dir.setText("" + selectedItems.get(0).getDirection().getValue());

                    }
                });
            }
        });

        btnUpdate.setOnAction(e -> {
            selectedItems.get(0).setName(name.getText());
            selectedItems.get(0).setLocation(location.getText());
            selectedItems.get(0).setKmLength(Double.parseDouble(length.getText()));
            selectedItems.get(0).setType(TrackType.valueOf(Integer.parseInt(type.getText())));
            selectedItems.get(0).setDirection(TrackDirection.valueOf(Integer.parseInt(dir.getText())));

            TrackIO.updateTrack(selectedItems.get(0));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tableView.refresh();
                    name.setText("");
                    location.setText("");
                    length.setText("");
                    type.setText("");
                    dir.setText("");

                }
            });
        });

        btnAdd.setOnAction(e -> {
            Track tmp = new Track();
            tmp.setName(addName.getText());
            tmp.setLocation(addLocation.getText());
            tmp.setKmLength(Double.parseDouble(addlength.getText()));
            tmp.setType(TrackType.valueOf(Integer.parseInt(addtype.getText())));
            tmp.setDirection(TrackDirection.valueOf(Integer.parseInt(adddir.getText())));

            TrackIO.addTrack(tmp);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    trackList = TrackIO.loadTracks();
                    tableView.getItems().clear();
                    tableView.getItems().addAll(trackList);
                    addName.setText("");
                    addLocation.setText("");
                    addlength.setText("");
                    addtype.setText("");
                    adddir.setText("");
                }
            });
        });
        btnDel.setOnAction(e -> {
            TrackIO.deleteTrack(selectedItems.get(0));
            selectionModel.clearSelection();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    trackList = TrackIO.loadTracks();
                    tableView.getItems().clear();
                    tableView.getItems().addAll(trackList);
                    name.setText("");
                    location.setText("");
                    length.setText("");
                    type.setText("");
                    dir.setText("");
                }

            });

        });
        btnBack.setOnAction(e -> {
            primaryStage.close();
            try {
                new HomePage().start(primaryStage);
            } catch (Exception ex) {
                System.out.println("Error loading HomePage");
                ex.printStackTrace();
            }
        });

        length.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    length.setText(oldValue);
                }
            }

        });

        addlength.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    addlength.setText(oldValue);
                }
            }
        });
        
        type.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    type.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        dir.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    dir.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        addtype.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    addtype.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        adddir.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    adddir.setText(newValue.replaceAll("[^\\d]", ""));
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
