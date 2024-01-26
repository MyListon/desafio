package com.cassioluciano.guaranisystemtomanage.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "bancomovel.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_PATH;


    // Tabela GUA_ESTOQUE_EMPRESA
    public static final String TABLE_ESTOQUE_EMPRESA = "GUA_ESTOQUE_EMPRESA";
    public static final String COLUMN_EMPRESA_ESTOQUE_EMPRESA = "EMPRESA_ESTOQUE_EMPRESA";
    public static final String COLUMN_CODIGO_ESTOQUE_EMPRESA = "CODIGO_ESTOQUE_EMPRESA";

    // Tabela GUA_CLIENTES
    public static final String TABLE_CLIENTES = "GUA_CLIENTES";
    public static final String COLUMN_CODIGO_CLIENTE = "CLI_CODIGOCLIENTE";
    public static final String COLUMN_RAZAO_SOCIAL = "CLI_RAZAOSOCIAL";
    public static final String COLUMN_NOME_FANTASIA = "CLI_NOMEFANTASIA";
    public static final String COLUMN_CNPJ = "CLI_CGCCPF";
    // Adicione outras colunas conforme necessário

    // Tabela GUA_ENDERECO
    public static final String TABLE_ENDERECO = "GUA_ENDERECO";
    public static final String COLUMN_ENDERECO = "ENDERECO";
    public static final String COLUMN_NUMERO = "NUMERO";
    public static final String COLUMN_COMPLEMENTO = "COMPLEMENTO";
    public static final String COLUMN_BAIRRO = "BAIRRO";
    public static final String COLUMN_CODIGO_MUNICIPIO = "CODIGOMUNICIPIO";
    public static final String COLUMN_TELEFONE = "TELEFONE";
    public static final String COLUMN_FAX = "FAX";
    public static final String COLUMN_CEP = "CEP";

    public static final String TABLE_PRODUTOS = "GUA_PRODUTOS";
    public static final String COLUMN_CODIGO_EMPRESA_PRODUTO = "CODIGO_EMPRESA_PRODUTO";
    public static final String COLUMN_CODIGO_PRODUTO = "CODIGO_PRODUTO";
    public static final String COLUMN_STATUS_PRODUTO = "STATUS_PRODUTO";


   // tabela de preços
    public static final String TABLE_PRECOS = "GUA_PRECOS";
    public static final String COLUMN_CODIGO_PRODUTO_PRECO = "CODIGO_PRODUTO_PRECO";
    public static final String COLUMN_VALOR_PRECO = "VALOR_PRECO";

    static {
        File filesDir = Environment.getExternalStorageDirectory();
        DATABASE_PATH = filesDir.getPath() + File.separator + "util";
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        copyDatabaseFromAssets(context);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return SQLiteDatabase.openDatabase(DATABASE_PATH + File.separator + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return SQLiteDatabase.openDatabase(DATABASE_PATH + File.separator + DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);
    }

    private void copyDatabaseFromAssets(Context context) {
        // Verifica se o banco de dados já existe na pasta de dados do aplicativo
        String dbPath = DATABASE_PATH + DATABASE_NAME;
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.d("DatabaseHelper", "O banco de dados já existe em: " + dbPath);
            return;
        }

        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            OutputStream outputStream = new FileOutputStream(dbPath);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
            Log.d("DatabaseHelper", "Banco de dados copiado para: " + dbPath);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DatabaseHelper", "Erro ao copiar o banco de dados: " + e.getMessage());
        }
    }


    private static final String CREATE_TABLE_CLIENTES = "CREATE TABLE " + TABLE_CLIENTES + "("
            + COLUMN_CODIGO_CLIENTE + " TEXT PRIMARY KEY NOT NULL,"
            + COLUMN_RAZAO_SOCIAL + " TEXT,"
            + COLUMN_NOME_FANTASIA + " TEXT,"
            + COLUMN_CNPJ + " TEXT"
            + ')';

    private static final String CREATE_TABLE_ENDERECO = "CREATE TABLE " + TABLE_ENDERECO + "("
            + COLUMN_ENDERECO + " TEXT,"
            + COLUMN_NUMERO + " TEXT,"
            + COLUMN_COMPLEMENTO + " TEXT,"
            + COLUMN_BAIRRO + " TEXT,"
            + COLUMN_CODIGO_MUNICIPIO + " TEXT,"
            + COLUMN_TELEFONE + " TEXT,"
            + COLUMN_FAX + " TEXT,"
            + COLUMN_CEP + " TEXT"
            + ")";

    // Adicionado SQL para criar a tabela de preços
    private static final String CREATE_TABLE_PRECOS = "CREATE TABLE " + TABLE_PRECOS + "("
            + COLUMN_CODIGO_PRODUTO_PRECO + " TEXT,"
            + COLUMN_VALOR_PRECO + " REAL"
            + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_CLIENTES);
            db.execSQL(CREATE_TABLE_ENDERECO);
            db.execSQL(CREATE_TABLE_PRECOS);
            Log.d("DatabaseHelper", "***********Tabelas criadas com sucesso.");
        } catch (SQLException e) {
            Log.e("DatabaseHelper", "**********Erro ao criar tabelas: " + e.getMessage());
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implemente conforme necessário se precisar atualizar o esquema do banco de dados
    }
}
