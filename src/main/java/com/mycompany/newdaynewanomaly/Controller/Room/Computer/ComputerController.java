package com.mycompany.newdaynewanomaly.Controller.Room.Computer;

import java.io.IOException;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.Models.Player; // ajuste pro pacote certo

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ComputerController {

    private Player jogador;

    @FXML
    private Label PlayerMoney;

    @FXML
    private Label amountCola;

    @FXML
    private Label amountChips;

    @FXML
    private Label amountBigCola;

    @FXML
    private Button glassButton;

    @FXML
    private Label hasGlasses;

    @FXML
    public void initialize() {
        jogador = App.jogador.get(0);

        PlayerMoney.setText("$: " + jogador.getMoney());

        amountCola.setText(": " + jogador.getCola());
        amountBigCola.setText(": " + jogador.getBigCola());
        amountChips.setText(": " + jogador.getChips());

        if (jogador.isGlasses()) {

            glassButton.setDisable(true);
            glassButton.setText("BOUGHT!");
            hasGlasses.setText("- Obtained");

        }

    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("View/House/Room");
    }

    @FXML
    public void buyCola() {

        int currentMoney = jogador.getMoney();

        if (currentMoney >= 3) {
            jogador.setCola(jogador.getCola() + 1);
            jogador.setMoney(currentMoney - 3);

            PlayerMoney.setText("$: " + jogador.getMoney());
            amountCola.setText(": " + jogador.getCola());
        }
    }

    @FXML
    public void buyBigCola() {

        int currentMoney = jogador.getMoney();

        if (currentMoney >= 6) {
            jogador.setBigCola(jogador.getBigCola() + 1);
            jogador.setMoney(currentMoney - 6);


            PlayerMoney.setText("$: " + jogador.getMoney());
            amountBigCola.setText(": " + jogador.getBigCola());
        }
    }

    @FXML
    public void buyChips() {

        int currentMoney = jogador.getMoney();

        if (currentMoney >= 15) {
            jogador.setChips(jogador.getChips() + 1);
            jogador.setMoney(currentMoney - 15);

            PlayerMoney.setText("$: " + jogador.getMoney());
            amountChips.setText(": " + jogador.getChips());
        }
    }

    @FXML
    public void buyGlasses() {

        int currentMoney = jogador.getMoney();

        if (currentMoney >= 50) {

            jogador.setGlasses(true);
            jogador.setMoney(currentMoney - 50);

            PlayerMoney.setText("$: " + jogador.getMoney());

            glassButton.setDisable(true);
            glassButton.setText("BOUGHT!");

            hasGlasses.setText("- Obtained");

        }

    }

    @FXML
    public void cheat() {

       jogador.setMoney(1000);

    }
}