package com.mycompany.newdaynewanomaly.Controller.Menu;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.DAO.RankDAO;
import com.mycompany.newdaynewanomaly.Models.RankEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.List;

public class RankController {

    @FXML
    private VBox rankList;

    @FXML
    public void initialize() {
        List<RankEntry> top10 = new RankDAO().findTop10();

        if (top10.isEmpty()) {
            Label vazio = new Label("Nenhum save encontrado ainda.");
            vazio.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 16px;");
            rankList.getChildren().add(vazio);
            return;
        }

        for (RankEntry entry : top10) {
            rankList.getChildren().add(buildRow(entry));
        }
    }

    private HBox buildRow(RankEntry entry) {
        HBox row = new HBox(30);
        row.setStyle("-fx-alignment: CENTER;");

        String medal;
        if (entry.getPosition() == 1) {
            medal = "🥇";
        } else if (entry.getPosition() == 2) {
            medal = "🥈";
        } else if (entry.getPosition() == 3) {
            medal = "🥉";
        } else {
            medal = "#" + entry.getPosition();
        }

        Label pos     = styledLabel(medal,                                                   22, "#FFD700");
        Label days    = styledLabel("Dia " + entry.getMaxDay(),                              20, "#FFFFFF");
        Label money   = styledLabel("$" + entry.getMoney(),                                  18, "#00FF99");
        Label sanity  = styledLabel(String.format("%.0f%% san", entry.getSanity()),          18, "#FF6666");
        Label acertos = styledLabel(entry.getAnomaliesGuessed() + "A / " + entry.getHumansGuessed() + "H", 16, "#AAAAAA");

        row.getChildren().addAll(pos, days, money, sanity, acertos);
        return row;
    }

    private Label styledLabel(String text, double size, String color) {
        Label lbl = new Label(text);
        lbl.setStyle("-fx-text-fill: " + color + "; -fx-min-width: 100px; -fx-alignment: CENTER;");
        lbl.setFont(new Font("Felix Titling", size));
        return lbl;
    }

    @FXML
    private void ReturnToMenu() throws IOException {
        App.setRoot("View/Menu/MainMenu");
    }
}