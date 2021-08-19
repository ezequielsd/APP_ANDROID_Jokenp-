package com.ezequieldaniel.pedrapapeloutesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int escolhaRoboAnterior = 0;
    Timer timerResetJogo = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selecionadoPedra(View view){
        this.opcaoSelecionada(enumOpcao.pedra);
    }

    public void selecionadoPapel(View view){
        this.opcaoSelecionada(enumOpcao.papel);
    }

    public void selecionadoTesoura(View view){
        this.opcaoSelecionada(enumOpcao.tesoura);
    }

    //Método roda o jogo, recebe como parametro a opção selecionada enumerador
    // 0 - pedra
    // 1 - papel
    // 2 - tesoura
    public void opcaoSelecionada(enumOpcao opcaoUsuario)  {
        //pega a imagemView resultado para trocar a imagem
        ImageView imageResult = findViewById(R.id.imageViewResultado);
        TextView textResult = findViewById(R.id.textViewResultado);

        int numeroRobo = 0;

        //gera numero aleatorio para o jogo
        //sorteia enquando estiver o mesmo numero que anterior, para evitar repedição
        while(numeroRobo == escolhaRoboAnterior){
            numeroRobo = new Random().nextInt(3);
        }

        escolhaRoboAnterior = numeroRobo;

        //converte o numero sorteado em um item do enumerador
        enumOpcao opcaoRobo = enumOpcao.values()[numeroRobo];

        switch (opcaoRobo){
            case papel:
                imageResult.setImageResource(R.drawable.papel);
                break;
            case pedra:
                imageResult.setImageResource(R.drawable.pedra);
                break;
            case tesoura:
                imageResult.setImageResource(R.drawable.tesoura);
                break;
            default:
                imageResult.setImageResource(R.drawable.padrao);
                break;
        }

        //verifica o vencedor
        verificaVencedor(opcaoUsuario, opcaoRobo, imageResult, textResult);

        Handler handler = new Handler();

        // tarefa postergada por 5000 milissegundos
        handler.postDelayed(new Runnable() {
            public void run() {
                imageResult.setImageResource(R.drawable.padrao);
                textResult.setText("Escolha uma opção abaixo");
            }
        }, 700);

        //imageResult.setImageResource(R.drawable.padrao);
        //textResult.setText("Escolha uma opção abaixo");
    }

    private void verificaVencedor(enumOpcao opcaoUsuario, enumOpcao opcaoRobo,  ImageView imageResult, TextView textResult)
    {
        if( (opcaoRobo == enumOpcao.tesoura && opcaoUsuario == enumOpcao.papel) ||
                (opcaoRobo == enumOpcao.papel && opcaoUsuario == enumOpcao.pedra) ||
                (opcaoRobo == enumOpcao.pedra && opcaoUsuario == enumOpcao.tesoura)
        ){
            textResult.setText("Você perdeu!");
        }else if( (opcaoUsuario == enumOpcao.tesoura && opcaoRobo == enumOpcao.papel) ||
                (opcaoUsuario == enumOpcao.papel && opcaoRobo == enumOpcao.pedra) ||
                (opcaoUsuario == enumOpcao.pedra && opcaoRobo == enumOpcao.tesoura)
        ){
            textResult.setText("Você ganhou!");
        }
        else {
            textResult.setText("Empatou!");
        }
    }
}