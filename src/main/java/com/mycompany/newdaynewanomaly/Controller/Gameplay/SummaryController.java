package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.Models.RoundResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class SummaryController {

    @FXML private Label moneyLabel;
    @FXML private ProgressBar sanityBar;
    @FXML private Label sanityLabel;
    @FXML private VBox resultsContainer;
    @FXML private Button continueBtn;

    public void setData(List<RoundResult> resultados, int moneyEarned, float sanidadeAtual) {

        moneyLabel.setText("+ $ " + moneyEarned);

        float percent = sanidadeAtual / 100f;
        sanityBar.setProgress(percent);
        sanityBar.setStyle("-fx-accent: #8B0000;"); // vermelho escuro (dark red)
        sanityLabel.setText(String.format("%.0f%%", sanidadeAtual));

        for (RoundResult r : resultados) {
            Label linha = new Label(
                    r.getNomeEntidade() + " — Era " + (r.isEraAnomalia() ? "Anomalia" : "Humano")
                            + " — " + (r.isJogadorAcertou() ? "✔ Acertou" : "✘ Errou")
            );

            linha.setStyle(r.isJogadorAcertou()
                    ? "-fx-text-fill: #3B6D11;"
                    : "-fx-text-fill: #A32D2D;");
            resultsContainer.getChildren().add(linha);
        }
    }

    @FXML
    public void onContinue() {
        Stage stage = (Stage) continueBtn.getScene().getWindow();
        stage.close(); // libera o showAndWait() do TableController
    }
}