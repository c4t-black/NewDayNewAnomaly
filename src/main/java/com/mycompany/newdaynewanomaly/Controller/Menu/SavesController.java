package com.mycompany.newdaynewanomaly.Controller.Menu;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.DAO.PlayerDAO;
import com.mycompany.newdaynewanomaly.DAO.SaveEntry;
import com.mycompany.newdaynewanomaly.Models.Player;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.List;

public class SavesController {

    @FXML
    private VBox savesList;  // injetado pelo FXML

    @FXML
    public void initialize() {
        PlayerDAO dao = new PlayerDAO();
        List<SaveEntry> saves = dao.findAllSaves();

        if (saves.isEmpty()) {
            Label vazio = new Label("Nenhum save encontrado.");
            vazio.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 16px;");
            savesList.getChildren().add(vazio);
            return;
        }

        for (SaveEntry entry : saves) {
            savesList.getChildren().add(buildSaveButton(entry));
        }
    }

    /** Cria um botão clicável para cada save. */
    private Button buildSaveButton(SaveEntry entry) {
        Button btn = new Button(entry.getDisplayText());
        btn.setPrefWidth(700);
        btn.setPrefHeight(48);
        btn.setFont(new Font("Felix Titling", 15));
        btn.setStyle(
            "-fx-background-color: #3a3a38; " +
            "-fx-text-fill: #ffffff; " +
            "-fx-border-color: #FF4444; " +
            "-fx-border-width: 1px; " +
            "-fx-cursor: hand;"
        );

        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: #FF4444; " +
            "-fx-text-fill: #ffffff; " +
            "-fx-border-color: #FF4444; " +
            "-fx-border-width: 1px; " +
            "-fx-cursor: hand;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: #3a3a38; " +
            "-fx-text-fill: #ffffff; " +
            "-fx-border-color: #FF4444; " +
            "-fx-border-width: 1px; " +
            "-fx-cursor: hand;"
        ));

        btn.setOnAction(e -> loadSave(entry.getId()));
        return btn;
    }

    /** Carrega o save selecionado e inicia o jogo a partir dele. */
    private void loadSave(int saveId) {
        PlayerDAO dao = new PlayerDAO();
        Player player = dao.findById(saveId);

        if (player == null) {
            System.err.println("[SavesController] Save ID " + saveId + " não encontrado.");
            return;
        }

        App.jogador.clear();
        App.jogador.add(player);

        try {
            App.setRoot("View/Screen/StandBy");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ReturnToMenu() throws IOException {
        App.setRoot("View/Menu/MainMenu");
    }
}
