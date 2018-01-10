package com.greenfox.lica.starfox;

import java.util.Random;

/**
 * Created by nezih on 10-Jan-18.
 */

public class Star {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Star(int screenX, int screenY) {
        this.maxX = screenX;
        this.maxY = screenY;
        this.minX = 0;
        this.minY = 0;
        Random generator = new Random();
        this.speed = generator.nextInt(10);
        this.x = generator.nextInt(maxX);
        this.y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed) {
        x -= playerSpeed;
        x -= speed;
        if (x < 0) {
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(15);
        }
    }

    public float getStarWidth() {
        float minX = 4.0f;
        float maxX = 12.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
