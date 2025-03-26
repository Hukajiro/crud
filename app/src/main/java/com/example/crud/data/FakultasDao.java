package com.example.crud.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FakultasDao {
    @Insert
    void insertFakultas(Fakultas fakultas);

    @Delete
    void deleteFakultas(Fakultas fakultas);

    @Query("SELECT * FROM fakultas ORDER BY nama_fakultas ASC")
    LiveData<List<Fakultas>> getAllFakultas();
}