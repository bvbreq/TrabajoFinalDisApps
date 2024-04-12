package com.dam.practicapersistencia;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiBaseDatos extends SQLiteOpenHelper {


    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "mibasedatos.db";
    private static final String TABLA_SALAS ="CREATE TABLE IF NOT EXISTS salas " +
            " (sala INTEGER PRIMARY KEY  nombre TEXT, mensaje TEXT)";

    public MiBaseDatos(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_SALAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLA_SALAS);
        onCreate(db);
    }




    public boolean insertarMENSAJE(int sala, String nombre, String mensaje) {
        long salida=0;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            if(sala!=0)
                valores.put("sala", sala);
            valores.put("nombre", nombre);
            valores.put("mensaje", mensaje);
            salida=db.insert(TABLA_SALAS, null, valores);
        }
        db.close();
        return(salida>0);
    }
    public ArrayList<Salas> recuperarSalas() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Salas> lista_salas = new ArrayList<>();

        // Obtener el nombre de todas las tablas en la base de datos
        Cursor cursor = db.rawQuery("SELECT sala FROM salas WHERE type='table'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String nombreTabla = cursor.getString(0);
            // Si la tabla no es "android_metadata" (metadatos de la base de datos)
            // ni "sqlite_sequence" (para autoincremento), la consideramos una "sala"
            //if (!nombreTabla.equals("android_metadata") && !nombreTabla.equals("sqlite_sequence")) {
            // Recuperar los datos de la tabla y agregarlos a la lista de salas
            // ArrayList<Contactos> contactos = recuperarContactosDeSala(db, nombreTabla);
            // lista_salas.addAll(contactos);

            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return lista_salas;
    }

    // Método para recuperar los contactos de una sala específica (tabla)



    /*public boolean  modificarCONTACTO(int id, String nom, String tlf, String email){
        long salida=0;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("_id", id);
            valores.put("nombre", nom);
            valores.put("telefono", tlf);
            valores.put("email", email);
            salida=db.update("contactos", valores, "_id=" + id, null);
        }
        db.close();
        return(salida>0);
    }

    public boolean  borrarCONTACTO(int id) {
        SQLiteDatabase db = getWritableDatabase();
        long salida=0;
        if (db != null) {
            salida=db.delete("contactos", "_id=" + id, null);
        }
        db.close();
        return(salida>0);
    }

    public Contactos recuperarCONTACTO(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"_id", "nombre", "telefono", "email"};
        Cursor c = db.query("contactos", valores_recuperar, "_id=" + id, null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        Contactos contactos = new Contactos(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
        db.close();
        c.close();
        return contactos;
    }

    public ArrayList<Contactos> recuperarCONTACTOS() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Contactos> lista_contactos = new ArrayList<Contactos>();
        String[] valores_recuperar = {"_id", "nombre", "telefono", "email"};
        Cursor c = db.query("contactos", valores_recuperar, null, null, null, null, null, null);
        c.moveToFirst();
        do {
            Contactos contactos = new Contactos(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
            lista_contactos.add(contactos);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_contactos;
    }
    */

}
