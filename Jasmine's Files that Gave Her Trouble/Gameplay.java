package edu.nau.flippyfish;

import android.app.Activity;
import android.os.Bundle;

public class Gameplay extends Activity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }
}
