package com.example.calcularnotaspm.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.calcularnotaspm.R;
import com.example.calcularnotaspm.modelo.Materia;
import com.example.calcularnotaspm.modelo.daoMateria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Materias extends AppCompatActivity {

    ListView listMateria;
    FloatingActionButton btnAbrirDialogoM;
    daoMateria daoM;
    AdaptadorMateria adaptador;
    ArrayList<Materia> listaMateria;
    Materia m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        daoM = new daoMateria(this);
        listaMateria = daoM.verTodosM();
        adaptador = new AdaptadorMateria(this, listaMateria,daoM);
        listMateria = findViewById(R.id.listaMateria);
        btnAbrirDialogoM = findViewById(R.id.btnAbrirDialogoM);
        listMateria.setAdapter(adaptador);

        listMateria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        btnAbrirDialogoM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialogo de agregar materia
                final Dialog dialogo = new Dialog(Materias.this);
                dialogo.setTitle("Nuevo Materia");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo_materia);
                dialogo.show();
                final EditText nombreMateria = (EditText) dialogo.findViewById(R.id.txtDNombreM);
                Button btnAgregarMateria = (Button)dialogo.findViewById(R.id.btnAgregarMateria);
                Button btnDcancelar = (Button) dialogo.findViewById(R.id.btnD_cancelar);

                btnAgregarMateria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            //aqui puse para ver si funciona
                            //lista = new ArrayList<Contacto>();
                            m = new Materia(nombreMateria.getText().toString());
                            daoM.insertarM(m);
                            listaMateria=daoM.verTodosM();
                            adaptador.notifyDataSetChanged();
                            dialogo.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnDcancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogo.dismiss();
                    }
                });
            }
        });
    }
}