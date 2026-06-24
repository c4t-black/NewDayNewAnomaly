package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.Utils.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

public class Loading {

    @FXML
    private Label DayText;

    @FXML
    private void startWork () throws IOException {

        App.setRoot("View/Work/WorkGame");

    }

    @FXML
    public void initialize() {

        Timer.TaskWait(5, () -> {

            try {
                startWork();

            } catch (IOException e) {

                throw new RuntimeException(e);
            }

        });
    }


}
