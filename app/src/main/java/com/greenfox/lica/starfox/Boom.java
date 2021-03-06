package com.greenfox.lica.starfox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by nezih on 10-Jan-18.
 */

public class Boom {

    private Bitmap bitmap;
    private int x;
    private int y;

    public Boom(Context context) {
        this.bitmap = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.boom);
        this.x = -250;
        this.y = -250;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
