package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TableController {

    @FXML
    public void initialize() {

        int CurrentDay = App.jogador.get(0).getCurrentDay();

        App.jogador.get(0).setCurrentDay(CurrentDay + 1);

        Platform.runLater(this::abrirResumo);
    }

    private void abrirResumo() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/mycompany/newdaynewanomaly/View/Work/Summary.fxml")
            );

            Parent root = loader.load();
            SummaryController summaryController = loader.getController();

            summaryController.setData(
                    App.lastRoundResults,
                    App.lastMoneyEarned,
                    App.jogador.get(0).getSanity()
            );

            Stage summaryStage = new Stage();
            summaryStage.initModality(Modality.APPLICATION_MODAL);
            summaryStage.setTitle("Resumo do Dia");
            summaryStage.setScene(new Scene(root));
            summaryStage.showAndWait();

            App.setRoot("View/Screen/StandBy"); // ajustar pro destino real

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}