package com.cassioluciano.guaranisystemtomanage.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.cassioluciano.guaranisystemtomanage.Model.ProdutoModel;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Construtor
    public ProdutoDAO(Context context) {
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

    // Método para inserir um produto no banco de dados
    public long insertProduto(ProdutoModel produto) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CODIGO_EMPRESA_PRODUTO, produto.getCodigoEmpresa());
        values.put(DatabaseHelper.COLUMN_CODIGO_PRODUTO, produto.getCodigo());
        // Adicione outros campos conforme necessário

        return database.insert(DatabaseHelper.TABLE_PRODUTOS, null, values);
    }

    // Método para buscar todos os produtos pelo status
    public List<ProdutoModel> getProdutosByStatus(String status) {
        List<ProdutoModel> produtos = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_PRODUTOS, null,
                DatabaseHelper.COLUMN_STATUS_PRODUTO + " = ?",
                new String[]{status}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                produtos.add(cursorToProduto(cursor));
            }
            cursor.close();
        }

        return produtos;
    }

    // Método para buscar um produto pelo código
    public ProdutoModel getProdutoByCodigo(String codigoProduto) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_PRODUTOS, null,
                DatabaseHelper.COLUMN_CODIGO_PRODUTO + " = ?", new String[]{codigoProduto},
                null, null, null);

        ProdutoModel produto = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                produto = cursorToProduto(cursor);
            }
            cursor.close();
        }
        return produto;
    }

    // Método auxiliar para converter um cursor em um objeto ProdutoModel
    private ProdutoModel cursorToProduto(Cursor cursor) {
        ProdutoModel produto = new ProdutoModel();
        produto.setCodigoEmpresa(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CODIGO_EMPRESA_PRODUTO)));
        produto.setCodigo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CODIGO_PRODUTO)));
        // Adicione outros campos conforme necessário
        return produto;
    }
}
