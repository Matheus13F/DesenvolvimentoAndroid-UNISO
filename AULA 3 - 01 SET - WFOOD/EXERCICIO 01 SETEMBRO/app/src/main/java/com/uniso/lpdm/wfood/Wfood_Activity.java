package com.uniso.lpdm.wfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Wfood_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wfood_main);
    }

    //função para registrar na tela um texto, acionada por um botao especifico
    public void onClickResgistrarPedido(View view) { //funcao para exibir texto na tela
        TextView registrarPedido = (TextView) findViewById(R.id.txtRegistro);
        registrarPedido.setText("Registrando pedido...");
    }

    //função em que quando acionada pelo bota, abrira na tela uma nova activity, com novas funcionalidades
    public void onEnviarParaCozinha(View view) {
        Intent intencao = new Intent(this, CozinhaActivity.class);
        startActivity(intencao);
    }

    //nova intecao, para abrir a nova activiy
    public void onEnivarHoras(View view) {
        Intent intencao = new Intent(this, Horas_Activity.class);
        startActivity(intencao);
    }
}