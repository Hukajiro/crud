package com.example.crud.tampilan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.data.Fakultas;

import java.util.List;

public class FakultasAdapter extends RecyclerView.Adapter<FakultasAdapter.ViewHolder> {
    private List<Fakultas> fakultasList;
    private OnFakultasClickListener listener;
    private OnFakultasDeleteListener deleteListener;
    private Context context;

    public interface OnFakultasClickListener {
        void onFakultasClick(Fakultas fakultas);
    }

    public interface OnFakultasDeleteListener {
        void onFakultasDelete(Fakultas fakultas);
    }

    public FakultasAdapter(List<Fakultas> fakultasList, OnFakultasClickListener listener, OnFakultasDeleteListener deleteListener) {
        this.fakultasList = fakultasList;
        this.listener = listener;
        this.deleteListener = deleteListener;
    }

    public void setFakultasList(List<Fakultas> fakultasList) {
        this.fakultasList = fakultasList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_fakultas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fakultas fakultas = fakultasList.get(position);
        holder.txtNamaFakultas.setText(fakultas.namaFakultas);

        holder.itemView.setOnClickListener(view -> listener.onFakultasClick(fakultas));

        holder.imgDelete.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus fakultas ini (termasuk jurusan di dalamnya)?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        deleteListener.onFakultasDelete(fakultas);
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return fakultasList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaFakultas;
        ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNamaFakultas = itemView.findViewById(R.id.txtNamaFakultas);
            imgDelete = itemView.findViewById(R.id.btnDeleteFakultas);
        }
    }
}