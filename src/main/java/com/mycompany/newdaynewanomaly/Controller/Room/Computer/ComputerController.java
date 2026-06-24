package com.mycompany.newdaynewanomaly.Controller.Room.Computer;
import java.io.IOException;
import com.mycompany.newdaynewanomaly.Utils.Timer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ComputerController {

    @FXML
    Label TestText;

    @FXML
    Button boto;

    @FXML
    ImageView peixe;

    @FXML
    private void test () {

        changeText(". . .");


        Timer.TaskWait(5f, this::showImage );


    }

    @FXML
    private void changeText (String text) {

        TestText.setText(text);

    }

    @FXML
    private void showImage () {

        peixe.setVisible(true);

    }

}

