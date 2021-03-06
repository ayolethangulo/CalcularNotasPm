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
    TextView definitaxMateria, definitivaCorte1, definitivaCorte2, definitivaCorte3, nombreMateriaS;
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
        nombreMateriaS = (TextView)findViewById(R.id.txtnombreMateriaSelccion);
        spnlistCorte = (Spinner) findViewById(R.id.spnDefinitivaXCorte);
        listActividades = (ListView) findViewById(R.id.listaActividadesxMateria);
        definitivaCorte1 = (TextView) findViewById(R.id.txtDefinitivaxcorte1);
        definitivaCorte2 = (TextView) findViewById(R.id.txtDefinitivacorte2);
        definitivaCorte3 = (TextView) findViewById(R.id.txtDefinitivacorte3);
        definitaxMateria = (TextView) findViewById(R.id.txtDefinitivaxmateria);


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
            listaSpinnerMaterias.add(String.valueOf(listaMaterias.get(i).getIdMateria())+";"+listaMaterias.get(i).getNombreMateria());
        }
    }
    private void filtrarlista(){
        String id = spnlistMateria.getSelectedItem().toString();
        String[] parts = id.split(";");
        int codigo = Integer.parseInt(parts[0]);
        listaActividadesxMateria = daoM.verTodosxMateriaN(codigo);
        calcularDefinitivaMateria(listaActividadesxMateria);
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
    private void calcularDefinitivaMateria(ArrayList<Nota_Actividad> lista) {
        float corte1=0, corte2=0, corte3=0, total = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumCorte() == 1) {
                corte1 = corte1 + lista.get(i).getValor2();
            }else if(lista.get(i).getNumCorte() == 2){
                corte2 = corte2 + lista.get(i).getValor2();
            }else {
                corte3 = corte3 + lista.get(i).getValor2();
            }
        }
        total = (float) ((corte1*0.3)+(corte2*0.3)+(corte3)*0.4);
        definitivaCorte1.setText(String.valueOf(corte1));
        definitivaCorte2.setText(String.valueOf(corte2));
        definitivaCorte3.setText(String.valueOf(corte3));
        definitaxMateria.setText(String.valueOf(total));
    }
}