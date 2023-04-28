package com.example.adara.game;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class Losing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losing);
        TextView rec = findViewById(R.id.T1);

        rec.setText("ВЫ ПРОИГРАЛИ" + "\n" +
                "Текущий результат: " + MainG.n + "\n" +
                "Лучший результат: " + MainG.Mn);
    }
}
