package com.uniso.lpdm.cafeteria_cap7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BebidaActivity extends AppCompatActivity {

    public static final String EXTRA_BEBIDA_ID = "bebida_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebida);

        int id_bebida = (Integer) getIntent().getExtras().get(EXTRA_BEBIDA_ID);
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);

        try{

            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            Cursor cursor = db.query("BEBIDA",
                    new String[] {"nome", "descricao", "imagem_resource_id"},
                    "_id = ?",
                    new String[] {Integer.toString(id_bebida)},
                    null, null, null);

            if(cursor.moveToFirst()){

                String nomeString = cursor.getString(0);
                String descricaoString = cursor.getString(1);
                int fotoId = cursor.getInt(2);


                TextView nome = (TextView) findViewById(R.id.nome);
                nome.setText(nomeString);

                TextView descricao = (TextView) findViewById(R.id.descricao);
                descricao.setText(descricaoString);

                ImageView foto = (ImageView) findViewById(R.id.foto);
                foto.setImageResource(fotoId);
                foto.setContentDescription(nomeString);
            }


            cursor.close();
            db.close();
        }catch (SQLiteException e){

            Toast toast = Toast.makeText(this, "Banco indisponivel", Toast.LENGTH_SHORT);
            Log.d("Erro de banco de dados", e.getMessage());
            toast.show();
        }

    }

    public void onFavoritaClicked(View view){


        int id_bebida = (Integer) getIntent().getExtras().get(EXTRA_BEBIDA_ID);

        CheckBox favorita = (CheckBox) findViewById(R.id.favorita);
        ContentValues bebidaValues = new ContentValues();
        bebidaValues.put("favorita", favorita.isChecked());

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        try{

            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            db.update(
                    "BEBIDA",
                    bebidaValues,
                    "_id = ?",
                    new String[]{Integer.toString(id_bebida)}
            );

            /*Como de costume, fechamos a comunicação com o banco após utiliza-lo*/
            db.close();


        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this, "Banco indisponivel", Toast.LENGTH_SHORT);

            Log.d("Erro de banco de dados", e.getMessage());
            toast.show();
        }

    }
}