package com.example.arthur.cabraldrums11.Class.Principais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.arthur.cabraldrums11.R;

public class BancoSSActivity extends AppCompatActivity {

    Button btBcos[] = new Button[11];
    public static CharSequence clicBanco = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco_ss);

        btBcos[1] = findViewById(R.id.btBco1);
        btBcos[2] = findViewById(R.id.btBco2);
        btBcos[3] = findViewById(R.id.btBco3);
        btBcos[4] = findViewById(R.id.btBco4);
        btBcos[5] = findViewById(R.id.btBco5);
        btBcos[6] = findViewById(R.id.btBco6);
        btBcos[7] = findViewById(R.id.btBco7);
        btBcos[8] = findViewById(R.id.btBco8);
        btBcos[9] = findViewById(R.id.btBco9);
        btBcos[10] = findViewById(R.id.btBco10);

        btBcos[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[1].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[2].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[3].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[4].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[5].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[6].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[7].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[8].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[9].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });
        btBcos[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicBanco = btBcos[10].getText();
                startActivity(new Intent(getBaseContext(), PadActivity.class));
            }
        });

    }

 }
