package com.example.calcularnotaspm.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.calcularnotaspm.R;

public class MainActivity extends AppCompatActivity {

    Button btnAbrirMateria;
    Button btnAbrirActividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrirMateria = findViewById(R.id.btnMateria);
        btnAbrirActividades = findViewById(R.id.btnActividades);

        btnAbrirMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Materias.class);
                startActivity(intent);
            }
        });
        btnAbrirActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui va abrir vista de actividades
            }
        });
    }
}