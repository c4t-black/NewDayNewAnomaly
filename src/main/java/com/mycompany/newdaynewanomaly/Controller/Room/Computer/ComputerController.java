package com.mycompany.newdaynewanomaly.Controller.Room.Computer;
import java.io.IOException;
import com.mycompany.newdaynewanomaly.Utils.Timer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ComputerController {

    @FXML
    Label TestText;

    @FXML
    private void test () {

        Timer.TaskWait(5f, this::changeText);

    }

    @FXML
    private void changeText () {

        TestText.setText("Deu certo!");

    }

}

