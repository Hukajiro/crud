package com.example.crud.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.crud.R;
import com.example.crud.data.Fakultas;
import com.example.crud.tampilan.FakultasViewModel;

public class AddFakultasActivity extends AppCompatActivity {
    private EditText edtNamaFakultas;
    private Button btnSave;
    private FakultasViewModel fakultasViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fakultas);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        edtNamaFakultas = findViewById(R.id.edtNamaFakultas);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setEnabled(false);

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

        edtNamaFakultas.addTextChangedListener(textWatcher);
        fakultasViewModel = new ViewModelProvider(this).get(FakultasViewModel.class);

        btnSave.setOnClickListener(view -> {
            String namaFakultas = edtNamaFakultas.getText().toString();
            if (!namaFakultas.isEmpty()) {
                Fakultas fakultas = new Fakultas(namaFakultas);
                fakultasViewModel.insert(fakultas);
                finish();
            } else {
                Toast.makeText(this, "Nama fakultas tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
    }
    private void checkInputFields() {
        String namaFakultas = edtNamaFakultas.getText().toString().trim();

        if (!namaFakultas.isEmpty()) {
            btnSave.setEnabled(true);
            btnSave.setBackgroundResource(R.drawable.btn_background);
        } else {
            btnSave.setEnabled(false);
            btnSave.setBackgroundResource(R.drawable.btn_background);
        }
    }
}
