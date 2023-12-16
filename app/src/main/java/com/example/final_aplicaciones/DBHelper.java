package com.example.final_aplicaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MiBD_prueba";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "usuarios";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_CORREO = "correo";
    private static final String COLUMN_CONTRASEÑA = "contrasena";
    private static final String COLUMN_ROL = "rol";

    private static final String TABLE_NAME_MASCOTAS = "mascotas";
    private static final String COLUMN_ID_MASCOTAS = "_id";
    private static final String COLUMN_NOMBRE_MASCOTAS = "nombre";
    private static final String COLUMN_SEXO_MASCOTAS = "sexo";
    private static final String COLUMN_RAZA_MASCOTAS = "raza";
    private static final String COLUMN_FECHA_NACIMIENTO_MASCOTAS = "fecha_nacimiento";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_CORREO + " TEXT, " +
                    COLUMN_CONTRASEÑA + " TEXT, " +
                    COLUMN_ROL + " TEXT);";

    private static final String CREATE_TABLE_MASCOTAS =
            "CREATE TABLE " + TABLE_NAME_MASCOTAS + " (" +
                    COLUMN_ID_MASCOTAS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE_MASCOTAS + " TEXT, " +
                    COLUMN_SEXO_MASCOTAS + " TEXT, " +
                    COLUMN_RAZA_MASCOTAS + " TEXT, " +
                    COLUMN_FECHA_NACIMIENTO_MASCOTAS + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_MASCOTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MASCOTAS);
        onCreate(db);
    }

    public void insertarUsuario(String nombre, String correo, String contrasena, String rol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_CORREO, correo);
        values.put(COLUMN_CONTRASEÑA, contrasena);
        values.put(COLUMN_ROL, rol);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean verificarCredenciales(String correo, String contraseña, String rol) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_CORREO + " = ? AND " +
                COLUMN_CONTRASEÑA + " = ? AND " +
                COLUMN_ROL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{correo, contraseña, rol});
        boolean resultado = cursor.moveToFirst();
        cursor.close();
        db.close();

        return resultado;
    }

    public boolean insertarMascota(String nombre, String sexo, String raza, String fechaNacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_MASCOTAS, nombre);
        values.put(COLUMN_SEXO_MASCOTAS, sexo);
        values.put(COLUMN_RAZA_MASCOTAS, raza);
        values.put(COLUMN_FECHA_NACIMIENTO_MASCOTAS, fechaNacimiento);

        long resultado = db.insert(TABLE_NAME_MASCOTAS, null, values);
        db.close();

        if (resultado != -1) {
            Log.d("DBHelper", "Mascota insertada correctamente con ID: " + resultado);
        } else {
            Log.d("DBHelper", "Error al insertar la mascota");
        }

        return resultado != -1;
    }


    public List<Mascota> obtenerTodosLosDatosMascotas() {
        List<Mascota> mascotas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_MASCOTAS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID_MASCOTAS));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_MASCOTAS));
                String sexo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SEXO_MASCOTAS));
                String raza = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RAZA_MASCOTAS));
                String fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_NACIMIENTO_MASCOTAS));

                Mascota mascota = new Mascota(id, nombre, sexo, raza, fechaNacimiento);
                mascotas.add(mascota);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return mascotas;
    }



    public boolean eliminarMascota(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME_MASCOTAS, COLUMN_ID_MASCOTAS + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return deletedRows > 0;
    }







}
