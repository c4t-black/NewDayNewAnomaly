package com.mycompany.newdaynewanomaly;

import com.mycompany.newdaynewanomaly.Models.Player;
import com.mycompany.newdaynewanomaly.Models.RoundResult;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("View/Menu/MainMenu"));
        stage.setScene(scene);

        stage.setResizable(false);

        stage.setWidth(1200);
        stage.setHeight(1000);

        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static ObservableList<Player> jogador = FXCollections.observableArrayList();
    public static List<RoundResult> lastRoundResults = new ArrayList<>();
    public static int lastMoneyEarned = 0;

    public static void main(String[] args) {
        launch();
    }

}