package com.cassioluciano.desafiogurani.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bancomovel.sqlite";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_CLIENTES = "GUA_CLIENTES";
    public static final String COLUMN_CODIGO_CLIENTE = "CLI_CODIGOCLIENTE";
    public static final String COLUMN_RAZAO_SOCIAL = "CLI_RAZAOSOCIAL";
    public static final String COLUMN_NOME_FANTASIA = "CLI_NOMEFANTASIA";
    public static final String COLUMN_CNPJ = "CLI_CGCCPF";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DatabaseHelper", "Constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_CLIENTES + " (" +
                COLUMN_CODIGO_CLIENTE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RAZAO_SOCIAL + " TEXT, " +
                COLUMN_NOME_FANTASIA + " TEXT, " +
                COLUMN_CNPJ + " TEXT)";
        db.execSQL(createTableQuery);
        Log.d("DatabaseHelper", "onCreate called");
    }

    public void insertCliente(SQLiteDatabase db, String razaoSocial, String nomeFantasia, String cnpj) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RAZAO_SOCIAL, razaoSocial);
        values.put(COLUMN_NOME_FANTASIA, nomeFantasia);
        values.put(COLUMN_CNPJ, cnpj);
        db.insert(TABLE_CLIENTES, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DatabaseHelper", "onUpgrade called********");
    }

    public SQLiteDatabase openDatabaseForRead() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DatabaseHelper", "openDatabaseForRead called");
        return db;
    }

    public void closeDatabase(SQLiteDatabase db) {
        if (db != null && db.isOpen()) {
            db.close();
            Log.d("DatabaseHelper", "closeDatabase called");
        }
    }
}
