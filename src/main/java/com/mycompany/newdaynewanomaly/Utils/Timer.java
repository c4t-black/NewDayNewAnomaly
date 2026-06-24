package com.mycompany.newdaynewanomaly.Utils;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Timer {

    public static void TaskWait (Float time, Runnable action) {

        PauseTransition pause = new PauseTransition( Duration.seconds ( time ) );
        pause.setOnFinished(e -> action.run() );
        pause.play();

    }

}
