package com.example.crud.data;

import androidx.room.Embedded;

public class JurusanWithFakultas {
    @Embedded
    public Jurusan jurusan;

    public String namaFakultas;
}
