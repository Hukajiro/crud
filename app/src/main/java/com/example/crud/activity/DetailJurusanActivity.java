package com.example.crud.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.crud.R;
import com.example.crud.data.Jurusan;
import com.example.crud.tampilan.JurusanViewModel;

public class DetailJurusanActivity extends AppCompatActivity {
    private TextView txtNamaJurusan, txtAkreditasi, txtJumlahMahasiswa, txtNamaFakultas;
    private Jurusan currentJurusan;
    private String namaFakultas;
    private JurusanViewModel jurusanViewModel;
    private int jurusanId;

    private static final int REQUEST_EDIT = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jurusan);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Inisialisasi TextView dengan findViewById() yang benar
        txtNamaJurusan = findViewById(R.id.txtNamaJurusan);
        txtAkreditasi = findViewById(R.id.txtAkreditasi);
        txtJumlahMahasiswa = findViewById(R.id.txtJumlahMahasiswa);
        txtNamaFakultas = findViewById(R.id.txtNamaFakultas);

        jurusanId = getIntent().getIntExtra("jurusanId", -1);
        namaFakultas = getIntent().getStringExtra("namaFakultas");

        Log.d("DetailJurusanActivity", "Received jurusanId: " + jurusanId);
        Log.d("DetailJurusanActivity", "Received namaFakultas: " + namaFakultas);

        // Validasi jurusanId agar tidak -1
        if (jurusanId == -1) {
            Toast.makeText(this, "Jurusan tidak ditemukan!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        jurusanViewModel = new ViewModelProvider(this).get(JurusanViewModel.class);
        loadJurusanData();

        findViewById(R.id.btnTamat).setOnClickListener(v -> {
            if (currentJurusan != null && currentJurusan.getJumlahMahasiswa() > 0) {
                currentJurusan.kurangiJumlah(); // Kurangi jumlah mahasiswa
                jurusanViewModel.update(currentJurusan); // Update database

                // Perbarui tampilan langsung agar lebih responsif
                txtJumlahMahasiswa.setText(String.valueOf(currentJurusan.getJumlahMahasiswa()));

                // Tambahkan Toast agar pengguna tahu aksi berhasil
                Toast.makeText(this, "Mahasiswa telah ditamatkan!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Tidak ada mahasiswa yang bisa ditamatkan!", Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.btnHapus).setOnClickListener(v -> {
            if (currentJurusan == null) {
                Toast.makeText(this, "Data jurusan belum dimuat!", Toast.LENGTH_SHORT).show();
                return;
            }

            new AlertDialog.Builder(this)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus jurusan ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        jurusanViewModel.delete(currentJurusan);
                        Toast.makeText(this, "Jurusan berhasil dihapus!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnEdit).setOnClickListener(v -> {
            if (currentJurusan != null) {
                Intent intent = new Intent(this, AddJurusanActivity.class);
                intent.putExtra("id", currentJurusan.getId());
                intent.putExtra("namaJurusan", currentJurusan.getNama());
                intent.putExtra("akreditasi", currentJurusan.getAkreditasi());
                intent.putExtra("jumlahMahasiswa", currentJurusan.getJumlahMahasiswa());
                intent.putExtra("fakultasId", currentJurusan.getFakultas());
                intent.putExtra("namaFakultas", txtNamaFakultas.getText().toString());
                startActivityForResult(intent, REQUEST_EDIT);
            } else {
                Toast.makeText(this, "Jurusan belum dimuat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadJurusanData() {
        jurusanViewModel.getJurusanById(jurusanId).observe(this, jurusan -> {
            if (jurusan == null) {
                Toast.makeText(this, "Data jurusan tidak ditemukan", Toast.LENGTH_SHORT).show();
                return;
            }

            currentJurusan = jurusan;

            txtNamaJurusan.setText(jurusan.getNama());
            txtAkreditasi.setText(jurusan.getAkreditasi());
            txtJumlahMahasiswa.setText(String.valueOf(jurusan.getJumlahMahasiswa()));
            txtNamaFakultas.setText(namaFakultas);

            Log.d("DetailJurusanActivity", "Jurusan Loaded: " + jurusan.getNama());
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK && data != null) {
            loadJurusanData();
        }
    }
}
