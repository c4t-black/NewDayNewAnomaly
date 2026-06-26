package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.Utils.Timer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Random;

/**
 * Boss "NoSignal".
 *
 * Aparece a cada 10 dias. A tela fica com um filtro vermelho por cima e a
 * imagem NoSignal.png ao fundo. Um botão muda de posição aleatoriamente a
 * cada clique e deve ser clicado 10 vezes para "escapar" e avançar para o
 * próximo dia. O jogador tem 30 segundos (controlados pela classe Timer)
 * para conseguir os 10 cliques, caso contrário o jogo é encerrado (YouLose).
 */
public class Boss {

    @FXML private ImageView bossImage;
    @FXML private Rectangle redFilter;
    @FXML private Rectangle vignette;

    @FXML private Label warningLabel;
    @FXML private Label instructionLabel;
    @FXML private Label clicksLabel;
    @FXML private Label timerLabel;

    @FXML private Button escapeBtn;

    private static final int CLICKS_NEEDED = 40;
    private static final int MAX_TIME_SECONDS = 35;

    private static final double BTN_WIDTH = 150.0;
    private static final double BTN_HEIGHT = 60.0;

    // margens de segurança para o botão não nascer colado na borda da tela (1200x1000)
    private static final double AREA_WIDTH = 1200.0;
    private static final double AREA_HEIGHT = 1000.0;
    private static final double MARGIN = 20.0;

    private final Random random = new Random();

    private int clicksDone = 0;
    private int secondsLeft = MAX_TIME_SECONDS;

    private boolean finished = false;

    @FXML
    public void initialize() {

        clicksDone = 0;
        secondsLeft = MAX_TIME_SECONDS;
        finished = false;

        clicksLabel.setText(clicksDone + " / " + CLICKS_NEEDED);
        timerLabel.setText(String.valueOf(secondsLeft));

        moverBotaoAleatoriamente();
        iniciarContagemRegressiva();
    }

    // -------------------------
    // CLIQUE NO BOTÃO
    // -------------------------

    @FXML
    public void onEscapeClick() {

        if (finished) {
            return;
        }

        clicksDone += 1;
        clicksLabel.setText(clicksDone + " / " + CLICKS_NEEDED);

        if (clicksDone >= CLICKS_NEEDED) {
            venceuBoss();
            return;
        }

        moverBotaoAleatoriamente();
    }

    private void moverBotaoAleatoriamente() {

        double maxX = AREA_WIDTH - BTN_WIDTH - MARGIN;
        double maxY = AREA_HEIGHT - BTN_HEIGHT - MARGIN;

        double novoX = MARGIN + random.nextDouble() * (maxX - MARGIN);
        double novoY = MARGIN + random.nextDouble() * (maxY - MARGIN);

        escapeBtn.setLayoutX(novoX);
        escapeBtn.setLayoutY(novoY);
    }

    // -------------------------
    // TEMPO (usa a classe Timer)
    // -------------------------

    private void iniciarContagemRegressiva() {
        tickTimer();
    }

    private void tickTimer() {

        if (finished) {
            return;
        }

        Timer.TaskWait(1f, () -> {

            if (finished) {
                return;
            }

            secondsLeft -= 1;
            timerLabel.setText(String.valueOf(Math.max(secondsLeft, 0)));

            if (secondsLeft <= 0) {
                perdeuBoss();
                return;
            }

            tickTimer();
        });
    }

    // -------------------------
    // RESULTADO DO ENCONTRO
    // -------------------------

    private void venceuBoss() {

        finished = true;
        escapeBtn.setDisable(true);

        int day = App.jogador.get(0).getCurrentDay();
        App.jogador.get(0).setCurrentDay(day + 1);
        
        warningLabel.setText("SINAL RESTAURADO");
        instructionLabel.setText("");

        Platform.runLater(() -> {
            try {
                App.setRoot("View/Screen/StandBy");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void perdeuBoss() {

        finished = true;
        escapeBtn.setDisable(true);

        warningLabel.setText("SINAL PERDIDO");
        instructionLabel.setText("");

        Platform.runLater(() -> {
            try {
                App.setRoot("View/Screen/YouLose");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
