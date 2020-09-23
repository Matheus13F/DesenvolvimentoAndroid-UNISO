package com.uniso.lpdm.wfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NovoProduto_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);
    }

    //como informado no codigo do layout, este codigo ainda nao esta completo, sera desenvolvido em aula
    //o codigo apenas informa uma mensagem na tela quando clicado no botao

    public void onSalvarNovoProduto(View view){
        TextView registrarPedido = (TextView) findViewById(R.id.txtSalvarNovoProduto);
        registrarPedido.setText("Produto Salvo com Sucesso!");
    }
}