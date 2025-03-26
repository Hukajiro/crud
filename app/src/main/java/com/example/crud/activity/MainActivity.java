package com.example.crud.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.tampilan.FakultasAdapter;
import com.example.crud.tampilan.FakultasViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddFakultas;
    private FakultasAdapter adapter;
    private FakultasViewModel fakultasViewModel;
    private TextView textEmpty;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Inisialisasi UI
        recyclerView = findViewById(R.id.recyclerView);
        fabAddFakultas = findViewById(R.id.fabAddFakultas);
        textEmpty = findViewById(R.id.textEmpty);

        // Cek apakah RecyclerView ditemukan di layout
        if (recyclerView == null) {
            Log.e("MainActivity", "RecyclerView tidak ditemukan di XML! Periksa ID di activity_main.xml.");
            return;
        }

        // Inisialisasi Adapter
        adapter = new FakultasAdapter(new ArrayList<>(), fakultas -> {
            if (fakultas != null) {
                Intent intent = new Intent(MainActivity.this, JurusanActivity.class);
                intent.putExtra("fakultasId", fakultas.id);
                intent.putExtra("namaFakultas", fakultas.namaFakultas);
                startActivity(intent);
            }
        }, fakultas -> {
            if (fakultas != null) {
                fakultasViewModel.delete(fakultas);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Inisialisasi ViewModel
        fakultasViewModel = new ViewModelProvider(this).get(FakultasViewModel.class);
        fakultasViewModel.getAllFakultas().observe(this, fakultasList -> {
            if (fakultasList == null || fakultasList.isEmpty()) {
                textEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                textEmpty.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setFakultasList(fakultasList);
            }
        });

        // Tombol Floating Action Button
        if (fabAddFakultas != null) {
            fabAddFakultas.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, AddFakultasActivity.class);
                startActivity(intent);
            });
        } else {
            Log.e("MainActivity", "FAB Add Fakultas tidak ditemukan!");
        }
    }
}
