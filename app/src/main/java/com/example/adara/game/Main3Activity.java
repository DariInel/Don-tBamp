package com.example.adara.game;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.FileProvider;

public class Main3Activity extends AppCompatActivity {

    public static boolean isLB = false;
    public static boolean isRB = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        MainG field = new MainG(this); // создаём gameView

        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.li); // находим gameLayout
        gameLayout.addView(field); // и добавляем в него gameView

        Button leftButton = (Button) findViewById(R.id.button1); // находим кнопки
        Button rightButton = (Button) findViewById(R.id.button2);


        leftButton.setOnTouchListener((View.OnTouchListener) this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)
        rightButton.setOnTouchListener((View.OnTouchListener) this);
    }
    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { // определяем какая кнопка
            case R.id.button1:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isLB= true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLB= false;
                        break;
                }
                break;
            case R.id.button2:
                switch (motion.getAction()) { // определяем нажата или отпущен
                    case MotionEvent.ACTION_DOWN:
                        isRB = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRB = false;
                        break;
                }
                break;
        }
        return true;
    }
    public void Los(){
        Intent intent = new Intent(getApplicationContext(), Losing.class);
        startActivity(intent);
    }
    }

