package com.cassioluciano.guaranisystemtomanage.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cassioluciano.guaranisystemtomanage.Model.PrecoModel;
import com.cassioluciano.guaranisystemtomanage.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PrecoDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Construtor
    public PrecoDAO(Context context) {
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

    // Método para inserir um preço no banco de dados
    public long insertPreco(PrecoModel preco) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CODIGO_PRODUTO_PRECO, preco.getCodigo());
        values.put(DatabaseHelper.COLUMN_VALOR_PRECO, preco.getPreco());
        // Adicione outros campos conforme necessário

        return database.insert(DatabaseHelper.TABLE_PRECOS, null, values);
    }

    // Método para buscar todos os preços de um produto
    public List<PrecoModel> getPrecosByProduto(String codigoProduto) {
        List<PrecoModel> precos = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_PRECOS, null,
                DatabaseHelper.COLUMN_CODIGO_PRODUTO_PRECO + " = ?",
                new String[]{codigoProduto}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                precos.add(cursorToPreco(cursor));
            }
            cursor.close();
        }

        return precos;
    }

    // Método auxiliar para converter um cursor em um objeto PrecoModel
    private PrecoModel cursorToPreco(Cursor cursor) {
        PrecoModel preco = new PrecoModel();
        preco.setCodigo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CODIGO_PRODUTO_PRECO)));
        preco.setPreco(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_VALOR_PRECO)));

        // Adicione outros campos conforme necessário
        return preco;
    }
}
