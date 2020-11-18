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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/*Para ler os comentários desse código, o ideal é começar pela BebidaActivity e depois a
* CategoriaBebidaActivity*/
public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor favoritasCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, //Este é o view clicado, no caso, o list view
                                    View view, // O view que foi clicado, o elemento clicado
                                    int posicao, // a posicao no listview, começando em 0
                                    long id) { // o id da linha

                if(posicao == 0){
                    Intent intent = new Intent(MainActivity.this, CategoriaBebidaActivity.class);
                    startActivity(intent);
                }
                else if(posicao == 1){
                    Intent intent = new Intent(MainActivity.this, CategoriaComidaActivity.class);
                    startActivity(intent);
                }
            }
        };

        ListView listview = (ListView) findViewById(R.id.lista_opcoes);
        listview.setOnItemClickListener(itemClickListener);
        setupFavoritesListView();
    }

    private void setupFavoritesListView(){
        ListView listaFavoritas = (ListView) findViewById(R.id.lista_favoritas);

        try{

            SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);

            db = databaseHelper.getReadableDatabase();
            favoritasCursor = db.query(
                    "BEBIDA",
                    new String[] {"_id", "nome"},
                    "favorita = 1",
                    null, null, null, null
            );

            CursorAdapter favoritasAdapter = new SimpleCursorAdapter(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    favoritasCursor,
                    new String[] {"nome"},
                    new int[] {android.R.id.text1},
                    0
            );

            listaFavoritas.setAdapter(favoritasAdapter);

        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this, "Banco indisponivel", Toast.LENGTH_SHORT);

            Log.d("Erro de banco de dados", e.getMessage());
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Intent intent = new Intent(MainActivity.this, BebidaActivity.class);
                intent.putExtra(BebidaActivity.EXTRA_BEBIDA_ID, (int) id);
                startActivity(intent);
            }
        };

        listaFavoritas.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Cursor novoCursor = db.query(
                "BEBIDA",
                new String[] {"_id", "nome"},
                "favorita = 1",
                null, null, null, null
        );

        ListView listaFavoritas = (ListView) findViewById(R.id.lista_favoritas);
        CursorAdapter adapter = (CursorAdapter) listaFavoritas.getAdapter();
        adapter.changeCursor(novoCursor);
        favoritasCursor = novoCursor;

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        favoritasCursor.close();
        db.close();
    }
}