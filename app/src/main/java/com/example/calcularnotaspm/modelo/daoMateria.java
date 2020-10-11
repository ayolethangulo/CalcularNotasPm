package com.example.calcularnotaspm.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class daoMateria extends SQLiteOpenHelper {

    ArrayList<Materia> listaMateria;
    Materia m;
    private Context context;
    private static final String nombreBD = "BDContactos";
    private static final int version = 1;
    public daoMateria(@Nullable Context context) {
        super(context, nombreBD, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE materias (idMateria integer primary key autoincrement, nombreMateria text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS materias");
        onCreate(db);
    }

    public boolean insertar(Materia m){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreMateria", m.getNombreMateria());
        return (db.insert("materias", null, contenedor))>0;
    }

    public ArrayList<Materia> verTodos(){

        SQLiteDatabase db = this.getReadableDatabase();
        listaMateria = new ArrayList<Materia>();
        listaMateria.clear();
        Cursor cursor = db.rawQuery("select * from materias", null);
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                listaMateria.add(new Materia(cursor.getInt(0),
                        cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        return listaMateria;
    }
    public boolean eliminar(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return (db.delete("materias", "idMateria="+id,null))>0;
    }

    public boolean editar(Materia m) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contenedor = new ContentValues();
        contenedor.put("nombreMateria", m.getNombreMateria());
        return (db.update("materias", contenedor, "idMateria="+m.getIdMateria(),null))>0;
    }
    public Materia verUno(int position) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from materias", null);
        cursor.moveToPosition(position);
        m = new Materia(cursor.getInt(0),
                cursor.getString(1));
        return m;
    }
}
