package com.uniso.lpdm.menssengeremaula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MessengerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
    }

    public void onEnviarMensagemPrimeira(View view){

        EditText editText =(EditText) findViewById(R.id.edit_Mensagem);
        String mensagem = editText.getText().toString();

        Intent intencao = new Intent(this, ReceberMensagemActivity.class);

        intencao.putExtra(ReceberMensagemActivity.MENSAGEM_EXTRA, mensagem);

        startActivity(intencao);

    }

    public void onEnviarMensagemSegunda(View view){

        EditText editText =(EditText) findViewById(R.id.edit_Mensagem);
        String mensagem = editText.getText().toString();

        Intent intencao = new Intent(Intent.ACTION_SEND);
        intencao.setType("text/plain");
        intencao.putExtra(Intent.EXTRA_TEXT, mensagem);
        intencao.putExtra(Intent.EXTRA_SUBJECT, "Assunto");

        startActivity(intencao);

    }
}