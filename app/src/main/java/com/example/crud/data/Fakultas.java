package com.example.crud.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fakultas")
public class Fakultas {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nama_fakultas")
    public String namaFakultas;

    public Fakultas(String namaFakultas) {
        this.namaFakultas = namaFakultas;
    }
    public String getFakultas() { return namaFakultas; }
}