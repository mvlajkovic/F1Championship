/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projekat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milica
 */
public class LatestNews extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: black");
        Label title = new Label();
        title.setText("F1 CHAMPIONSHIP");
        ImageView img = new ImageView(new Image("res/f1.png"));
        img.setFitWidth(100);
        img.setFitHeight(100);

        title.setStyle("-fx-text-fill: white; -fx-font-size:40; -fx-font-weight:bold");
        HBox hb = new HBox();
        Button btnBack = new Button("Back");
        hb.setStyle("-fx-alignment: center;-fx-spacing: 20;");
        btnBack.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        hb.getChildren().addAll(img, title, btnBack);
        bp.setTop(hb);

        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webView.getEngine().load("https://www.formula1.com/en/latest.html");
        ProgressBar progressBar = new ProgressBar();
        Worker<Void> worker = webEngine.getLoadWorker();
        progressBar.progressProperty().bind(worker.progressProperty());
        VBox vb = new VBox();
        vb.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-spacing: 20;");
        vb.getChildren().add(progressBar);
        bp.setCenter(vb);

        Scene scene = new Scene(bp, 1200, 900);

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

        worker.stateProperty().addListener(new ChangeListener<Worker.State>() {

            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            bp.setCenter(webView);
                        }
                    });

                }
                if (newValue == Worker.State.RUNNING) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            bp.setCenter(progressBar);
                        }
                    });
                }
            }
        });

    }

   

}
