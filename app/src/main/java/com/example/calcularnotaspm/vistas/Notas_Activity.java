package com.example.calcularnotaspm.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calcularnotaspm.R;
import com.example.calcularnotaspm.modelo.Materia;
import com.example.calcularnotaspm.modelo.Nota_Actividad;
import com.example.calcularnotaspm.modelo.daoMateria;

import java.util.ArrayList;

public class Notas_Activity extends AppCompatActivity {

    daoMateria daoM;
    Nota_Actividad n;
    ArrayList<String> listaSpinnerMaterias;
    ArrayList<Materia> listaMaterias;
    Spinner spnCortes, spnMaterias;
    TextView porcentajeCorte;
    EditText nombreActividad, porcentaje, valor;
    Button btnAgregarActividad, btnCancelarActividad;
    int numCorte = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        spnCortes = (Spinner) findViewById(R.id.cmbCortes);
        spnMaterias = (Spinner) findViewById(R.id.cmbMaterias);
        porcentajeCorte = (TextView) findViewById(R.id.txtCorte);

        nombreActividad = findViewById(R.id.txtnombreActividad);
        porcentaje = findViewById(R.id.txtPorcentajeA);
        valor = findViewById(R.id.txtValorA);
        btnAgregarActividad = findViewById(R.id.btnAgregarActividad);
        btnCancelarActividad = findViewById(R.id.btnCancelarActividad);

        spnCortes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        porcentajeCorte.setText("");
                        break;
                    case 1:
                        numCorte = 1;
                        porcentajeCorte.setText("30%");
                        break;
                    case 2:
                        numCorte = 2;
                        porcentajeCorte.setText("30%");
                        break;
                    case 3:
                        numCorte = 3;
                        porcentajeCorte.setText("40%");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        daoM = new daoMateria(this);
        listaMaterias =daoM.verTodosM();
        obtenerLista();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaSpinnerMaterias);
        spnMaterias.setAdapter(adaptador);

        btnAgregarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = spnMaterias.getSelectedItem().toString();
                String[] parts = id.split(";");
                int codigo = Integer.parseInt(parts[0]);
                daoM = new daoMateria(Notas_Activity.this);

                n = new Nota_Actividad(nombreActividad.getText().toString(),
                        Float.parseFloat(porcentaje.getText().toString()),
                        Float.parseFloat(valor.getText().toString()),
                        numCorte, codigo);
                daoM.insertarN(n);
                LimpiarCampos();
            }
        });
        btnCancelarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notas_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LimpiarCampos() {
        spnMaterias.setSelection(0);
        spnCortes.setSelection(0);
        nombreActividad.setText("");
        porcentaje.setText("");
        porcentajeCorte.setText("");
        valor.setText("");

    }

    private void obtenerLista() {
        listaSpinnerMaterias = new ArrayList<String>();
        listaSpinnerMaterias.add("Seleccione una materia");

        for (int i=0;i<listaMaterias.size();i++){
            listaSpinnerMaterias.add(String.valueOf(listaMaterias.get(i).getIdMateria())+";"+listaMaterias.get(i).getNombreMateria());
        }
    }
}
