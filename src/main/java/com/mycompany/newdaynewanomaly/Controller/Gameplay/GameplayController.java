package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.Models.Entity;
import com.mycompany.newdaynewanomaly.Models.Player;
import com.mycompany.newdaynewanomaly.Utils.Randomizers;
import com.mycompany.newdaynewanomaly.Utils.Timer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class GameplayController {

    @FXML private ImageView pessoa;
    @FXML private Label speechPlayer;
    @FXML private Label speechCreature;
    @FXML private Label speechRadio;

    @FXML private Button papersBtn;
    @FXML private AnchorPane papersOverlay;

    @FXML private Label papersNome;
    @FXML private Label papersCidade;
    @FXML private Label papersIdade;
    @FXML private Label papersGender;

    // Botões de pergunta
    @FXML private Button askBtn;
    @FXML private Button askNomeBtn;
    @FXML private Button askIdadeBtn;
    @FXML private Button askCidadeBtn;

    @FXML private Button verdictBtn;
    @FXML private AnchorPane verdictOverlay;
    @FXML private Label verdictConfirmLabel;
    @FXML private Button verdictCancelBtn;
    @FXML private Button verdictConfirmBtn;
    @FXML private Button btnNormal;
    @FXML private Button btnAnomalia;

    private int AnomalyNumber = 0;

    private Entity entityAtual;

    @FXML
    private void endWork() throws IOException {
        App.setRoot("View/House/Room");
    }

    @FXML
    public void initialize() {

        entityAtual = generateAnomaly();
        AnomalyNumber += 1;

        Timer.TaskWait(1, () -> speechRadio.setVisible(true));
        Timer.TaskWait(5, () -> speechRadio.setVisible(false));
        Timer.TaskWait(7, () -> setImageAnomaly(entityAtual));

        Timer.TaskWait(2, () -> {
            papersBtn.setVisible(true);
            askBtn.setVisible(true);
            verdictBtn.setVisible(true);
        });
    }

    @FXML
    public void nextPlay() throws IOException {

        if (AnomalyNumber > 4) {

            endWork();

        }

        AnomalyNumber += 1;

        entityAtual = generateAnomaly();

        Timer.TaskWait(3, () -> setImageAnomaly(entityAtual));
    }

    @FXML
    public void setImageAnomaly(Entity e) {

        String link = e.getLinkImage();
        pessoa.setImage(new Image(getClass().getResource(link).toExternalForm()));
    }

    // Abre ou fecha os botões de pergunta
    @FXML
    public void onAsk() {
        boolean visible = askNomeBtn.isVisible();
        askNomeBtn.setVisible(!visible);
        askIdadeBtn.setVisible(!visible);
        askCidadeBtn.setVisible(!visible);
    }

    // Pergunta o Nome
    @FXML
    public void onAskNome() {
        fecharBotoesAsk();
        mostrarPerguntaEResposta("Nome?", entityAtual.getNome());
    }

    // Pergunta a Idade
    @FXML
    public void onAskIdade() {
        fecharBotoesAsk();
        mostrarPerguntaEResposta("Idade?", String.valueOf(entityAtual.getIdade()));
    }

    // Pergunta a Cidade
    @FXML
    public void onAskCidade() {
        fecharBotoesAsk();
        mostrarPerguntaEResposta("Cidade de Origem?", entityAtual.getCidade());
    }

    // Exibe a pergunta no speechPlayer, depois a resposta no speechCreature
    private void mostrarPerguntaEResposta(String pergunta, String resposta) {
        // Mostra a pergunta do player
        speechPlayer.setText(pergunta);
        speechPlayer.setVisible(true);

        // Após 2s esconde a pergunta e mostra a resposta da criatura
        Timer.TaskWait(2, () -> {
            speechPlayer.setVisible(false);

            speechCreature.setText(resposta);
            speechCreature.setVisible(true);

            // Após 3s esconde a resposta
            Timer.TaskWait(3, () -> speechCreature.setVisible(false));
        });
    }

    private void fecharBotoesAsk() {
        askNomeBtn.setVisible(false);
        askIdadeBtn.setVisible(false);
        askCidadeBtn.setVisible(false);
    }

    // Papers
    @FXML
    public void openPapers() {
        papersNome.setText(entityAtual.getNome());
        papersCidade.setText(entityAtual.getCidade());
        papersIdade.setText(String.valueOf(entityAtual.getIdade()));
        papersGender.setText(String.valueOf(entityAtual.getGender()));

        papersOverlay.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(250), papersOverlay);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @FXML
    public void closePapers() {
        FadeTransition ft = new FadeTransition(Duration.millis(200), papersOverlay);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> papersOverlay.setVisible(false));
        ft.play();
    }

    private boolean verdictEscolhaAnomalia;

// No initialize(), libere o botão quando quiser:
// Timer.TaskWait(2, () -> verdictBtn.setVisible(true));

    @FXML
    public void openVerdict() {
        resetVerdict();
        verdictOverlay.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(250), verdictOverlay);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @FXML
    public void closeVerdict() {
        FadeTransition ft = new FadeTransition(Duration.millis(200), verdictOverlay);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> verdictOverlay.setVisible(false));
        ft.play();
    }

    @FXML
    public void onVerdictNormal() {
        verdictEscolhaAnomalia = false;
        verdictConfirmLabel.setText("Confirmar que o indivíduo é normal e permitir a entrada?");
        verdictConfirmLabel.setTextFill(javafx.scene.paint.Color.web("#97C459"));
        verdictConfirmBtn.setStyle("-fx-background-color: #3B6D11; -fx-text-fill: white; -fx-border-width: 0;");
        mostrarConfirmacaoVerdict();
    }

    @FXML
    public void onVerdictAnomalia() {
        verdictEscolhaAnomalia = true;
        verdictConfirmLabel.setText("Confirmar que o indivíduo é uma anomalia e negar a entrada?");
        verdictConfirmLabel.setTextFill(javafx.scene.paint.Color.web("#F09595"));
        verdictConfirmBtn.setStyle("-fx-background-color: #A32D2D; -fx-text-fill: white; -fx-border-width: 0;");
        mostrarConfirmacaoVerdict();
    }

    @FXML
    public void onVerdictCancel() {
        resetVerdict();
    }

    @FXML
    public void onVerdictConfirm() throws IOException {

        if (verdictEscolhaAnomalia == entityAtual.getAnomaly()) {

            int currentMoney = App.jogador.get(0).getMoney();

            App.jogador.get(0).setMoney(currentMoney + 35);


        } else {

            float currentSanity = App.jogador.get(0).getSanity();

            App.jogador.get(0).setSanity(currentSanity - 10);

        }

        nextPlay();

        closeVerdict();


    }

    private void mostrarConfirmacaoVerdict() {
        verdictConfirmLabel.setVisible(true);
        verdictCancelBtn.setVisible(true);
        verdictConfirmBtn.setVisible(true);
        btnNormal.setOpacity(verdictEscolhaAnomalia ? 0.4 : 1.0);
        btnAnomalia.setOpacity(verdictEscolhaAnomalia ? 1.0 : 0.4);
    }

    private void resetVerdict() {
        verdictConfirmLabel.setVisible(false);
        verdictCancelBtn.setVisible(false);
        verdictConfirmBtn.setVisible(false);
        btnNormal.setOpacity(1.0);
        btnAnomalia.setOpacity(1.0);
    }

    public Entity generateAnomaly () {

        Entity e = new Entity(Randomizers.getRandomBoolean());

        return e;

    }
}