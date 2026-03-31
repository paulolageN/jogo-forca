package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityResultadoBinding;

public class ResultadoActivity extends AppCompatActivity {
    ActivityResultadoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResultadoBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent activityCategorias = new Intent(this, CategoriasActivity.class);

        Bundle ganhouOuPerdeu = getIntent().getExtras();


        if(ganhouOuPerdeu != null){
            int resultado = ganhouOuPerdeu.getInt("resultado");
            if(resultado == 1){
                binding.imgResultado.setImageResource(R.mipmap.img_perdeu);
            }
            else if(resultado == 2){
                binding.imgResultado.setImageResource(R.mipmap.img_ganhou);
            }
        }


        binding.btnJogarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(activityCategorias);
            }
        });
    }
}