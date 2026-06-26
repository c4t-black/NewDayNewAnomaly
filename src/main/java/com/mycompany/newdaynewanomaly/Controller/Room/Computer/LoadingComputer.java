package com.mycompany.newdaynewanomaly.Controller.Room.Computer;
import java.io.IOException;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.Utils.Timer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class LoadingComputer {

    @FXML
    public void computerWork() throws IOException {

        App.setRoot("View/House/Computer");


    }


    @FXML
    private void initialize () {

        Timer.TaskWait(8, () -> {

            try {
                computerWork();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }
        });

    }


}

