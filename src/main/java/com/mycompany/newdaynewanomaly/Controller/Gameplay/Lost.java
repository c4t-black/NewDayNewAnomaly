package com.mycompany.newdaynewanomaly.Controller.Gameplay;

import com.mycompany.newdaynewanomaly.App;
import com.mycompany.newdaynewanomaly.DAO.PlayerDAO;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Lost implements Initializable {

    @FXML private Label titleLabel;
    @FXML private Label subtitleLabel;
    @FXML private Button menuButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playFlickerAnimation();
        playFadeInAnimation();
        applyButtonHover();
    }

    private void playFlickerAnimation() {
        Timeline flicker = new Timeline(
            new KeyFrame(Duration.seconds(0),    new KeyValue(titleLabel.opacityProperty(), 1.0)),
            new KeyFrame(Duration.seconds(3.2),  new KeyValue(titleLabel.opacityProperty(), 1.0)),
            new KeyFrame(Duration.seconds(3.25), new KeyValue(titleLabel.opacityProperty(), 0.5)),
            new KeyFrame(Duration.seconds(3.3),  new KeyValue(titleLabel.opacityProperty(), 1.0)),
            new KeyFrame(Duration.seconds(3.5),  new KeyValue(titleLabel.opacityProperty(), 0.7)),
            new KeyFrame(Duration.seconds(3.55), new KeyValue(titleLabel.opacityProperty(), 1.0)),
            new KeyFrame(Duration.seconds(4.0),  new KeyValue(titleLabel.opacityProperty(), 1.0))
        );
        flicker.setCycleCount(Timeline.INDEFINITE);
        flicker.play();
    }

    private void playFadeInAnimation() {
        titleLabel.setOpacity(0);
        subtitleLabel.setOpacity(0);
        menuButton.setOpacity(0);

        FadeTransition fadeTitle = new FadeTransition(Duration.millis(800), titleLabel);
        fadeTitle.setFromValue(0); fadeTitle.setToValue(1);
        fadeTitle.setDelay(Duration.millis(200));

        FadeTransition fadeSub = new FadeTransition(Duration.millis(600), subtitleLabel);
        fadeSub.setFromValue(0); fadeSub.setToValue(1);
        fadeSub.setDelay(Duration.millis(900));

        FadeTransition fadeBtn = new FadeTransition(Duration.millis(600), menuButton);
        fadeBtn.setFromValue(0); fadeBtn.setToValue(1);
        fadeBtn.setDelay(Duration.millis(1400));

        fadeTitle.play();
        fadeSub.play();
        fadeBtn.play();
    }

    private void applyButtonHover() {
        String base = "-fx-background-color: transparent; -fx-border-color: #5a0011; " +
                      "-fx-border-width: 1; -fx-text-fill: #C0001A; " +
                      "-fx-font-family: 'Bookman Old Style'; -fx-font-size: 13px; -fx-cursor: hand;";
        String hover = "-fx-background-color: #C0001A; -fx-border-color: #C0001A; " +
                       "-fx-border-width: 1; -fx-text-fill: #000000; " +
                       "-fx-font-family: 'Bookman Old Style'; -fx-font-size: 13px; -fx-cursor: hand;";

        menuButton.setOnMouseEntered(e -> menuButton.setStyle(hover));
        menuButton.setOnMouseExited(e -> menuButton.setStyle(base));
    }

    @FXML
    private void menu() throws IOException {
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