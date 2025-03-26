package com.example.crud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.tampilan.JurusanAdapter;
import com.example.crud.tampilan.JurusanViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class JurusanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddJurusan;
    private JurusanAdapter adapter;
    private JurusanViewModel jurusanViewModel;
    private int fakultasId;
    private String namaFakultas;
    private TextView textEmpty, tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurusan);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.recyclerJurusan);
        fabAddJurusan = findViewById(R.id.fabAddJurusan);

        fakultasId = getIntent().getIntExtra("fakultasId", -1);
        namaFakultas = getIntent().getStringExtra("namaFakultas");
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(namaFakultas);

        adapter = new JurusanAdapter(new ArrayList<>(), jurusan -> {
            Intent intent = new Intent(this, DetailJurusanActivity.class);
            intent.putExtra("jurusanId", jurusan.getId());
            intent.putExtra("fakultasId", fakultasId);
            intent.putExtra("namaFakultas", namaFakultas);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        jurusanViewModel = new ViewModelProvider(this).get(JurusanViewModel.class);
        textEmpty = findViewById(R.id.textEmpty);
        jurusanViewModel.getJurusanByFakultas(fakultasId).observe(this, jurusanList -> {
            if (jurusanList.isEmpty()) {
                textEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                textEmpty.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setJurusanList(jurusanList);
            }
        });



        fabAddJurusan.setOnClickListener(view -> {
            Intent intent = new Intent(JurusanActivity.this, AddJurusanActivity.class);
            intent.putExtra("fakultasId", fakultasId);
            intent.putExtra("namaFakultas", namaFakultas);
            startActivity(intent);
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
    }
}
