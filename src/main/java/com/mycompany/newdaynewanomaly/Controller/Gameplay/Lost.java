package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.DAO.PlayerDAO;
import com.mycompany.newdaynewanomaly.Utils.Timer;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

public class Lost {

    @FXML
    private void menu () throws IOException {

        App.jogador.get(0).setCola(0);
        App.jogador.get(0).setBigCola(0);
        App.jogador.get(0).setChips(0);
        App.jogador.get(0).setGlasses(false);
        App.jogador.get(0).setMoney(0);

        App.jogador.get(0).setCurrentDay(1);
        App.jogador.get(0).setSanity(100);

        new PlayerDAO().save(App.jogador.get(0));

        App.setRoot("View/Menu/MainMenu");

    }

}
