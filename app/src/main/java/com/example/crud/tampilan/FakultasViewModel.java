package com.example.crud.tampilan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.crud.data.Fakultas;
import com.example.crud.data.FakultasRepository;

import java.util.List;

// FakultasViewModel.java
public class FakultasViewModel extends AndroidViewModel {
    private FakultasRepository repository;
    private LiveData<List<Fakultas>> allFakultas;

    public FakultasViewModel(@NonNull Application application) {
        super(application);
        repository = new FakultasRepository(application);
        allFakultas = repository.getAllFakultas();
    }

    public LiveData<List<Fakultas>> getAllFakultas() {
        return allFakultas;
    }

    public void insert(Fakultas fakultas) {
        repository.insert(fakultas);
    }
    public void delete(Fakultas fakultas) {
        repository.delete(fakultas);
    }
}
