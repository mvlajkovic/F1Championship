/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package projekat;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.data.Driver;
import projekat.data.DriverRole;
import projekat.data.Team;
import projekat.io.DriverIO;
import projekat.io.TeamIO;

/**
 *
 * @author Milica
 */
public class Teams extends Application {
    List<Team> teamList = TeamIO.loadTeams();
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        TableView tableView = new TableView();
        VBox vb = new VBox();
        VBox vb1 = new VBox();

        bp.setStyle("-fx-background-color: black");

        Label title = new Label();
        title.setText("F1 CHAMPIONSHIP");
        ImageView img = new ImageView(new Image("res/f1.png"));
        img.setFitWidth(100);
        img.setFitHeight(100);

        title.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");

        TableColumn<Team, String> teamCodeColumn
                = new TableColumn<>("Team Code");
        teamCodeColumn.setCellValueFactory(
                new PropertyValueFactory<>("teamCode"));

        TableColumn<Team, String> nameColumn
                = new TableColumn<>("Team Name");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        
        TableColumn<Team, String> locationColumn
                = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("Location"));

        tableView.setPlaceholder(new Label("No rows to display"));
        TableView.TableViewSelectionModel<Team> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        tableView.getColumns().add(teamCodeColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(locationColumn);


        tableView.getItems().addAll(teamList);
        


        ObservableList<Team>selectedItems = selectionModel.getSelectedItems();
        //selectionModel.clearSelection();

        Label update = new Label("Update Team");
        Label lblLocation = new Label("Location: ");  
        TextField location = new TextField();
        location.setAlignment(Pos.BASELINE_RIGHT);
        Label lblName = new Label("Name: ");
        TextField name = new TextField();
        name.setAlignment(Pos.BASELINE_RIGHT);
        Button btnUpdate = new Button();
        btnUpdate.setText("Update");

        Label add = new Label("Add Team");
        Label lblAddLocation = new Label("Location: ");
        TextField addLocation = new TextField();
        addLocation.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddName = new Label("Name: ");
        TextField addName = new TextField();
        addName.setAlignment(Pos.BASELINE_LEFT);
        Button btnAdd = new Button();
        btnAdd.setText("Add");

        Button btnDel = new Button();
        btnDel.setText("Delete");

        vb.getChildren().addAll(update, lblName, name, lblLocation, location, btnUpdate);
        vb1.getChildren().addAll(add, lblAddName, addName, lblAddLocation, addLocation, btnAdd);

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
        lblName.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblLocation.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddName.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddLocation.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");

        
        
        hb.getChildren().addAll(img, title, btnBack);
        hb1.getChildren().add(btnDel);
        bp.setTop(hb);
        bp.setCenter(tableView);
        bp.setRight(vb);
        bp.setLeft(vb1);
        bp.setBottom(hb1);
        
        Scene scene = new Scene(bp, 1200, 1000);

        primaryStage.setTitle("F1 championship");
        primaryStage.setScene(scene);
        primaryStage.show();

        
        selectedItems.addListener(
                new ListChangeListener<Team>() {
            @Override
            public void onChanged(
                    ListChangeListener.Change<? extends Team> change) {
                System.out.println(
                        "Selection changed: " + change.getList());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(selectedItems.get(0).getName());
                        location.setText(selectedItems.get(0).getLocation());

                    }
                });
            }
        });
        
        
        btnUpdate.setOnAction(e -> {
            selectedItems.get(0).setName(name.getText());
            selectedItems.get(0).setLocation(location.getText());
  
            TeamIO.updateTeam(selectedItems.get(0));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tableView.refresh();
                    name.setText("");
                    location.setText("");

                }
            });
        });
        
        
        btnAdd.setOnAction(e -> {
            Team tmp = new Team();
            tmp.setName(addName.getText());
            tmp.setLocation(addLocation.getText());

            TeamIO.addTeam(tmp); 
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    teamList = TeamIO.loadTeams();
                    tableView.getItems().clear();
                    tableView.getItems().addAll(teamList);
                    addName.setText("");
                    addLocation.setText("");

                }
            });
        });
        btnDel.setOnAction(e -> {
            TeamIO.deleteTeam(selectedItems.get(0));
            selectionModel.clearSelection();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    teamList = TeamIO.loadTeams();
                    tableView.getItems().clear();
                    tableView.getItems().addAll(teamList);
                    name.setText("");
                    location.setText("");

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
        
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
