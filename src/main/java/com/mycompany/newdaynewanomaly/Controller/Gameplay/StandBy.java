package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.Utils.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

public class StandBy {

    @FXML
    private Label DayText;

    public String currentDay;
    
    @FXML
    private void startGame () throws IOException {

        int dia = App.jogador.get(0).getCurrentDay();

        if (dia > 0 && dia % 10 == 0) {

            App.setRoot("View/Screen/Boss");

        } else {

            App.setRoot("View/House/Room");
        }

    }

    @FXML
    public void initialize() {

        currentDay = "DAY " + App.jogador.get(0).getCurrentDay();

        System.out.println(currentDay);

        Timer.TaskSequence(List.of(
                new AbstractMap.SimpleEntry<>(1f, () -> DayText.setText("D")),
                new AbstractMap.SimpleEntry<>(1f, () -> DayText.setText("DA")),
                new AbstractMap.SimpleEntry<>(1f, () -> DayText.setText("DAY")),
                new AbstractMap.SimpleEntry<>(1f, () -> DayText.setText("DAY ")),
                new AbstractMap.SimpleEntry<>(1f, () -> DayText.setText(currentDay)),
                new AbstractMap.SimpleEntry<>(3f, () -> DayText.setText("..."))

        ), () -> {
            try {
                startGame();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
