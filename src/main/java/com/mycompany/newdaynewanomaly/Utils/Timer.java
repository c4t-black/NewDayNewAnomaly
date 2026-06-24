package com.mycompany.newdaynewanomaly.Utils;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Timer {

    public static void TaskWait(float time, Runnable action) {
        PauseTransition pause = new PauseTransition(Duration.seconds(time));
        pause.setOnFinished(e -> action.run());
        pause.play();
    }

    public static void TaskSequence(List<SimpleEntry<Float, Runnable>> steps) {
        TaskSequence(steps, null); // sem ação final, comportamento antigo
    }

    public static void TaskSequence(List<SimpleEntry<Float, Runnable>> steps, Runnable onFinished) {
        SequentialTransition sequence = new SequentialTransition();

        for (SimpleEntry<Float, Runnable> step : steps) {
            PauseTransition pause = new PauseTransition(Duration.seconds(step.getKey()));
            pause.setOnFinished(e -> step.getValue().run());
            sequence.getChildren().add(pause);
        }

        if (onFinished != null) {
            sequence.setOnFinished(e -> onFinished.run());
        }

        sequence.play();
    }
}