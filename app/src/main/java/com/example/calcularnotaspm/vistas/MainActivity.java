package com.example.calcularnotaspm.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.calcularnotaspm.R;

public class MainActivity extends AppCompatActivity {

    Button btnAbrirMateria, btnAbrirActividades, btnAbrirDefinitiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrirMateria = findViewById(R.id.btnMateria);
        btnAbrirActividades = findViewById(R.id.btnActividades);
        btnAbrirDefinitiva = findViewById(R.id.btnDefinitiva);

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
                Intent intent = new Intent(MainActivity.this, Notas_Activity.class);
                startActivity(intent);
            }
        });
        btnAbrirDefinitiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Definitiva_Activity.class);
                startActivity(intent);
            }
        });
    }
}