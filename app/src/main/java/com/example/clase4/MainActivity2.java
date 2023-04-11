package com.example.clase4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.clase4.databinding.ActivityMain2Binding;

//on data binding
public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView2.setText("hola mundo");
        binding.button2.setOnClickListener(view -> {
            binding.textView2.setText("hola ");
        });
    }
}