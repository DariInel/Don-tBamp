package com.example.adara.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.core.content.FileProvider;

public class MainG extends SurfaceView implements Runnable{
        public static int n = 0;
        public static int Mn = 0;
    public static int maxX = 20;
    public static int maxY = 28;
    public static float F_W = 0;
    public static float F_H = 0;
    private Smile smile;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Obj> ths = new ArrayList<>(); // тут будут харанится астероиды
    private boolean firstTime = true;
    private boolean ActivityRun = true;
    private final int time_P = 50; // время через которое появляются астероиды (в итерациях)
    private int Present_ = 0;
        TextView text = findViewById(R.id.tw);

    public MainG(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (ActivityRun) {
            update();
            draw();
            checkCollision();
            checkObject();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            smile.update();
            for (Obj th : ths) {
                th.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                F_W = surfaceHolder.getSurfaceFrame().width()/maxX;
                F_H = surfaceHolder.getSurfaceFrame().height()/maxY;

                smile = new Smile(getContext()); // добавляем корабль
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.WHITE); // заполняем фон чёрным

            smile.drow(paint, canvas); // рисуем корабль

            for(Obj th: ths){ // рисуем астероиды
                th.drow(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision(){
        for (Obj th : ths) {
            if(th.isCollision(smile.x, smile.y, smile.size)){
                // игрок проиграл
                if(n > Mn)
                    Mn = n;
                ActivityRun = false;
                Main3Activity m = new Main3Activity();
                m.Los();
                // останавливаем игру
            }
            else{
                n ++;
                text.setText(Integer.toString(n));
            }
        }
    }

    private void checkObject(){
        if(Present_ >= time_P){
            Obj th = new Obj(getContext());
            ths.add(th);
            Present_ = 0;
        }else{
            Present_ ++;
        }
    }

}