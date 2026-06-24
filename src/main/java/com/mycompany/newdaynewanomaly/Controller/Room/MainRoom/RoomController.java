package com.mycompany.newdaynewanomaly.Controller.Room.MainRoom;

import java.io.IOException;

import com.mycompany.newdaynewanomaly.App;
import javafx.fxml.FXML;

public class RoomController {

    @FXML
    private void switchToComputer() throws IOException {

        App.setRoot("View/House/Computer");

    }

    @FXML
    private void switchToWork() throws IOException {

        App.setRoot("View/Screen/GoingToWork");

    }


}