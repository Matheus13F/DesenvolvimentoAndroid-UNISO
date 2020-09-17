package com.uniso.lpdm.wfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CozinhaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cozinha);
    }

    //função criada para capturar o texto do editText e enviar para outro aplicativo, no caso, whatsaap

    public void onEnviarPedidoZAP(View view){
        EditText editText =(EditText) findViewById(R.id.edit_Mensagem);
        String mensagem = editText.getText().toString();

        Intent intencao = new Intent(Intent.ACTION_SEND);
        intencao.setType("text/plain");
        intencao.putExtra(Intent.EXTRA_TEXT, mensagem);
        intencao.putExtra(Intent.EXTRA_SUBJECT, "Assunto");

        //inicia a nova activity dentro do app
        startActivity(intencao);
    }
}