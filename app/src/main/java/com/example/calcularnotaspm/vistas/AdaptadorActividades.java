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
import com.example.calcularnotaspm.modelo.Nota_Actividad;
import com.example.calcularnotaspm.modelo.daoMateria;

import java.util.ArrayList;

public class AdaptadorActividades extends BaseAdapter {
    ArrayList<Nota_Actividad> listaActividades;
    daoMateria dao;
    Nota_Actividad n;
    Activity a;
    int idActividad = 0;

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public AdaptadorActividades(Activity a, ArrayList<Nota_Actividad> listaActividades, daoMateria dao){
        this.a = a;
        this.listaActividades = listaActividades;
        this.dao = dao;
    }
    @Override
    public int getCount() {
        return listaActividades.size();
    }

    @Override
    public Nota_Actividad getItem(int i) {
        n = listaActividades.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        n=listaActividades.get(i);
        return n.getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (v == null){
            LayoutInflater li =(LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v =li.inflate(R.layout.item_actividades, null);
        }
        n = listaActividades.get(position);
        TextView nombre = (TextView)v.findViewById(R.id.txtActividadI);
        TextView valor = (TextView)v.findViewById(R.id.txtValorActividadI);
        TextView porcentaje = (TextView)v.findViewById(R.id.txtPorcentajeActividadI);
        Button editar = (Button)v.findViewById(R.id.btnEditarI);
        Button eliminar = (Button)v.findViewById(R.id.btnEliminarI);
        nombre.setText(n.getNombreActividad());
        valor.setText(""+n.getValor());
        porcentaje.setText(""+n.getPorcentaje());
        editar.setTag(position);
        eliminar.setTag(position);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos =Integer.parseInt(view.getTag().toString());
                final Dialog dialogo = new Dialog(a);
                dialogo.setTitle("Editar Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo_actividad);
                dialogo.show();
                final EditText nombre = (EditText) dialogo.findViewById(R.id.txtDnombreActividad);
                final EditText valor = (EditText) dialogo.findViewById(R.id.txtDValorA);
                final EditText porcentaje = (EditText) dialogo.findViewById(R.id.txtDPorcentajeA);
                Button agregarIActividad = (Button) dialogo.findViewById(R.id.dA_agregar);
                Button cancelarIActividad = (Button) dialogo.findViewById(R.id.dA_cancelar);
                n=listaActividades.get(pos);
                setIdActividad(n.getId());
                nombre.setText(n.getNombreActividad());
                valor.setText(""+n.getValor());
                porcentaje.setText(""+n.getPorcentaje());
                agregarIActividad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            n = new Nota_Actividad(getIdActividad(),nombre.getText().toString(),
                                    Float.parseFloat(valor.getText().toString()),
                                    Float.parseFloat(porcentaje.getText().toString()));
                            dao.editarN(n);
                            listaActividades=dao.verTodosN();
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(a, "Error miraaaaa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelarIActividad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogo.dismiss();
                    }
                });
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo para eliminar si/no
                int pos =Integer.parseInt(view.getTag().toString());
                n = listaActividades.get(pos);
                setIdActividad(n.getId());
                final AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Desea eliminar?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dao.eliminarN(getIdActividad());
                        listaActividades=dao.verTodosN();
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
