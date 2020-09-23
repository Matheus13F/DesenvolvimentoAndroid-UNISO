package com.uniso.lpdm.wfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Estoque_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);
    }

    //codigo que apenas inicia uma nova atividade, direcionando cada botao e uma nova atividade

    public void onNovoProduto(View view) {
        Intent intencao = new Intent(this, NovoProduto_Activity.class);
        startActivity(intencao);
    }

    public void onAtualizarProduto (View view) {
        Intent intencao = new Intent(this, AtualizarProdutoActivity.class);
        startActivity(intencao);
    }

    public void onEnviarEmail (View view) {
        Intent intencao = new Intent(this, EnviarEmailActivity.class);
        startActivity(intencao);
    }


}