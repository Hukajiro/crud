package com.example.crud.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JurusanDao {
    @Insert
    void insertJurusan(Jurusan jurusan);

    @Query("SELECT * FROM jurusan WHERE fakultasId = :fakultasId ORDER BY namaJurusan ASC")
    LiveData<List<Jurusan>> getJurusanByFakultas(int fakultasId);

    @Delete
    void deleteJurusan(Jurusan jurusan);

    @Update
    void updateJurusan(Jurusan jurusan);

    @Query("DELETE FROM jurusan WHERE fakultasId = :fakultasId")
    void deleteJurusanByFakultas(int fakultasId);

    @Query("SELECT * FROM jurusan WHERE id = :jurusanId LIMIT 1")
    LiveData<Jurusan> getJurusanById(int jurusanId);

    @Query("SELECT jurusan.*, fakultas.nama_fakultas AS namaFakultas FROM jurusan " +
            "INNER JOIN fakultas ON jurusan.fakultasId = fakultas.id WHERE jurusan.id = :jurusanId")
    LiveData<JurusanWithFakultas> getJurusanWithFakultasById(int jurusanId);
}
