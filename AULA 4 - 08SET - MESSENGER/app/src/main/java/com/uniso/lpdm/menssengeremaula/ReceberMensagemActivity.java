package com.uniso.lpdm.menssengeremaula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceberMensagemActivity extends AppCompatActivity {

    public static final String MENSAGEM_EXTRA = "texto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receber_mensagem);

        Intent intencaoRecebida = getIntent();
        String mensagem = intencaoRecebida.getStringExtra(Intent.EXTRA_TEXT);

        TextView mensagemRecebida = findViewById(R.id.txtMensagemRecebida);
        mensagemRecebida.setText(mensagem);
    }
}