package com.mycompany.newdaynewanomaly.Controller;

import java.io.IOException;

import com.mycompany.newdaynewanomaly.App;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("House/Room");
    }

    @FXML
    private void Credits() throws IOException {
        App.setRoot("Menu/Creditos");
    }

    @FXML
    private void ReturnToMenu() throws IOException {
        App.setRoot("Menu/MainMenu");
    }

    @FXML
    private void quit() throws IOException {

        Platform.exit();

    }
}
