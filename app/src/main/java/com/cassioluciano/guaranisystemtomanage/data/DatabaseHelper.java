package com.cassioluciano.guaranisystemtomanage.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "util/guaranisystem.db";
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
        // Obtém o caminho do diretório de arquivos do aplicativo
        File filesDir = Environment.getExternalStorageDirectory();
        DATABASE_PATH = filesDir.getPath() + File.separator + DATABASE_NAME;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_PATH, null, DATABASE_VERSION);
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
        db.execSQL(CREATE_TABLE_CLIENTES);
        db.execSQL(CREATE_TABLE_ENDERECO);
        db.execSQL(CREATE_TABLE_PRECOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implemente conforme necessário se precisar atualizar o esquema do banco de dados
    }
}
