package com.cassioluciano.guaranisystemtomanage.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cassioluciano.guaranisystemtomanage.Model.EnderecoModel;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Construtor
    public EnderecoDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Método para abrir a conexão com o banco de dados
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Método para fechar a conexão com o banco de dados
    public void close() {
        dbHelper.close();
    }

    // Método para inserir um endereço no banco de dados
    public long insertEndereco(EnderecoModel endereco) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ENDERECO, endereco.getEndereco());
        values.put(DatabaseHelper.COLUMN_NUMERO, endereco.getNumero());
        values.put(DatabaseHelper.COLUMN_COMPLEMENTO, endereco.getComplemento());
        values.put(DatabaseHelper.COLUMN_BAIRRO, endereco.getBairro());
        values.put(DatabaseHelper.COLUMN_CODIGO_MUNICIPIO, endereco.getCodigoMunicipio());
        values.put(DatabaseHelper.COLUMN_TELEFONE, endereco.getTelefone());
        values.put(DatabaseHelper.COLUMN_FAX, endereco.getFax());
        values.put(DatabaseHelper.COLUMN_CEP, endereco.getCep());
        // Adicione outros campos conforme necessário

        return database.insert(DatabaseHelper.TABLE_ENDERECO, null, values);
    }

    // Método para buscar todos os endereços
    public List<EnderecoModel> getAllEnderecos() {
        List<EnderecoModel> enderecos = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_ENDERECO, null,
                null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                enderecos.add(cursorToEndereco(cursor));
            }
            cursor.close();
        }

        return enderecos;
    }

    // Método auxiliar para converter um cursor em um objeto EnderecoModel
    private EnderecoModel cursorToEndereco(Cursor cursor) {
        EnderecoModel endereco = new EnderecoModel();
        endereco.setEndereco(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ENDERECO)));
        endereco.setNumero(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NUMERO)));
        endereco.setComplemento(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMPLEMENTO)));
        endereco.setBairro(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BAIRRO)));
        endereco.setCodigoMunicipio(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CODIGO_MUNICIPIO)));
        endereco.setTelefone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TELEFONE)));
        endereco.setFax(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FAX)));
        endereco.setCep(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CEP)));
        // Adicione outros campos conforme necessário
        return endereco;
    }

}
