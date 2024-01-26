package com.cassioluciano.guaranisystemtomanage.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.cassioluciano.guaranisystemtomanage.Model.ClienteModel;
import com.cassioluciano.guaranisystemtomanage.Model.EnderecoModel;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String COLUNA_CODIGO_CLIENTE = "CLI_CODIGOCLIENTE";
    private static final String COLUNA_RAZAO_SOCIAL = "CLI_RAZAOSOCIAL";
    private static final String COLUNA_NOME_FANTASIA = "CLI_NOMEFANTASIA";
    private static final String COLUNA_CNPJ = "CLI_CNPJ";
    private static final String COLUNA_CPF = "CLI_CPF";
    private static final String COLUNA_EMAIL_PRINCIPAL = "CLI_EMAILPRINCIPAL";
    private static final String COLUNA_EMAIL_SECUNDARIO = "CLI_EMAILSECUNDARIO";

    private static final String TABELA_CLIENTES = "GUA_CLIENTES";

    private static final int VERSAO_BANCO_DADOS = 1;

    private static final String CRIAR_TABELA =
            "CREATE TABLE " + TABELA_CLIENTES + " (" +
                    COLUNA_CODIGO_CLIENTE + " TEXT PRIMARY KEY NOT NULL, " +
                    COLUNA_RAZAO_SOCIAL + " TEXT (100), " +
                    COLUNA_NOME_FANTASIA + " TEXT (50), " +
                    COLUNA_CNPJ + " TEXT (20), " +
                    COLUNA_CPF + " TEXT (20), " +
                    COLUNA_EMAIL_PRINCIPAL + " TEXT (100), " +
                    COLUNA_EMAIL_SECUNDARIO + " TEXT (100)" +
                    ");";

    private static class SQLiteHelper extends SQLiteOpenHelper {

        SQLiteHelper(Context context) {
            super(context, "NomeDoSeuBancoDeDados.db", null, VERSAO_BANCO_DADOS);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CRIAR_TABELA);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Lógica de upgrade do banco de dados
        }
    }

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ClienteDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
        abrir();
    }

    public void abrir() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void fechar() {
        dbHelper.close();
    }

//    public long inserirCliente(ClienteModel cliente) {
//        ContentValues values = new ContentValues();
//        values.put(COLUNA_CODIGO_CLIENTE, cliente.getCodigoCliente());
//        values.put(COLUNA_RAZAO_SOCIAL, cliente.getRazaoSocial());
//        values.put(COLUNA_NOME_FANTASIA, cliente.getNomeFantasia());
//        values.put(COLUNA_CNPJ, cliente.getCnpj());
//        values.put(COLUNA_CPF, cliente.getCpf());
//        values.put(COLUNA_EMAIL_PRINCIPAL, cliente.getEmailPrincipal());
//        values.put(COLUNA_EMAIL_SECUNDARIO, cliente.getEmailSecundario());
//
//        return database.insert(TABELA_CLIENTES, null, values);
//    }

//    public int atualizarCliente(ClienteModel cliente) {
//        ContentValues values = new ContentValues();
//        values.put(COLUNA_RAZAO_SOCIAL, cliente.getRazaoSocial());
//        values.put(COLUNA_NOME_FANTASIA, cliente.getNomeFantasia());
//        values.put(COLUNA_CNPJ, cliente.getCnpj());
//        values.put(COLUNA_CPF, cliente.getCpf());
//        values.put(COLUNA_EMAIL_PRINCIPAL, cliente.getEmailPrincipal());
//        values.put(COLUNA_EMAIL_SECUNDARIO, cliente.getEmailSecundario());
//
//        return database.update(TABELA_CLIENTES, values,
//                COLUNA_CODIGO_CLIENTE + " = ?",
//                new String[]{cliente.getCodigoCliente()});
//    }

//    public int deletarCliente(String codigoCliente) {
//        return database.delete(TABELA_CLIENTES,
//                COLUNA_CODIGO_CLIENTE + " = ?",
//                new String[]{codigoCliente});
//    }

//    public ClienteModel buscarClientePorCodigo(String codigoCliente) {
//        Cursor cursor = database.query(TABELA_CLIENTES,
//                null,
//                COLUNA_CODIGO_CLIENTE + " = ?",
//                new String[]{codigoCliente},
//                null,
//                null,
//                null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//            ClienteModel cliente = cursorToCliente(cursor);
//            cursor.close();
//            return cliente;
//        }
//
//        return null;
//    }

//    public List<ClienteModel> buscarTodosClientes() {
//        List<ClienteModel> clientes = new ArrayList<>();
//        Cursor cursor = database.query(TABELA_CLIENTES,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null);
//
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                clientes.add(cursorToCliente(cursor));
//            }
//            cursor.close();
//        }
//
//        return clientes;
//    }

    @SuppressLint("Range")
    private ClienteModel cursorToCliente(Cursor cursor) {
        ClienteModel cliente = new ClienteModel();
        cliente.setCodigoCliente(cursor.getString(cursor.getColumnIndex(COLUNA_CODIGO_CLIENTE)));
        cliente.setRazaoSocial(cursor.getString(cursor.getColumnIndex(COLUNA_RAZAO_SOCIAL)));
        cliente.setNomeFantasia(cursor.getString(cursor.getColumnIndex(COLUNA_NOME_FANTASIA)));
        cliente.setCnpj(cursor.getString(cursor.getColumnIndex(COLUNA_CNPJ)));
        cliente.setCpf(cursor.getString(cursor.getColumnIndex(COLUNA_CPF)));
        cliente.setEmailPrincipal(cursor.getString(cursor.getColumnIndex(COLUNA_EMAIL_PRINCIPAL)));
        cliente.setEmailSecundario(cursor.getString(cursor.getColumnIndex(COLUNA_EMAIL_SECUNDARIO)));
        // Adicione outros campos conforme necessário

        List<EnderecoModel> enderecos = buscarEnderecosDoCliente(cliente.getCodigoCliente());
        cliente.setEnderecos(enderecos);

        return cliente;
    }

    // Este método busca os endereços do cliente no banco de dados
    private List<EnderecoModel> buscarEnderecosDoCliente(String codigoCliente) {
        // Implemente a lógica para buscar os endereços do cliente no banco de dados
        // Retorne a lista de endereços
        return new ArrayList<>(); // ou null, dependendo da implementação real
    }
    // Outros métodos e atributos da classe

//    public List<ClienteModel> buscarClientes(String termo) {
//        List<ClienteModel> clientes = new ArrayList<>();
//
//        String selection = COLUNA_RAZAO_SOCIAL + " LIKE ? OR " +
//                COLUNA_NOME_FANTASIA + " LIKE ? OR " +
//                COLUNA_CNPJ + " LIKE ?";
//
//        String[] selectionArgs = new String[]{"%" + termo + "%", "%" + termo + "%", "%" + termo + "%"};
//
//        Cursor cursor = database.query(TABELA_CLIENTES,
//                null,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null);
//
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                clientes.add(cursorToCliente(cursor));
//            }
//            cursor.close();
//        }
//
//        return clientes;
//    }
//
//
//}


    public List<ClienteModel> buscarClientes(String termo) {
        List<ClienteModel> clientes = new ArrayList<>();

        String selection = COLUNA_RAZAO_SOCIAL + " LIKE ? OR " +
                COLUNA_NOME_FANTASIA + " LIKE ? OR " +
                COLUNA_CNPJ + " LIKE ?";

        String[] selectionArgs = new String[]{"%" + termo + "%", "%" + termo + "%", "%" + termo + "%"};

        Cursor cursor = null;

        try {
            cursor = database.query(TABELA_CLIENTES,
                    null,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    clientes.add(cursorToCliente(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();

            }
        }

        return clientes;
    }
}