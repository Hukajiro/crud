package com.example.crud.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FakultasRepository {
    private FakultasDao fakultasDao;
    private LiveData<List<Fakultas>> allFakultas;

    public FakultasRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        fakultasDao = db.fakultasDao();
        allFakultas = fakultasDao.getAllFakultas();
    }

    public LiveData<List<Fakultas>> getAllFakultas() {
        return allFakultas;
    }

    public void insert(Fakultas fakultas) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            fakultasDao.insertFakultas(fakultas);
        });
    }
    public void delete(Fakultas fakultas) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            fakultasDao.deleteFakultas(fakultas);
        });
    }
}
