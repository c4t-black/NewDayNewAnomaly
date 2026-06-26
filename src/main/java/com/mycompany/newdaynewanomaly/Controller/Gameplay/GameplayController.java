package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.Models.Entity;
import com.mycompany.newdaynewanomaly.Models.RoundResult;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GameplayController {


    @FXML private Button pcBtn;
    @FXML private AnchorPane itemsOverlay;
    @FXML private Label colaQtdLabel;
    @FXML private Label bigColaQtdLabel;
    @FXML private Label chipsQtdLabel;
    @FXML private Button useColaBtn;
    @FXML private Button useBigColaBtn;
    @FXML private Button useChipsBtn;

    @FXML private ImageView pessoa;
    @FXML private ImageView closingDoor;
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

    @FXML private Label glassesStatusLabel;
    @FXML private Button useGlassesBtn;

    @FXML private AnchorPane eyeOverlay;
    @FXML private ImageView eyeImageView;

    private static final float MAX_SANITY = 100f;

    private Entity entityAtual;
    private boolean verdictEscolhaAnomalia;

    private int anomalyNumber = 0;
    private int MaxAnomalyNumber;

    private int questionsLeft = 2;

    private final List<RoundResult> resultados = new ArrayList<>();
    private int moneyEarned = 0;


    // -------------------------
    // INICIALIZAÇÃO
    // -------------------------

    @FXML
    public void initialize() {
        

        MaxAnomalyNumber = 3 + App.jogador.get(0).getCurrentDay();

        anomalyNumber = 0;

        Timer.TaskWait(1, () -> speechRadio.setVisible(true));
        Timer.TaskWait(5, () -> speechRadio.setVisible(false));
        Timer.TaskWait(6, this::CnODoor);

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

        if (App.jogador.get(0).getSanity() <= 0) {

            App.setRoot("View/Screen/YouLose");

        }

        if (anomalyNumber >= MaxAnomalyNumber) {
            endWork();
            return;
        }

        bloquearBotoes();

        entityAtual = generateAnomaly();
        anomalyNumber += 1;


        Timer.TaskWait(0.5f, this::CnODoor);

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

    @FXML
    public void setDoorWork(String door) {

        if (door == null || door.isEmpty()) {

            closingDoor.setImage(null); // limpa a imagem
            return;
        }

        URL resource = getClass().getResource(door);
        if (resource == null) {
            System.err.println("Imagem não encontrada: " + door);
            return;
        }

        System.out.println(resource);
        closingDoor.setImage(new Image(resource.toExternalForm()));
    }

    @FXML
    public void CnODoor() {
        String path = "/com/mycompany/newdaynewanomaly/Images/Rooms/ClosingDoor.gif";
        URL test = getClass().getResource(path);
        System.out.println("URL da porta: " + test); // null = não achou

        setDoorWork(path);
        Timer.TaskWait(3, () -> setDoorWork(""));
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

        askBtn.setDisable(false);
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

            if (questionsLeft == 0) {

                askBtn.setDisable(true);

            }

        } else {

            mostrarPerguntaEResposta("Nome?", entityAtual.getNome());

            if (questionsLeft == 0) {

                askBtn.setDisable(true);

            }
        }


    }

    @FXML
    public void onAskIdade() {

        questionsLeft -= 1;

        fecharBotoesAsk();

        if (entityAtual.getAnomaly() && Randomizers.FalseInfo()) {

            mostrarPerguntaEResposta("Idade?", String.valueOf(Randomizers.getRandomAge()));

            if (questionsLeft == 0) {

                askBtn.setDisable(true);

            }

        } else {

            mostrarPerguntaEResposta("Idade?", String.valueOf(entityAtual.getIdade()));

            if (questionsLeft == 0) {

                askBtn.setDisable(true);

            }
        }


    }

    @FXML
    public void onAskCidade() {

        questionsLeft -= 1;

        fecharBotoesAsk();

        if (entityAtual.getAnomaly() && Randomizers.FalseInfo()) {

            mostrarPerguntaEResposta("Cidade de Origem?", Randomizers.getRandomCity());

            if (questionsLeft == 0) {

                askBtn.setDisable(true);

            }

        } else {

            mostrarPerguntaEResposta("Cidade de Origem?", entityAtual.getCidade());

            if (questionsLeft == 0) {

                askBtn.setDisable(true);

            }
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

            int ganho = 5;
            moneyEarned += ganho;

            int currentMoney = App.jogador.get(0).getMoney();
            App.jogador.get(0).setMoney(currentMoney + ganho);

        } else {

            float currentSanity = App.jogador.get(0).getSanity();
            App.jogador.get(0).setSanity(currentSanity - Randomizers.rollSanityNumber());

        }


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

    // -------------------------
// AREA DE ITEMS (PC)
// -------------------------

    @FXML
    public void openItems() {
        atualizarQuantidadesItens();
        itemsOverlay.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(250), itemsOverlay);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @FXML
    public void closeItems() {
        FadeTransition ft = new FadeTransition(Duration.millis(200), itemsOverlay);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> itemsOverlay.setVisible(false));
        ft.play();
    }

    @FXML
    public void useCola() {
        var player = App.jogador.get(0);
        if (player.getCola() <= 0) return;

        player.setCola(player.getCola() - 1);
        recuperarSanidade(10);
        atualizarQuantidadesItens();
    }

    @FXML
    public void useBigCola() {
        var player = App.jogador.get(0);
        if (player.getBigCola() <= 0) return;

        player.setBigCola(player.getBigCola() - 1);
        recuperarSanidade(20);
        atualizarQuantidadesItens();
    }

    @FXML
    public void useChips() {
        var player = App.jogador.get(0);
        if (player.getChips() <= 0) return;

        player.setChips(player.getChips() - 1);
        recuperarSanidade(20);
        atualizarQuantidadesItens();
    }

    private void recuperarSanidade(float quantidade) {
        var player = App.jogador.get(0);
        float novaSanidade = Math.min(player.getSanity() + quantidade, MAX_SANITY);
        player.setSanity(novaSanidade);
    }

    private void atualizarQuantidadesItens() {
        var player = App.jogador.get(0);

        colaQtdLabel.setText("x" + player.getCola());
        bigColaQtdLabel.setText("x" + player.getBigCola());
        chipsQtdLabel.setText("x" + player.getChips());

        useColaBtn.setDisable(player.getCola() <= 0);
        useBigColaBtn.setDisable(player.getBigCola() <= 0);
        useChipsBtn.setDisable(player.getChips() <= 0);

        boolean temOculos = player.isGlasses();
        glassesStatusLabel.setText(temOculos ? "Disponível" : "Não possui");
        useGlassesBtn.setDisable(!temOculos);
    }

    @FXML
    public void useGlasses() {
        var player = App.jogador.get(0);

        if (!player.isGlasses()) {
            return;
        }

        abrirVisaoOculos();
    }

    private void abrirVisaoOculos() {
        String link = entityAtual.getEyes();
        eyeImageView.setImage(new Image(getClass().getResource(link).toExternalForm()));

        eyeOverlay.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(250), eyeOverlay);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @FXML
    public void closeEyeView() {
        FadeTransition ft = new FadeTransition(Duration.millis(200), eyeOverlay);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> eyeOverlay.setVisible(false));
        ft.play();
    }
}