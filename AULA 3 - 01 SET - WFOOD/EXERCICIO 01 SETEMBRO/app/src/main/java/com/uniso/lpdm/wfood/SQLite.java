package com.uniso.lpdm.wfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

    //criamos variaveis constantes para armazenar dados do banco

    private static final String DB_NAME = "wfood";
    private static final int DB_VERSION = 2;


    //Construtor
    SQLite(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //onCreate e onUpgrade sao funções necessarios quando utilizamos o sqliteOpenHelper
    // criar a estrutura do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        atualizarBanco(db, 0, DB_VERSION);
    }

    //quando os dados sao alterados, usamos essa função, tabem é preciso verificar se o banco de dados preciser ser atualizado
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        atualizarBanco(db, oldVersion, newVersion);

    }

    //metodo para inserção de dados
    public static void insertHamburguer(SQLiteDatabase db, String nome, String descricao, int imagem_resource_id){

        ContentValues hamburguer = new ContentValues();
        hamburguer.put("nome", nome);
        hamburguer.put("descricao", descricao);
        hamburguer.put("imagem_resource_id", imagem_resource_id);
        db.insert("HAMBURGUER", null, hamburguer);

    }

    //metodo para atualizar os dados
    private void atualizarBanco(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql;


        //verifica se o onCreate esta senndo chamado, esse metodo cria a estrutura basica
        if(oldVersion < 1){

            sql = "CREATE TABLE HAMBURGUER (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "descricao TEXT, " +
                    "imagem_resource_id INTEGER" +
                    ");";

            db.execSQL(sql);

            //populamos o banco com alguns dados e imagens
            insertHamburguer(db, "xburguer", "pao com hamburger", R.drawable.xburger);
            insertHamburguer(db, "xsalada", "pao com haburguer e alface", R.drawable.xsalada);
            insertHamburguer(db, "xtudo", "pao com todos os ingredientes da casa", R.drawable.xtudo);
        }

        if (oldVersion <= 2){
            sql = "ALTER TABLE BEBIDA ADD COLUMN favorita NUMERIC;";
            db.execSQL(sql);
        }

    }
}
