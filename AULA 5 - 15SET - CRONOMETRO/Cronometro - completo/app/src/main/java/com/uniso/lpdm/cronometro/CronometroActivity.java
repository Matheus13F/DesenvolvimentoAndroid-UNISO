package com.uniso.lpdm.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class CronometroActivity extends AppCompatActivity {

    private int segundos = 0;
    private boolean executando;
    private boolean estavaExecutadando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        executando = true;
        estavaExecutadando=true;

        /*Se o savedInstanceState não for nulo, significa que a atividade já estava rodando
        * em algum momento e salvamos o estado dela. Precisamos fazer essa verificação
        * uma vez que a primeira vez que abrimos o aplicativo, esse objeto estará nulo e
        * teremos um erro*/
        if(savedInstanceState != null){
            segundos = savedInstanceState.getInt("segundos");
            executando = savedInstanceState.getBoolean("executando");
            estavaExecutadando = savedInstanceState.getBoolean("estavaExecutadando");
        }

        /*Quando a atividade iniciar, o temporizador já precisa estar executando
        * Ele só vai incrementar os segundos quando a variavel que controla isso estiver
        * ativa, mas ele já precisa estar rodando em paralelo logo que iniciamos para o
        * usuário poder iniciar o cronometro
        *
        * O temporizador só para de ser executado quando a atividade é destruída.
        * Para o restante dos métodos e funções do ciclo de vida, ele ainda está em execução*/
        executarTemporizador();
    }

    /*Para salvarmos o estado da atividade precisamos implementar o método/função onSaveInstanceState
    * Ele é chamado antes da destruição da atividade, para que possamos salvar os valores que precisarmos
    * O metodo está acusando erro porque não estamos chamando um m*/
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        /*Similiarmente como fazemos quando queremos incluri valores extra na intenção
         * para enviar para outra atividade, incluimos valores no Bundle, que irá representar
         * o estado atual da atividade antes da destruição.*/
        savedInstanceState.putInt("segundos", segundos);
        savedInstanceState.putBoolean("executando", executando);
        savedInstanceState.putBoolean("estavaExecutadando", estavaExecutadando);
        /* Como estamos trabalhando com um método função sobrescrito, chamamos o método da classe
        * pai ao final*/
        super.onSaveInstanceState(savedInstanceState);

    }

    // Esses métodos são executados sempre que perde foco, e como são executados sempre depois
    // de onstart e onstop, podemos deixar apenas eles;
    @Override
    protected void onPause() {
        super.onPause();
        estavaExecutadando = executando;
        executando = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        executando = estavaExecutadando;
    }

/*  Métodos usados quando a aplicação deixa de ser visivel e volta a ser visivel, não funcionam
    quando a aplicação perde o foco mas continua visivel
    @Override
    protected void onStop() {
        super.onStop();
        estavaExecutadando = estavaExecutadando;
        executando = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        executando = estavaExecutadando;
    } */

    public void onClickIniciar(View view){
        executando = true;
    }

    public void onClickParar(View view){
        executando = false;
    }

    public void onClickZerar(View view){
        executando = false;
        segundos = 0;
    }

    private void executarTemporizador(){
        /*a variavel precisa ser final para podermos acess-la dentro do handler*/
        final TextView textView = (TextView) findViewById(R.id.txtTempo);
        /*O Handler é uma classe android utilizada para agendar a execução do código
        * par algum momento no futuro. Essa classe, tembém pode postar código para ser executado
        * em uma thread diferente da thread principal do app. Aqui ele será responsável
        * em a cada segundo, incrementar a variavel de segundos, e então formatar o tempo
        * e exibir no nosso temporizador do textview*/
        final Handler handler = new Handler();

        /*o Handler possui um método/função chamado post, responsavel por executar, ou agendar a execução
        * do código. Nesse método/função, é necessário passar como parametro um objeto do tipo Runnable,
        * ou seja, o conteúdo de uma variavel do tipo Runnable. Precisamos desse parametro para o post
        * porque é no objeto Runnable que escrevemos a função run() (ou para quem viu java, sobrescrevemos
        * o método run()). Nesse método/função é que colocamos o código que será executado quando post()
        * for chamado.
        *
        * Para passarmos o parametro, precisariamos criar uma classe que implementasse o Runnable,
        * porém não é o que estamos fazendo aqui.
        * Aqui usamos o conceito de uma classe anonima. Isso significa que estamos
        * criando uma classe que implementa a classe Runnable, mas não damos um nome
        * para essa classe, ou a instanciamos (isso é, criamos uma variavel para armazenar
        * um objeto).
        *
        * Isso é possível porque não costumamos interagir muito com classes que implementam Runnable,
        * ela é usada apenas para podermos colocar o conteúdo de run(). Esse é um conceito importante
        * também para o que chamamos de programação funcional no java.
        *
        * */
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             /*Calculamos o tempo baseados em quantos segundos passaram*/
                             int horas = segundos/3600;
                             int minutos = (segundos%3600)/60;
                             int segundos_interno = segundos%60;

                             /*Formatamos os valores inteiros usando o String.format, para ser exibido
                             * no text view*/
                             String time = String.format(Locale.getDefault(), "%d:%02d:%02d",
                                     horas, minutos, segundos_interno);

                             // Colocamos o texto no text view
                             textView.setText(time);
                             // se nosso cronomotro não tiver sido parado usando o botão para isso, então
                             // incrementamos os segundos
                             if(executando){
                                 segundos++;
                             }

                             /*Aqui funciona como uma chamada recursiva. Fazemos com que nosso código seja
                             * executado daqui 1000 milisegundos (1 segundo). Quando esse tempo passar
                             * o método/função será executado novamente*/
                             handler.postDelayed(this, 1000);
                         }
                     }
        );
    }


    public void mudarTela(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}