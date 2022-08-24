/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package projekat;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Milica
 */
public class Techical extends Application {
    
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
        Button btnBack = new Button("Back");
        hb.setStyle("-fx-alignment: center;-fx-spacing: 20;");
        btnBack.setStyle("-fx-background-color: #ff0100; -fx-text-fill: white; -fx-font-size:30; -fx-font-weight:bold");
        hb.getChildren().addAll(img, title, btnBack);
        bp.setTop(hb);
        TextArea ta = new TextArea();
         WebView webView = new WebView();
        try {
        Document doc = Jsoup.connect("https://www.formula1.com/en/latest/article.10-things-you-need-to-know-about-the-all-new-2022-f1-car.4OLg8DrXyzHzdoGrbqp6ye.html").get();
          //f1-article--rich-tex
       
        webView.getEngine().loadContent(doc.body().html());
        ta.setText(doc.body().text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //MOŽDA JSOUP ZA PREUZIMANJE PDF TECH REGULATION SA FIA SAJTA - POTREBAN JE LOCATION PICKER
        bp.setCenter(webView);
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
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
