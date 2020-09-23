package com.uniso.lpdm.wfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnviarEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_email);
    }

    public void onEnviarEmailFornecedor(View view){

        //este codigo permite que o texto digitado seja enviado por email, captando as informaçoes digitadas e inserindo no corpo e assunto do email

        EditText editText =(EditText) findViewById(R.id.editNomeProdutoNovo);
        String mensagem = editText.getText().toString();

        EditText editText1 = (EditText) findViewById(R.id.editQuantidade);
        String mensagem2 = editText1.getText().toString();

        Intent intencao = new Intent(Intent.ACTION_SEND);
        intencao.setType("text/plain");
        intencao.putExtra(Intent.EXTRA_TEXT, mensagem + " - Total quantidade: " + mensagem2);
        intencao.putExtra(Intent.EXTRA_SUBJECT, "Produtos em falta - fornecedores");


        //inicia a nova activity dentro do app
        startActivity(intencao);
    }
}