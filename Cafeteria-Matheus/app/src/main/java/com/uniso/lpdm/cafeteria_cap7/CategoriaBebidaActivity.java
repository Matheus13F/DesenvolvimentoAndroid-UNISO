package com.uniso.lpdm.cafeteria_cap7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class CategoriaBebidaActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_bebida);

        ListView listView = findViewById(R.id.lista_bebidas);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);

        try {
            db = databaseHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT _id, nome from bebida", null, null);
            cursor = db.query(
                  "bebida",
                  new String[] {"_id", "nome"},
                  null, null, null, null, "nome ASC"
            );

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {"nome"},
                    new int[] {android.R.id.text1},
                    0
            );

            listView.setAdapter(listAdapter);
        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this, "Banco indisponivel", Toast.LENGTH_SHORT);
            Log.d("Erro de banco de dados", e.getMessage());
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Intent intent = new Intent(CategoriaBebidaActivity.this, BebidaActivity.class);
                intent.putExtra(BebidaActivity.EXTRA_BEBIDA_ID, (int) id);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }

    @Override

    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}