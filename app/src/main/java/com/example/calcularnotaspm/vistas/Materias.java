package com.example.calcularnotaspm.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.calcularnotaspm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Materias extends AppCompatActivity {

    ListView listaMateria;
    FloatingActionButton btnAbrirDialogoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        listaMateria = findViewById(R.id.listaMateria);
        btnAbrirDialogoM = findViewById(R.id.btnAbrirDialogoM);

        btnAbrirDialogoM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}