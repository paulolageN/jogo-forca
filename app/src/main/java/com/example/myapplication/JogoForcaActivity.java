package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityJogoForcaBinding;

import java.util.ArrayList;
import java.util.Random;

public class JogoForcaActivity extends AppCompatActivity {
    ActivityJogoForcaBinding binding;
    ArrayList<String> aleatorios, animais, esportes, frutas, palavraSorteada;
    ArrayList<Integer> imagensForca;
    String Palavra = "";
    int erros = 0;
    int acertos = 0;

    // metodo para inserir palavras aleatorias dentro do array
    public void inserirAleatorios(){
        aleatorios.add("capacete");
        aleatorios.add("teclado");
        aleatorios.add("janela");
        aleatorios.add("caderno");
        aleatorios.add("mochila");
        aleatorios.add("espelho");
        aleatorios.add("garrafa");
        aleatorios.add("cadeira");
        aleatorios.add("controle");
        aleatorios.add("ventilador");

    }

    // metodo para inserir nomes de animais dentro do array
    public void inserirAnimais(){
        animais.add("cachorro");
        animais.add("gato");
        animais.add("elefante");
        animais.add("girafa");
        animais.add("leao");
        animais.add("tigre");
        animais.add("macaco");
        animais.add("zebra");
        animais.add("coelho");
        animais.add("cavalo");
    }

    // metodo para inserir nomes de esportes dentro do array
    public void inserirEsportes(){
        esportes.add("futebol");
        esportes.add("basquete");
        esportes.add("volei");
        esportes.add("natacao");
        esportes.add("tenis");
        esportes.add("handebol");
        esportes.add("corrida");
        esportes.add("ciclismo");
        esportes.add("boxe");
        esportes.add("skate");
    }

    // metodo para inserir nomes de frutas dentro do array
    public void inserirFrutas(){
        frutas.add("banana");
        frutas.add("maca");
        frutas.add("laranja");
        frutas.add("abacaxi");
        frutas.add("morango");
        frutas.add("uva");
        frutas.add("melancia");
        frutas.add("pera");
        frutas.add("kiwi");
        frutas.add("manga");
    }

    // metodo para inserir palavra sorteado no array palavra
    public void inserirPalavraSorteada(String palavra){
        palavraSorteada.clear();
        for(int i=0; i<palavra.length();i++){
            palavraSorteada.add("_");
        }
    }

    // metodo para imprimir a palavra sorteada
    public void imprimirPalavraSorteada(){
        String palavra = "";
        for(String letra: palavraSorteada){
            palavra += letra+" ";
        }
        binding.txtPalavraSorteada.setText(palavra);
    }

    public boolean verificarLetra(char letra, String palavra){
        boolean temLetra = false;
        for(int i=0;i<palavra.length();i++){
            char L = palavra.charAt(i);
            if (L == letra){
                temLetra = true;
                palavraSorteada.set(i, Character.toString(letra));
                acertos++;
            }
        }

        if(erros>=6){
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putInt("resultado", 1);
                    Intent resultadoPerdeu = new Intent(JogoForcaActivity.this, ResultadoActivity.class);
                    resultadoPerdeu.putExtras(bundle);
                    startActivity(resultadoPerdeu);
                }
            },3000);

        }


        if(acertos==palavra.length()){
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putInt("resultado", 2);
                    Intent resultadoPerdeu = new Intent(JogoForcaActivity.this, ResultadoActivity.class);
                    resultadoPerdeu.putExtras(bundle);
                    startActivity(resultadoPerdeu);
                }
            },3000);
        }

        return temLetra;
    }

    // mudar imagem da forca
    public void mudarImagemForca(){
        erros++;
        binding.imgForca.setImageResource(imagensForca.get(erros));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityJogoForcaBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imagensForca = new ArrayList<>();
        imagensForca.add(R.mipmap.forca_00);
        imagensForca.add(R.mipmap.forca_01);
        imagensForca.add(R.mipmap.forca_02);
        imagensForca.add(R.mipmap.forca_03);
        imagensForca.add(R.mipmap.forca_04);
        imagensForca.add(R.mipmap.forca_05);
        imagensForca.add(R.mipmap.forca_06);

        binding.imgForca.setImageResource(imagensForca.get(erros));



        Intent activityCategorias = new Intent(this, CategoriasActivity.class);

        aleatorios = new ArrayList<>();
        animais = new ArrayList<>();
        esportes = new ArrayList<>();
        frutas = new ArrayList<>();
        palavraSorteada = new ArrayList<>();

        // classe para gerar um numero aleatorio
        Random random = new Random();

        Bundle dadosRecebidos = getIntent().getExtras();


        if(dadosRecebidos != null){
            int categoria = dadosRecebidos.getInt("categorias");
            if(categoria == 1){
                // mudar imagem
                binding.imgCategoria.setImageResource(R.mipmap.img_aleatorio);
                inserirAleatorios();
                Palavra = aleatorios.get(random.nextInt(aleatorios.size()-1));
            }
            else if(categoria == 2){
                // mudar imagem
                binding.imgCategoria.setImageResource(R.mipmap.img_animais);
                inserirAnimais();
                Palavra = animais.get(random.nextInt(animais.size()-1));
            }
            else if(categoria == 3){
                // mudar imagem
                binding.imgCategoria.setImageResource(R.mipmap.img_esportes);
                inserirEsportes();
                Palavra = esportes.get(random.nextInt(esportes.size()-1));
            }
            else{
                // mudar imagem
                binding.imgCategoria.setImageResource(R.mipmap.img_frutas);
                inserirFrutas();
                Palavra = frutas.get(random.nextInt(frutas.size()-1));
            }
        }


        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(activityCategorias);
            }
        });

        binding.btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('a', Palavra);
                if(achou == true){
                    binding.btnA.setImageResource(R.mipmap.letra_a_correto);
                }else{
                    binding.btnA.setImageResource(R.mipmap.letra_a_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('b', Palavra);
                if(achou){
                    binding.btnB.setImageResource(R.mipmap.letra_b_correto);
                }else{
                    binding.btnB.setImageResource(R.mipmap.letra_b_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('c', Palavra);
                if(achou){
                    binding.btnC.setImageResource(R.mipmap.letra_c_correto);
                }else{
                    binding.btnC.setImageResource(R.mipmap.letra_c_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('d', Palavra);
                if(achou){
                    binding.btnD.setImageResource(R.mipmap.letra_d_correto);
                }else{
                    binding.btnD.setImageResource(R.mipmap.letra_d_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('e', Palavra);
                if(achou){
                    binding.btnE.setImageResource(R.mipmap.letra_e_correto);
                }else{
                    binding.btnE.setImageResource(R.mipmap.letra_e_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('f', Palavra);
                if(achou){
                    binding.btnF.setImageResource(R.mipmap.letra_f_correto);
                }else{
                    binding.btnF.setImageResource(R.mipmap.letra_f_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('g', Palavra);
                if(achou){
                    binding.btnG.setImageResource(R.mipmap.letra_g_correto);
                }else{
                    binding.btnG.setImageResource(R.mipmap.letra_g_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('h', Palavra);
                if(achou){
                    binding.btnH.setImageResource(R.mipmap.letra_h_correto);
                }else{
                    binding.btnH.setImageResource(R.mipmap.letra_h_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('i', Palavra);
                if(achou){
                    binding.btnI.setImageResource(R.mipmap.letra_i_correto);
                }else{
                    binding.btnI.setImageResource(R.mipmap.letra_i_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('j', Palavra);
                if(achou){
                    binding.btnJ.setImageResource(R.mipmap.letra_j_correto);
                }else{
                    binding.btnJ.setImageResource(R.mipmap.letra_j_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('k', Palavra);
                if(achou){
                    binding.btnK.setImageResource(R.mipmap.letra_k_correto);
                }else{
                    binding.btnK.setImageResource(R.mipmap.letra_k_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('l', Palavra);
                if(achou){
                    binding.btnL.setImageResource(R.mipmap.letra_l_correto);
                }else{
                    binding.btnL.setImageResource(R.mipmap.letra_l_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('m', Palavra);
                if(achou){
                    binding.btnM.setImageResource(R.mipmap.letra_m_correto);
                }else{
                    binding.btnM.setImageResource(R.mipmap.letra_m_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('n', Palavra);
                if(achou){
                    binding.btnN.setImageResource(R.mipmap.letra_n_correto);
                }else{
                    binding.btnN.setImageResource(R.mipmap.letra_n_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('o', Palavra);
                if(achou){
                    binding.btnO.setImageResource(R.mipmap.letra_o_correto);
                }else{
                    binding.btnO.setImageResource(R.mipmap.letra_o_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('p', Palavra);
                if(achou){
                    binding.btnP.setImageResource(R.mipmap.letra_p_correto);
                }else{
                    binding.btnP.setImageResource(R.mipmap.letra_p_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('q', Palavra);
                if(achou){
                    binding.btnQ.setImageResource(R.mipmap.letra_q_correto);
                }else{
                    binding.btnQ.setImageResource(R.mipmap.letra_q_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('r', Palavra);
                if(achou){
                    binding.btnR.setImageResource(R.mipmap.letra_r_correto);
                }else{
                    binding.btnR.setImageResource(R.mipmap.letra_r_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('s', Palavra);
                if(achou){
                    binding.btnS.setImageResource(R.mipmap.letra_s_correto);
                }else{
                    binding.btnS.setImageResource(R.mipmap.letra_s_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('t', Palavra);
                if(achou){
                    binding.btnT.setImageResource(R.mipmap.letra_t_correto);
                }else{
                    binding.btnT.setImageResource(R.mipmap.letra_t_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('u', Palavra);
                if(achou){
                    binding.btnU.setImageResource(R.mipmap.letra_u_correto);
                }else{
                    binding.btnU.setImageResource(R.mipmap.letra_u_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('v', Palavra);
                if(achou){
                    binding.btnV.setImageResource(R.mipmap.letra_v_correto);
                }else{
                    binding.btnV.setImageResource(R.mipmap.letra_v_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('w', Palavra);
                if(achou){
                    binding.btnW.setImageResource(R.mipmap.letra_w_correto);
                }else{
                    binding.btnW.setImageResource(R.mipmap.letra_w_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('x', Palavra);
                if(achou){
                    binding.btnX.setImageResource(R.mipmap.letra_x_correto);
                }else{
                    binding.btnX.setImageResource(R.mipmap.letra_x_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('y', Palavra);
                if(achou){
                    binding.btnY.setImageResource(R.mipmap.letra_y_correto);
                }else{
                    binding.btnY.setImageResource(R.mipmap.letra_y_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('z', Palavra);
                if(achou){
                    binding.btnZ.setImageResource(R.mipmap.letra_z_correto);
                }else{
                    binding.btnZ.setImageResource(R.mipmap.letra_z_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });

        binding.btnCedilha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean achou = verificarLetra('c', Palavra);
                if(achou){
                    binding.btnZ.setImageResource(R.mipmap.letra_z_correto);
                }else{
                    binding.btnZ.setImageResource(R.mipmap.letra_z_errado);
                    mudarImagemForca();
                }
                imprimirPalavraSorteada();
            }
        });


    }
}