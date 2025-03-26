package com.example.crud.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

// JurusanRepository.java
public class JurusanRepository {
    private JurusanDao jurusanDao;
    private LiveData<List<Jurusan>> jurusanByFakultas;

    public JurusanRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        jurusanDao = db.jurusanDao(); // Inisialisasi jurusanDao
    }

    public LiveData<List<Jurusan>> getJurusanByFakultas(int fakultasId) {
        return jurusanDao.getJurusanByFakultas(fakultasId);
    }

    public LiveData<Jurusan> getJurusanById(int jurusanId) {
        return jurusanDao.getJurusanById(jurusanId);
    }

    public LiveData<JurusanWithFakultas> getJurusanWithFakultasById(int jurusanId) {
        return jurusanDao.getJurusanWithFakultasById(jurusanId); // Pindahkan ke repository
    }

    public void insert(Jurusan jurusan) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            jurusanDao.insertJurusan(jurusan);
        });
    }

    public void delete(Jurusan jurusan) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            jurusanDao.deleteJurusan(jurusan);
        });
    }

    public void update(Jurusan jurusan) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            jurusanDao.updateJurusan(jurusan);
        });
    }
}
