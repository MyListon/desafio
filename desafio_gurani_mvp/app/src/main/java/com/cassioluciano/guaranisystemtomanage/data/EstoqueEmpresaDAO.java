package com.cassioluciano.guaranisystemtomanage.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cassioluciano.guaranisystemtomanage.Model.EstoqueEmpresaModel;
import com.cassioluciano.guaranisystemtomanage.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class EstoqueEmpresaDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Construtor
    public EstoqueEmpresaDAO(Context context) {
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

    // Método para inserir um registro de estoque no banco de dados
    public long insertEstoqueEmpresa(EstoqueEmpresaModel estoqueEmpresa) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMPRESA_ESTOQUE_EMPRESA, estoqueEmpresa.getEmpresa());
        values.put(DatabaseHelper.COLUMN_CODIGO_ESTOQUE_EMPRESA, estoqueEmpresa.getCodigo());
        // Adicione outros campos conforme necessário

        return database.insert(DatabaseHelper.TABLE_ESTOQUE_EMPRESA, null, values);
    }

    // Método para buscar todos os estoques pela empresa
    public List<EstoqueEmpresaModel> getEstoquesByEmpresa(String empresa) {
        List<EstoqueEmpresaModel> estoques = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_ESTOQUE_EMPRESA, null,
                DatabaseHelper.COLUMN_EMPRESA_ESTOQUE_EMPRESA + " = ?",
                new String[]{empresa}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                estoques.add(cursorToEstoqueEmpresa(cursor));
            }
            cursor.close();
        }

        return estoques;
    }

    // Método para buscar um estoque pelo código e empresa
    public EstoqueEmpresaModel getEstoqueByCodigo(String empresa, String codigo) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_ESTOQUE_EMPRESA, null,
                DatabaseHelper.COLUMN_EMPRESA_ESTOQUE_EMPRESA + " = ? AND " +
                        DatabaseHelper.COLUMN_CODIGO_ESTOQUE_EMPRESA + " = ?",
                new String[]{empresa, codigo}, null, null, null);

        EstoqueEmpresaModel estoque = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                estoque = cursorToEstoqueEmpresa(cursor);
            }
            cursor.close();
        }
        return estoque;
    }

    // Método auxiliar para converter um cursor em um objeto EstoqueEmpresaModel
    private EstoqueEmpresaModel cursorToEstoqueEmpresa(Cursor cursor) {
        EstoqueEmpresaModel estoque = new EstoqueEmpresaModel();
        estoque.setEmpresa(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMPRESA_ESTOQUE_EMPRESA)));
        estoque.setCodigo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CODIGO_ESTOQUE_EMPRESA)));
        // Adicione outros campos conforme necessário
        return estoque;
    }
}
