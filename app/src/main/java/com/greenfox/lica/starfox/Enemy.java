package com.greenfox.lica.starfox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by lica on 2018. 01. 10..
 */

public class Enemy {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 1;
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;
    private Rect detectCollision;


    public Enemy(Context context, int screenX, int screenY) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        this.maxX = screenX;
        this.maxY = screenY;
        this.minX = 0;
        this.minY = 0;

        Random generator = new Random();
        this.speed = generator.nextInt(6) + 10;
        this.x = screenX;
        this.y = generator.nextInt(maxY) - bitmap.getHeight();

        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(int playerSpeed) {
        x -= playerSpeed;
        x -= speed;

        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }

        if (x < minX - bitmap.getWidth()) {
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight();

        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    public void setX(int x) {
        this.x = x;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
