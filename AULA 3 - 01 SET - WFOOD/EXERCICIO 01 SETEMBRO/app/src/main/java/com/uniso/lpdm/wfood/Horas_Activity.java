package com.uniso.lpdm.wfood;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Horas_Activity extends AppCompatActivity {

    private int segundos = 0;
    private boolean executando;
    private boolean estavaExecutando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horas);
        executando = true;
        estavaExecutando = true;

        if (savedInstanceState != null) {
            segundos = savedInstanceState.getInt("segundos");
            executando = savedInstanceState.getBoolean("executando");
            estavaExecutando = savedInstanceState.getBoolean("estavaExecutadando");
        }
        executarTemporizador();
    }

    /*  usado para a aplicação voltar a ser visivel*/

    @Override
    protected void onRestart() {
        super.onRestart();
        executando = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        estavaExecutando = executando;
        executando = false;
    }

    // Esses métodos são executados sempre que perde foco, e como são executados sempre depois
    // de onstart e onstop, podemos deixar apenas eles;
    @Override
    protected void onPause() {
        super.onPause();
        estavaExecutando = executando;
        executando = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        executando = estavaExecutando;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("segundos", segundos);
        savedInstanceState.putBoolean("executando", executando);
        savedInstanceState.putBoolean("estavaExecutadando", estavaExecutando);

        /* Chamamos a classe pai ao final*/
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onClickIniciar(View view) {
        executando = true;
    }

    public void onClickParar(View view) {
        executando = false;
    }

    public void onClickZerar(View view) {
        executando = false;
        segundos = 0;
    }

    private void executarTemporizador() {

        final TextView textView = (TextView) findViewById(R.id.txtTempo);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                /*Calculo do tempo baseado em segundos*/
                int horas = segundos / 3600;
                int minutos = (segundos % 3600) / 60;
                int segundos_interno = segundos % 60;

                 /*Valores formatados para horas*/
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d",
                       horas, minutos, segundos_interno);

                textView.setText(time);

                if (executando) {
                    segundos++;
                }

                handler.postDelayed(this, 1000);
            }

        });
    }
    
}