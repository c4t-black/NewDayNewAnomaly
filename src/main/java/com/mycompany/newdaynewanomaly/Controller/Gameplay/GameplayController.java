package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.Models.Entity;
import com.mycompany.newdaynewanomaly.Models.RoundResult;
import com.mycompany.newdaynewanomaly.Utils.Randomizers;
import com.mycompany.newdaynewanomaly.Utils.Timer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private Entity entityAtual;
    private boolean verdictEscolhaAnomalia;
    private int anomalyNumber = 0;

    private int questionsLeft = 2;

    private final List<RoundResult> resultados = new ArrayList<>();
    private int moneyEarned = 0;


    // -------------------------
    // INICIALIZAÇÃO
    // -------------------------

    @FXML
    public void initialize() {
        anomalyNumber = 0;

        Timer.TaskWait(1, () -> speechRadio.setVisible(true));
        Timer.TaskWait(5, () -> speechRadio.setVisible(false));

        Timer.TaskWait(7, () -> {
            entityAtual = generateAnomaly();
            anomalyNumber += 1;
            setImageAnomaly(entityAtual);
            liberarBotoes();
        });
    }

    // -------------------------
    // FLUXO DE JOGO
    // -------------------------

    @FXML
    public void nextPlay() throws IOException {

        questionsLeft = 2;

        if (anomalyNumber >= 4) {
            endWork();
            return;
        }

        bloquearBotoes();

        entityAtual = generateAnomaly();
        anomalyNumber += 1;

        Timer.TaskWait(3, () -> {
            setImageAnomaly(entityAtual);
            liberarBotoes();
        });
    }

    @FXML
    public void setImageAnomaly(Entity e) {
        String link = e.getLinkImage();
        pessoa.setImage(new Image(getClass().getResource(link).toExternalForm()));
    }

    public Entity generateAnomaly() {
        return new Entity(Randomizers.getRandomChance());
    }

    @FXML
    private void endWork() throws IOException {

        App.lastRoundResults = new ArrayList<>(resultados);
        App.lastMoneyEarned = moneyEarned;

        App.setRoot("View/House/Table");
    }

    // -------------------------
    // BOTÕES DE INTERAÇÃO
    // -------------------------

    private void liberarBotoes() {
        papersBtn.setVisible(true);
        askBtn.setVisible(true);
        verdictBtn.setVisible(true);
    }

    private void bloquearBotoes() {
        papersBtn.setVisible(false);
        askBtn.setVisible(false);
        verdictBtn.setVisible(false);
        askNomeBtn.setVisible(false);
        askIdadeBtn.setVisible(false);
        askCidadeBtn.setVisible(false);
        speechPlayer.setVisible(false);
        speechCreature.setVisible(false);
    }

    // -------------------------
    // SISTEMA DE PERGUNTAS
    // -------------------------

    @FXML
    public void onAsk() {

        if (questionsLeft == 0) {

            return;

        }

        boolean visible = askNomeBtn.isVisible();
        askNomeBtn.setVisible(!visible);
        askIdadeBtn.setVisible(!visible);
        askCidadeBtn.setVisible(!visible);
    }

    @FXML
    public void onAskNome() {

        questionsLeft -= 1;

        fecharBotoesAsk();

        if (entityAtual.getAnomaly() && Randomizers.FalseInfo()) {

            mostrarPerguntaEResposta("Nome?", Randomizers.getRandomName(Randomizers.getRandomGender()));

        } else {

            mostrarPerguntaEResposta("Nome?", entityAtual.getNome());

        }


    }

    @FXML
    public void onAskIdade() {

        questionsLeft -= 1;

        fecharBotoesAsk();

        if (entityAtual.getAnomaly() && Randomizers.FalseInfo()) {

            mostrarPerguntaEResposta("Idade?", String.valueOf(Randomizers.getRandomAge()));

        } else {

            mostrarPerguntaEResposta("Idade?", String.valueOf(entityAtual.getIdade()));

        }


    }

    @FXML
    public void onAskCidade() {

        questionsLeft -= 1;

        fecharBotoesAsk();

        if (entityAtual.getAnomaly() && Randomizers.FalseInfo()) {

            mostrarPerguntaEResposta("Cidade de Origem?", Randomizers.getRandomCity());

        } else {

            mostrarPerguntaEResposta("Cidade de Origem?", entityAtual.getCidade());

        }


    }

    private void mostrarPerguntaEResposta(String pergunta, String resposta) {

        speechPlayer.setText(pergunta);
        speechPlayer.setVisible(true);

        Timer.TaskWait(2, () -> {
            speechPlayer.setVisible(false);


            speechCreature.setText(resposta);


            speechCreature.setVisible(true);

            Timer.TaskWait(3, () -> speechCreature.setVisible(false));
        });
    }

    private void fecharBotoesAsk() {
        askNomeBtn.setVisible(false);
        askIdadeBtn.setVisible(false);
        askCidadeBtn.setVisible(false);
    }

    // -------------------------
    // PAINEL DE PAPERS
    // -------------------------

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

    // -------------------------
    // PAINEL DE VEREDICTO
    // -------------------------

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

        boolean acertou = verdictEscolhaAnomalia == entityAtual.getAnomaly();

        if (acertou) {

            moneyEarned += 35;

            int currentMoney = App.jogador.get(0).getMoney();
            App.jogador.get(0).setMoney(currentMoney + 35);

        } else {

            float currentSanity = App.jogador.get(0).getSanity();
            App.jogador.get(0).setSanity(currentSanity - 10);

        }


        Timer.TaskWait(1, () -> pessoa.setImage(new Image(getClass().getResource("").toExternalForm())) );


        resultados.add(new RoundResult(
                entityAtual.getNome(),
                entityAtual.getAnomaly(),
                acertou
        ));

        closeVerdict();
        nextPlay();
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
}