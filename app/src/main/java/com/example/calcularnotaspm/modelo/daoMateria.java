package com.example.calcularnotaspm.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class daoMateria extends SQLiteOpenHelper {

    private static final String nombreBD = "BDPrimerParcial";
    private static final String table1 = "materias";
    private static final String table2 = "notas";
    private Context context;
    private static final int version = 1;

    Materia m;
    Nota_Actividad n;
    ArrayList<Nota_Actividad> listaActividades;
    ArrayList<Materia> listaMateria;

    public daoMateria(@Nullable Context context) {
        super(context, nombreBD, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE "+table1+" (idMateria integer primary key autoincrement, nombreMateria text)";
        String query2 = "CREATE TABLE "+table2+" (id integer primary key autoincrement, nombreActividad text, porcentaje double, valor double, numCorte integer, idMateria int, valor2 double)";
        db.execSQL(query1);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table1);
        db.execSQL("DROP TABLE IF EXISTS "+table2);
        onCreate(db);
    }
    public boolean insertarM(Materia m){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreMateria", m.getNombreMateria());
        return (db.insert(table1, null, contenedor))>0;
    }

    public ArrayList<Materia> verTodosM(){

        SQLiteDatabase db = this.getReadableDatabase();
        listaMateria = new ArrayList<Materia>();
        listaMateria.clear();
        Cursor cursor = db.rawQuery("select * from "+table1, null);
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                listaMateria.add(new Materia(cursor.getInt(0),
                        cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        return listaMateria;
    }
    public boolean eliminarM(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return (db.delete(table1, "idMateria="+id,null))>0;
    }

    public boolean editarM(Materia m) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreMateria", m.getNombreMateria());
        return (db.update(table1, contenedor, "idMateria="+m.getIdMateria(),null))>0;
    }
    public Materia verUnoM(int position) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+table1, null);
        cursor.moveToPosition(position);
        m = new Materia(cursor.getInt(0),
                cursor.getString(1));
        return m;
    }

    //notas
    public boolean insertarN(Nota_Actividad n){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreActividad",n.getNombreActividad());
        contenedor.put("porcentaje",n.getPorcentaje());
        contenedor.put("valor",n.getValor());
        contenedor.put("numCorte",n.getNumCorte());
        contenedor.put("idMateria",n.getIdMateria());
        contenedor.put("valor2",n.getValor2());
        return (db.insert(table2, null, contenedor))>0;
    }

    public ArrayList<Nota_Actividad> verTodosN(){

        SQLiteDatabase db = this.getReadableDatabase();
        listaActividades = new ArrayList<Nota_Actividad>();
        listaActividades.clear();
        Cursor cursor = db.rawQuery("select * from "+table2, null);
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                listaActividades.add(new Nota_Actividad(cursor.getInt(0),
                        cursor.getString(1), cursor.getFloat(2),
                        cursor.getFloat(3), cursor.getInt(4),
                        cursor.getInt(5), cursor.getFloat(6)));
            }while(cursor.moveToNext());
        }
        return listaActividades;
    }
    public boolean eliminarN(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return (db.delete(table2, "id="+id,null))>0;
    }

    public boolean editarN(Nota_Actividad n) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreActividad", n.getNombreActividad());
        contenedor.put("porcentaje", n.getPorcentaje());
        contenedor.put("valor", n.getValor());
        contenedor.put("numCorte", n.getNumCorte());
        contenedor.put("idMateria", n.getIdMateria());
        contenedor.put("valor2", n.getValor2());
        return (db.update(table2, contenedor, "id="+n.getId(),null))>0;
    }
    public Nota_Actividad verUnoN(int position) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+table2, null);
        cursor.moveToPosition(position);
        n = new Nota_Actividad(cursor.getInt(0),
                cursor.getString(1), cursor.getFloat(2),
                cursor.getFloat(3), cursor.getInt(4),
                cursor.getInt(5), cursor.getFloat(6));
        return n;
    }
}
