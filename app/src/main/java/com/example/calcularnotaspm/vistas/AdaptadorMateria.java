package com.example.calcularnotaspm.vistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calcularnotaspm.R;
import com.example.calcularnotaspm.modelo.Materia;
import com.example.calcularnotaspm.modelo.daoMateria;

import java.util.ArrayList;

public class AdaptadorMateria extends BaseAdapter {

    ArrayList<Materia> listaMateria;
    daoMateria dao;
    Materia m;
    Activity a;
    int idMateria = 0;

    public AdaptadorMateria(Activity a, ArrayList<Materia> listaMateria,daoMateria dao){
        this.a = a;
        this.listaMateria = listaMateria;
        this.dao = dao;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public int getCount() {
        return listaMateria.size();
    }

    @Override
    public Object getItem(int position) {
        m = listaMateria.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        m=listaMateria.get(position);
        return m.getIdMateria();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (v == null) {
            LayoutInflater li =(LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_materia, null);
        }
        m = listaMateria.get(position);
        TextView idMateria = (TextView)v.findViewById(R.id.txtIdMateria);
        TextView nombreMateria = (TextView)v.findViewById(R.id.txtNombreMateria);
        Button btnEditarM = (Button)v.findViewById(R.id.btnEditarM);
        Button btnEliminarM = (Button)v.findViewById(R.id.btnEliminarM);
       // idMateria.setText(m.getIdMateria());
        nombreMateria.setText(m.getNombreMateria());
        btnEditarM.setTag(position);
        btnEliminarM.setTag(position);
        btnEditarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialogo para editar
                int pos =Integer.parseInt(view.getTag().toString());
                final Dialog dialogo = new Dialog(a);
                dialogo.setTitle("Editar Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo_materia);
                dialogo.show();
                final EditText nombreMateria = (EditText) dialogo.findViewById(R.id.txtDNombreM);
                Button btnAgregarMateria = (Button) dialogo.findViewById(R.id.btnAgregarMateria);
                btnAgregarMateria.setText("Guardar");
                Button btnDCancelar = (Button) dialogo.findViewById(R.id.btnD_cancelar);
                m=listaMateria.get(pos);
                setIdMateria(m.getIdMateria());
                nombreMateria.setText(m.getNombreMateria());
                btnAgregarMateria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            m = new Materia(getIdMateria(),nombreMateria.getText().toString());
                            dao.editar(m);
                            listaMateria=dao.verTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(a, "Error entraaaaaa", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                btnDCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogo.dismiss();
                    }
                });

            }
        });
        btnEliminarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo para eliminar si/no
                int pos =Integer.parseInt(view.getTag().toString());
                m = listaMateria.get(pos);
                setIdMateria(m.getIdMateria());
                final AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Desea eliminar?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dao.eliminar(getIdMateria());
                        listaMateria=dao.verTodos();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
