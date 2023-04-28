package com.example.adara.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Obj extends Of {
    private int radius = 2; // радиус
    private float minSpeed = (float) 0.2; // минимальная скорость
    private float maxSpeed = (float) 1.0; // максимальная скорость
    public static int c;
    protected float x; // координаты
    protected float y;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap;

    void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap pic = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                pic, (int)(size * MainG.F_W), (int)(size * MainG.F_H), false);
        pic.recycle();
    }

    public Obj(Context context) {
        Random random = new Random();
        Random r = new Random();
        c = r.nextInt(3);
        if(c == 0)
            bitmapId = R.drawable.g;
        if(c == 1)
            bitmapId = R.drawable.y;
        if(c == 2)
            bitmapId = R.drawable.r;
        y=0;
        x = random.nextInt(MainG.maxX) - radius;
        size = radius*2;
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }

    @Override
    public void update() {
        y += speed;
    }

    public boolean isCollision(float smileX, float smileY, float smileSize) {
        if(!(((x+size) < smileX)||(x > (smileX+smileSize))||((y+size) < smileY)||(y > (smileY+smileSize))) && Smile.s == Obj.c){
            Random ri = new Random();
            Smile.s = ri.nextInt(3);
            return false;
        }
        else{
            if(!(((x+size) < smileX)||(x > (smileX+smileSize))||((y+size) < smileY)||(y > (smileY+smileSize))) && Smile.s != Obj.c)
                return true;
            else
                return false;
        }
    }
    void drow(Paint paint, Canvas canvas){ // рисуем картинку
        canvas.drawBitmap(bitmap, x*MainG.F_W, y*MainG.F_H, paint);
    }
}