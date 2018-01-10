package com.greenfox.lica.starfox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by lica on 2018. 01. 10..
 */

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    private Player player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Enemy[] enemies;
    private int enemyCount = 3;
    private ArrayList<Star> stars = new
            ArrayList<Star>();
    private Boom boom;
    int screenX;
    int countMisses;
    boolean flag;
    private boolean isGameOver;

   public GameView(Context context, int screenX, int screenY) {
       super(context);
       this.player = new Player(context, screenX, screenY);
       this.surfaceHolder = getHolder();
       this.paint = new Paint();
       this.screenX = screenX;
       this.countMisses = 0;
       this.isGameOver = false;

       int starNums = 100;
       for (int i = 0; i < starNums; i++) {
           Star s = new Star(screenX, screenY);
           stars.add(s);
       }

       enemies = new Enemy[enemyCount];
       for(int j=0; j<enemyCount; j++){
           enemies[j] = new Enemy(context, screenX, screenY);
       }
       boom = new Boom(context);
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        player.update();
        boom.setX(-250);
        boom.setY(-250);

        for (Star s : stars) {
            s.update(player.getSpeed());
        }

        for (int j = 0; j < enemyCount; j++) {
            if (enemies[j].getX() == screenX) {
                flag = true;
            }
        }


        for (int i = 0; i < enemyCount; i++) {
            enemies[i].update(player.getSpeed());
            if (Rect.intersects(player.getDetectCollision(), enemies[i].getDetectCollision())) {
                boom.setX(enemies[i].getX());
                boom.setY(enemies[i].getY());
                enemies[i].setX(-500);
            } else {
                if (flag) {
                    if (player.getDetectCollision().exactCenterX() >= enemies[i].getDetectCollision().exactCenterX()) {
                        countMisses++;
                        flag = false;
                        if (countMisses == 10) {
                            playing = false;
                            isGameOver = true;
                        }
                    }
                }
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.rgb(34,139,34));

            paint.setColor(Color.rgb(204,204,0));
            paint.setTextSize(20);

            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }

            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);

            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }

            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );

            if (isGameOver) {
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Retake exam!",canvas.getWidth()/2,yPos,paint);
            }
          
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
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
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
        }
        return true;
    }
    
}
