package com.example.clase4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.clase4.databinding.ActivityMainBinding;
import com.example.clase4.models.Profile;
import com.example.clase4.models.TypicodeRepository;
import com.example.clase4.viewmodels.ContadorViewModel;

import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        //jsonschema2pojo.org
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TypicodeRepository typicodeRepository = new Retrofit.Builder()
                        .baseUrl("https://my-json-server.typicode.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(TypicodeRepository.class);

                typicodeRepository.getProfile().enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if(response.isSuccessful()){
                            Profile profile = response.body();
                            binding.textViewProfile.setText(profile.getName());
                        }else{
                            Log.d("msg-test","error en la respuesta");
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });


    }
}