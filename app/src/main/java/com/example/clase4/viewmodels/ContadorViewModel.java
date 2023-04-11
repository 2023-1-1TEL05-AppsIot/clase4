package com.example.clase4.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.clase4.Persona;

import java.util.ArrayList;

public class ContadorViewModel extends ViewModel {
    private final MutableLiveData<Integer> contador = new MutableLiveData<>();
    private final MutableLiveData<String> nombre = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Persona>> lista = new MutableLiveData<>();

    public MutableLiveData<Integer> getContador() {
        return contador;
    }

    public MutableLiveData<String> getNombre() {
        return nombre;
    }

    public MutableLiveData<ArrayList<Persona>> getLista() {
        return lista;
    }
}
