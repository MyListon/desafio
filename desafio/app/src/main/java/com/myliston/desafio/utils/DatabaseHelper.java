package com.myliston.desafio.utils;

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

import com.myliston.desafio.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bancomovel.sqlite";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_CLIENTES = "GUA_CLIENTES";
    public static final String COLUMN_CODIGO_CLIENTE = "CLI_CODIGOCLIENTE";
    public static final String COLUMN_RAZAO_SOCIAL = "CLI_RAZAOSOCIAL";
    public static final String COLUMN_NOME_FANTASIA = "CLI_NOMEFANTASIA";
    public static final String COLUMN_CNPJ = "CLI_CGCCPF";



    public static final String TABLE_PRODUTOS = "GUA_PRODUTOS";
    public static final String COLUMN_CODIGO_EMPRESA = "PRO_CODIGOEMPRESA";
    public static final String COLUMN_CODIGO = "PRO_CODIGO";
    public static final String COLUMN_CODIGO_MARCA = "PRO_CODIGOMARCA";
    public static final String COLUMN_CODIGO_GRUPO = "PRO_CODIGOGRUPO";
    public static final String COLUMN_DESCRICAO = "PRO_DESCRICAO";
    public static final String COLUMN_STATUS = "PRO_STATUS";
    public static final String COLUMN_ESTOQUE = "PRO_SITESTOQUE";
    public static final String COLUMN_PRECO_MAXIMO = "PRO_PRECUSTO";
    public static final String COLUMN_PRECO_MINIMO = "PRO_PRECUSTO";


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


        // Criar a tabela de produtos
        String createTableQueryProdutos = "CREATE TABLE " + TABLE_PRODUTOS + " (" +
                COLUMN_CODIGO_EMPRESA + " TEXT NOT NULL, " +
                COLUMN_CODIGO + " TEXT NOT NULL, " +
                COLUMN_CODIGO_MARCA + " TEXT, " +
                COLUMN_CODIGO_GRUPO + " TEXT, " +
                COLUMN_DESCRICAO + " TEXT NOT NULL, " +
                COLUMN_STATUS + " TEXT NOT NULL, " +
                COLUMN_ESTOQUE + " INT NOT NULL, " +
                COLUMN_PRECO_MAXIMO + " REAL, " +
                COLUMN_PRECO_MINIMO + " REAL, " +
                "PRIMARY KEY (" + COLUMN_CODIGO_EMPRESA + ", " + COLUMN_CODIGO + "))";
        db.execSQL(createTableQueryProdutos);

    }

    public void insertProduto(SQLiteDatabase db, Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODIGO_EMPRESA, product.getCodigoEmpresa());
        values.put(COLUMN_CODIGO, product.getCodigo());
        values.put(COLUMN_CODIGO_MARCA, product.getCodigoMarca());
        values.put(COLUMN_CODIGO_GRUPO, product.getCodigoGrupo());
        values.put(COLUMN_DESCRICAO, product.getDescricao());
        values.put(COLUMN_STATUS, product.getStatus());
        values.put(COLUMN_ESTOQUE, product.getEstoque());
        values.put(COLUMN_PRECO_MAXIMO, product.getPrecoMaximo());
        values.put(COLUMN_PRECO_MINIMO, product.getPrecoMinimo());
        // Adicione outros campos conforme necessário
        db.insert(TABLE_PRODUTOS, null, values);
    }


    public void insertCliente(SQLiteDatabase db, String razaoSocial, String nomeFantasia, String cnpj) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RAZAO_SOCIAL, razaoSocial);
        values.put(COLUMN_NOME_FANTASIA, nomeFantasia);
        values.put(COLUMN_CNPJ, cnpj);
        db.insert(TABLE_CLIENTES, null, values);
    }


    public static List<Product> getProdutosByStatus(SQLiteDatabase db, String status) {
        List<Product> produtos = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PRODUTOS + " WHERE " + COLUMN_STATUS + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{status});
        try {
            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product();
                    product.setCodigoEmpresa(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGO_EMPRESA)));
                    product.setCodigo(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGO)));
                    product.setCodigoMarca(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGO_MARCA)));
                    product.setCodigoGrupo(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGO_GRUPO)));
                    product.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO)));
                    product.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)));
                    product.setEstoque(cursor.getInt(cursor.getColumnIndex(COLUMN_ESTOQUE)));
                    product.setPrecoMaximo(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECO_MAXIMO)));
                    product.setPrecoMinimo(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECO_MINIMO)));
                    // Adicione outros campos conforme necessário
                    produtos.add(product);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return produtos;
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
