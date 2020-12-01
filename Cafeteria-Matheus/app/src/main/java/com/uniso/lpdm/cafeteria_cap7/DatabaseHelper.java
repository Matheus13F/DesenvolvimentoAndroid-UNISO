package com.uniso.lpdm.cafeteria_cap7;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "cafeteria_completo";
    private static final int DB_VERSION = 1;

    DatabaseHelper(Context context){

        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        atualizarBanco(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*Para facilitar o reuso de código da criação e do upgrade, colocamos o código que cria
         * a tabela do exemplo no método atualizarBanco*/
        atualizarBanco(db, oldVersion, newVersion);

    }

    public static void insertBebida(SQLiteDatabase db, String nome, String descricao,
                                    int imagem_resource_id){
        ContentValues bebida = new ContentValues();
        bebida.put("nome", nome);
        bebida.put("descricao", descricao);
        bebida.put("imagem_resource_id", imagem_resource_id);
        db.insert("BEBIDA", null, bebida);
    }

    public static void insertComida(SQLiteDatabase db, String nome, String descricao, int imagem_resourse_id) {
        ContentValues comida = new ContentValues();
        comida.put("nome", nome);
        comida.put("descricao", descricao);
        comida.put("imagem_resourse_id", imagem_resourse_id);

        db.insert("COMIDA", null, comida);
    }

    private void atualizarBanco(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql;
        Log.d("entrou", "######################-------ENTROU ####################---------------");

        if(oldVersion < 1){

            sql = "CREATE TABLE BEBIDA (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "descricao TEXT, " +
                    "imagem_resource_id INTEGER" +
                    ");";
            db.execSQL(sql);

            sql = "CREATE TABLE COMIDA (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "descricao TEXT, " +
                    "imagem_resource_id INTEGER" +
                    ");";

            db.execSQL(sql);

            insertBebida(db, "Latte", "Cafe com leite", R.drawable.latte);
            insertBebida(db, "Cappuccino", "Cafe com leite e chantily", R.drawable.cappuccino);
            insertBebida(db, "Filtrado", "Cafe preto filtrado", R.drawable.filtrado);

            insertComida(db, "Churrasco", "Carne assada", R.drawable.churrasco);
            insertComida(db, "Japonesa", "Sushi e Hot Roll", R.drawable.japonesa);
            insertComida(db, "Vegana", "Saladas orgânicas", R.drawable.vegana);
        }

        if (oldVersion <= 2){
            sql = "ALTER TABLE BEBIDA ADD COLUMN favorita NUMERIC;";
            sql = "ALTER TABLE COMIDA ADD COLUMN favorita NUMERIC;";
            db.execSQL(sql);
        }

    }
}
