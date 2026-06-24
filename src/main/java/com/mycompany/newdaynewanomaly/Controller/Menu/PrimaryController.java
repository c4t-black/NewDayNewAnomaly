package com.mycompany.newdaynewanomaly.Controller.Menu;

import java.io.IOException;

import com.mycompany.newdaynewanomaly.App;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void Play() throws IOException {
        App.setRoot("View/House/Room");
    }

    @FXML
    private void Credits() throws IOException {
        App.setRoot("View/Menu/Creditos");
    }

    @FXML
    private void ReturnToMenu() throws IOException {
        App.setRoot("View/Menu/MainMenu");
    }

    @FXML
    private void quit() throws IOException {

        Platform.exit();

    }
}
