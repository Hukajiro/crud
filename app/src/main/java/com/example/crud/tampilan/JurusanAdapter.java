package com.example.crud.tampilan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.data.Jurusan;

import java.util.List;

public class JurusanAdapter extends RecyclerView.Adapter<JurusanAdapter.ViewHolder> {
    private List<Jurusan> jurusanList;
    private OnJurusanClickListener listener;

    public interface OnJurusanClickListener {
        void onJurusanClick(Jurusan jurusan);
    }

    public JurusanAdapter(List<Jurusan> jurusanList, OnJurusanClickListener listener) {
        this.jurusanList = jurusanList;
        this.listener = listener;
    }

    public void setJurusanList(List<Jurusan> jurusanList) {
        this.jurusanList = jurusanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jurusan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jurusan jurusan = jurusanList.get(position);
        holder.txtNamaJurusan.setText(jurusan.namaJurusan);
        holder.txtJumlahMahasiswa.setText("Jumlah: " + jurusan.jumlahMahasiswa);
        holder.txtAkreditasi.setText("Akreditasi: " + jurusan.akreditasi);
        holder.itemView.setOnClickListener(view -> listener.onJurusanClick(jurusan));
    }

    @Override
    public int getItemCount() {
        return jurusanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaJurusan;
        TextView txtJumlahMahasiswa;
        TextView txtAkreditasi;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNamaJurusan = itemView.findViewById(R.id.txtNamaJurusan);
            txtAkreditasi = itemView.findViewById(R.id.txtAkreditasi); // Sesuaikan dengan XML
            txtJumlahMahasiswa = itemView.findViewById(R.id.txtJumlahMahasiswa);

        }
    }
}

