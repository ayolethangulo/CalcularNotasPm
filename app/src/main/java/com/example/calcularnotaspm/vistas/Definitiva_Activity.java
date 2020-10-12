package com.example.calcularnotaspm.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.calcularnotaspm.R;
import com.example.calcularnotaspm.modelo.Materia;
import com.example.calcularnotaspm.modelo.Nota_Actividad;
import com.example.calcularnotaspm.modelo.daoMateria;

import java.util.ArrayList;

public class Definitiva_Activity extends AppCompatActivity {

    Spinner spnlistMateria, spnlistCorte;
    ListView listActividades;
    TextView definitaxMateria, definitivaxCorte;
    daoMateria daoM;
    AdaptadorActividades adaptadorActividades;
    ArrayList<Nota_Actividad> listaActividadesxMateria, listaxCorte;
    ArrayList<Materia> listaMaterias;
    ArrayList<String> listaSpinnerMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definitiva_);

        spnlistMateria = (Spinner) findViewById(R.id.spnDefinitivaXMateria);
        spnlistCorte = (Spinner) findViewById(R.id.spnDefinitivaXCorte);
        listActividades = (ListView) findViewById(R.id.listaActividadesxMateria);
        definitaxMateria = (TextView) findViewById(R.id.txtDefinitivaxmateria);
        definitivaxCorte = (TextView) findViewById(R.id.txtDefinitivaxcorte);

        daoM = new daoMateria(this);
        listaMaterias =daoM.verTodosM();
        obtenerLista();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaSpinnerMaterias);
        spnlistMateria.setAdapter(adaptador);

        spnlistMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    filtrarlista();
                }else {
                    listaActividadesxMateria = new ArrayList<Nota_Actividad>();
                    listaActividadesxMateria = daoM.verTodosN();
                    adaptadorActividades = new AdaptadorActividades(Definitiva_Activity.this, listaActividadesxMateria,daoM);
                    listActividades = findViewById(R.id.listaActividadesxMateria);
                    listActividades.setAdapter(adaptadorActividades);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnlistCorte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    filtrarListaxCorte();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void obtenerLista() {
        listaSpinnerMaterias = new ArrayList<String>();
        listaSpinnerMaterias.add("Seleccione una materia");

        for (int i=0;i<listaMaterias.size();i++){
            listaSpinnerMaterias.add(String.valueOf(listaMaterias.get(i).getIdMateria()));
        }
    }
    private void filtrarlista(){
        listaActividadesxMateria = daoM.verTodosxMateriaN(Integer.parseInt(spnlistMateria.getSelectedItem().toString()));
        adaptadorActividades = new AdaptadorActividades(Definitiva_Activity.this, listaActividadesxMateria,daoM);
        listActividades = findViewById(R.id.listaActividadesxMateria);
        listActividades.setAdapter(adaptadorActividades);
    }
    private void filtrarListaxCorte(){
        int corte = 0;
        if (spnlistCorte.getSelectedItem().toString().equals("Primer corte")){
            corte = 1;
        }else if (spnlistCorte.getSelectedItem().toString().equals("Segundo corte")){
            corte = 2;
        }else {
            corte=3;
        }
        listaxCorte = new ArrayList<Nota_Actividad>();
        for (int i=0;i<listaActividadesxMateria.size();i++){
           if (listaActividadesxMateria.get(i).getNumCorte() == corte){
               listaxCorte.add(listaActividadesxMateria.get(i));
           }
        }
        adaptadorActividades = new AdaptadorActividades(Definitiva_Activity.this, listaxCorte,daoM);
        listActividades = findViewById(R.id.listaActividadesxMateria);
        listActividades.setAdapter(adaptadorActividades);
    }
}