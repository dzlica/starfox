package com.greenfox.lica.starfox;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by lica on 2018. 01. 10..
 */

public class GameView extends SurfaceView implements Runnable {
    volatile boolean playing;
    private Thread gameThread = null;

    public GameView(Context context) {
        super(context);
    }

    @Override
    public void run() {
        while (playing) {
            //to update the frame
            update();

            //to draw the frame
            draw();

            //to control
            control();
        }
    }

    private void update() {

    }

    private void draw() {

    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            //stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
