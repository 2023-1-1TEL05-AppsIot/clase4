package com.example.clase4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clase4.databinding.ActivityMainBinding;
import com.example.clase4.viewmodels.ContadorViewModel;

import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppThreads appThreads = (AppThreads) getApplication();
        ExecutorService executorService = appThreads.executorService;

        binding.button.setOnClickListener(view -> {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int contador = 1;

                    while (contador <= 10) {
                        System.out.println("contador: " + contador);

                        //mandar al viewmodel -> livedata
                        ContadorViewModel contadorViewModel =
                                new ViewModelProvider(MainActivity.this).get(ContadorViewModel.class);
                        contadorViewModel.getContador().postValue(contador);

                        contador++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }


                }
            });
        });

        ContadorViewModel contadorViewModel = new ViewModelProvider(MainActivity.this)
                .get(ContadorViewModel.class);

        contadorViewModel.getContador().observe(this, contador -> {
            binding.textView.setText("contador: " + contador);
        });


    }
}