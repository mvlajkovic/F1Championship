/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package projekat;

import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.data.Driver;
import projekat.data.DriverRole;
import projekat.io.DriverIO;

/**
 *
 * @author Milica
 */
public class Drivers extends Application {
    List<Driver> driverList = DriverIO.loadDrivers();

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

        TableColumn<Driver, String> firstNameColumn
                = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Driver, String> lastNameColumn
                = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        
        TableColumn<Driver, String> driverRoleColumn
                = new TableColumn<>("Driver Role");
        driverRoleColumn.setCellValueFactory(
                new PropertyValueFactory<>("role"));

        TableColumn<Driver, String> teamCodeColumn
                = new TableColumn<>("Team code");
        teamCodeColumn.setCellValueFactory(
                new PropertyValueFactory<>("teamCode"));
        
        TableColumn<Driver, String> carNumColumn
                = new TableColumn<>("Car Number");
        carNumColumn.setCellValueFactory(
                new PropertyValueFactory<>("carNumber"));
        
        tableView.setPlaceholder(new Label("No rows to display"));
        TableViewSelectionModel<Driver> selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        tableView.getColumns().add(firstNameColumn);
        tableView.getColumns().add(lastNameColumn);
        tableView.getColumns().add(driverRoleColumn);
        tableView.getColumns().add(teamCodeColumn);
        tableView.getColumns().add(carNumColumn);

        tableView.getItems().addAll(driverList);
        


        ObservableList<Driver> selectedItems = selectionModel.getSelectedItems();
        //selectionModel.clearSelection();

        Label update = new Label("Update Driver");
        Label lblLastName = new Label("LastName: ");  
        TextField lastName = new TextField();
        lastName.setAlignment(Pos.BASELINE_RIGHT);
        Label lblName = new Label("Name: ");
        TextField name = new TextField();
        name.setAlignment(Pos.BASELINE_RIGHT);
        Label lblRole = new Label("Role: ");
        TextField role = new TextField();
        role.setAlignment(Pos.BASELINE_RIGHT);
        Label lblTeam = new Label("Team Code: ");
        TextField team = new TextField();
        team.setAlignment(Pos.BASELINE_RIGHT);
        Label lblCar = new Label("Car number: ");
        TextField car = new TextField();
        car.setAlignment(Pos.BASELINE_RIGHT);
        Button btnUpdate = new Button();
        btnUpdate.setText("Update");

        Label add = new Label("Add Driver");
        Label lblAddLastName = new Label("LastName: ");
        Label lblAddName = new Label("Name: ");
        TextField addLastName = new TextField();
        addLastName.setAlignment(Pos.BASELINE_LEFT);
        TextField addName = new TextField();
        addName.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddRole = new Label("Role: ");
        TextField addrole = new TextField();
        addrole.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddTeam = new Label("Team Code: ");
        TextField addteam = new TextField();
        addteam.setAlignment(Pos.BASELINE_LEFT);
        Label lblAddCar = new Label("Car number: ");
        TextField addcar = new TextField();
        addcar.setAlignment(Pos.BASELINE_LEFT);
        Button btnAdd = new Button();
        btnAdd.setText("Add");

        Button btnDel = new Button();
        btnDel.setText("Delete");

        vb.getChildren().addAll(update, lblName, name, lblLastName, lastName,lblRole, role, lblTeam, team, lblCar, car, btnUpdate);
        vb1.getChildren().addAll(add, lblAddName, addName, lblAddLastName, addLastName, lblAddRole, addrole, lblAddTeam, addteam, lblAddCar, addcar, btnAdd);

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
        lblLastName.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblRole.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblTeam.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblCar.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddName.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddLastName.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddRole.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddTeam.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        lblAddCar.setStyle("-fx-text-fill: white; -fx-font-size:20; -fx-font-weight:bold");
        
        
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
                new ListChangeListener<Driver>() {
            @Override
            public void onChanged(
                    Change<? extends Driver> change) {
                System.out.println(
                        "Selection changed: " + change.getList());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(selectedItems.get(0).getName());
                        lastName.setText(selectedItems.get(0).getLastName());
                        role.setText(""+selectedItems.get(0).getRole().getValue());
                        team.setText(""+selectedItems.get(0).getTeamCode());
                        car.setText(""+selectedItems.get(0).getCarNumber());
                    }
                });
            }
        });
        
        
        btnUpdate.setOnAction(e -> {
            selectedItems.get(0).setName(name.getText());
            selectedItems.get(0).setLastName(lastName.getText());
            selectedItems.get(0).setRole(DriverRole.valueOf(Integer.parseInt(role.getText())));
            selectedItems.get(0).setTeamCode(Integer.parseInt(team.getText()));
            selectedItems.get(0).setCarNumber(Integer.parseInt(car.getText()));
            DriverIO.updateDriver(selectedItems.get(0));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tableView.refresh();
                    name.setText("");
                    lastName.setText("");
                    role.setText("");
                    team.setText("");
                    car.setText("");
                }
            });
        });
        
        
        btnAdd.setOnAction(e -> {
            Driver tmp = new Driver();
            tmp.setName(addName.getText());
            tmp.setLastName(addLastName.getText());
            tmp.setRole(DriverRole.valueOf(Integer.parseInt(addrole.getText())));
            tmp.setCarNumber(Integer.parseInt(addcar.getText()));
            tmp.setTeamCode(Integer.parseInt(addteam.getText()));
            DriverIO.addDriver(tmp); 
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    driverList = DriverIO.loadDrivers();
                    tableView.getItems().clear();
                    tableView.getItems().addAll(driverList);
                    addName.setText("");
                    addLastName.setText("");
                    addrole.setText("");
                    addteam.setText("");
                    addcar.setText("");
                }
            });
        });
        btnDel.setOnAction(e -> {
            DriverIO.deleteDriver(selectedItems.get(0));
            selectionModel.clearSelection();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    driverList = DriverIO.loadDrivers();
                    tableView.getItems().clear();
                    tableView.getItems().addAll(driverList);
                    name.setText("");
                    lastName.setText("");
                    role.setText("");
                    team.setText("");
                    car.setText("");
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
        
        role.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    role.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        team.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    team.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        car.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    car.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addrole.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    addrole.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addteam.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    addteam.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addcar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    addcar.setText(newValue.replaceAll("[^\\d]", ""));
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
