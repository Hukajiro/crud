package com.example.crud.tampilan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.crud.data.JurusanWithFakultas;
import com.example.crud.data.Jurusan;
import com.example.crud.data.JurusanRepository;

import java.util.List;

// JurusanViewModel.java
public class JurusanViewModel extends AndroidViewModel {
    private JurusanRepository repository;
    private LiveData<List<Jurusan>> jurusanByFakultas;

    public JurusanViewModel(@NonNull Application application) {
        super(application);
        repository = new JurusanRepository(application); // Inisialisasi repository
    }

    public LiveData<List<Jurusan>> getJurusanByFakultas(int fakultasId) {
        jurusanByFakultas = repository.getJurusanByFakultas(fakultasId);
        return jurusanByFakultas;
    }

    public LiveData<Jurusan> getJurusanById(int jurusanId) {
        return repository.getJurusanById(jurusanId);
    }

    public LiveData<JurusanWithFakultas> getJurusanWithFakultasById(int jurusanId) {
        return repository.getJurusanWithFakultasById(jurusanId); // Pindahkan ke repository
    }

    public void insert(Jurusan jurusan) {
        repository.insert(jurusan);
    }

    public void delete(Jurusan jurusan) {
        repository.delete(jurusan);
    }

    public void update(Jurusan jurusan) {
        repository.update(jurusan);
    }

    
}
