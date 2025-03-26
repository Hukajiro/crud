package com.example.crud.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.crud.R;
import com.example.crud.data.Jurusan;
import com.example.crud.tampilan.JurusanViewModel;

public class AddJurusanActivity extends AppCompatActivity {
    private EditText edtNamaJurusan, edtJumlahMahasiswa, edtAkreditasi, edtNamaFakultas;
    private TextView tvTitle;
    private Button btnSave;
    private JurusanViewModel jurusanViewModel;
    private int fakultasId;
    private int jurusanId = -1; // Default -1 untuk menandakan mode tambah baru

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jurusan);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        edtNamaJurusan = findViewById(R.id.edtNamaJurusan);
        edtJumlahMahasiswa = findViewById(R.id.edtJumlahMahasiswa);
        edtAkreditasi = findViewById(R.id.edtAkreditasi);
        edtNamaFakultas = findViewById(R.id.edtNamaFakultas);
        tvTitle = findViewById(R.id.tvTitle);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setEnabled(false);

        // Cek apakah ada data yang dikirim (mode edit)
        if (getIntent().hasExtra("id")) {
            jurusanId = getIntent().getIntExtra("id", -1);
            String nama = getIntent().getStringExtra("nama");
            int jumlah = getIntent().getIntExtra("jumlah", 0);
            int akreditasi = getIntent().getIntExtra("akreditasi", 0);
            String namaFakultas = getIntent().getStringExtra("namaFakultas");

            edtNamaJurusan.setText(nama);
            edtJumlahMahasiswa.setText(String.valueOf(jumlah));
            edtAkreditasi.setText(String.valueOf(akreditasi));
            edtNamaFakultas.setText(namaFakultas);
            tvTitle.setText("Edit Jurusan");
        }
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputFields();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        edtNamaJurusan.addTextChangedListener(textWatcher);
        edtJumlahMahasiswa.addTextChangedListener(textWatcher);
        edtAkreditasi.addTextChangedListener(textWatcher);

        fakultasId = getIntent().getIntExtra("fakultasId", -1);
        edtNamaFakultas.setText(getIntent().getStringExtra("namaFakultas"));

        jurusanViewModel = new ViewModelProvider(this).get(JurusanViewModel.class);

        btnSave.setOnClickListener(view -> {
            // Tutup keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            // Lanjutkan proses simpan
            String namaJurusan = edtNamaJurusan.getText().toString().trim();
            String jumlahStr = edtJumlahMahasiswa.getText().toString().trim();
            String akreditasiStr = edtAkreditasi.getText().toString().trim();

            if (namaJurusan.isEmpty() || jumlahStr.isEmpty() || akreditasiStr.isEmpty()) {
                Toast.makeText(this, "Semua bidang harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int jumlah = Integer.parseInt(jumlahStr);
                int arkeditasi = Integer.parseInt(akreditasiStr);
                Jurusan jurusan = new Jurusan(namaJurusan, jumlah, akreditasiStr, fakultasId);

                if (jurusanId != -1) {  // Mode Edit
                    jurusan.setId(jurusanId);
                    jurusanViewModel.update(jurusan);
                } else {  // Mode Tambah Baru
                    jurusanViewModel.insert(jurusan);
                }
                setResult(RESULT_OK);
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Jumlah mahasiswa dan akreditasi harus berupa angka!", Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
    }
    private void checkInputFields() {
        String nama = edtNamaJurusan.getText().toString().trim();
        String jumlah = edtJumlahMahasiswa.getText().toString().trim();
        String arkeditasi = edtAkreditasi.getText().toString().trim();

        if (!nama.isEmpty() && !jumlah.isEmpty() && !arkeditasi.isEmpty()) {
            btnSave.setEnabled(true);
            btnSave.setBackgroundResource(R.drawable.btn_enabled_background); // Ubah ke style tombol aktif
        } else {
            btnSave.setEnabled(false);
            btnSave.setBackgroundResource(R.drawable.btn_disabled_background); // Ubah ke style tombol nonaktif
        }
    }

}
