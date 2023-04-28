package com.example.adara.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Smile extends Of {
    protected float x; // координаты
    protected float y;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка
    public static int s = 0;

    void init(Context context) {
        Bitmap pic = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                pic, (int)(size * MainG.F_W), (int)(size * MainG.F_H), false);
        pic.recycle();
    }

    public Smile(Context context) {
        if(s == 0)
            bitmapId = R.drawable.y_1;
        if(s == 1)
            bitmapId = R.drawable.r_1;
        if(s == 2)
            bitmapId = R.drawable.g_1;
        size = 5;
        x=7;
        y=MainG.maxY - size - 1;
        speed = (float) 0.2;

        init(context); // инициализируем корабль
    }

    @Override
    public void update() {
        if(Main3Activity.isLB && x >= 0){
            x -= speed;
        }
        if(Main3Activity.isRB && x <= MainG.maxX - 5){
            x += speed;
        }
    }
    void drow(Paint paint, Canvas canvas){
        canvas.drawBitmap(bitmap, x*MainG.F_W, y*MainG.F_H, paint);
    }
}
