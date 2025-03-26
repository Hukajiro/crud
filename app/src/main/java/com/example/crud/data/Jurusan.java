package com.example.crud.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "jurusan",
        foreignKeys = @ForeignKey(
                entity = Fakultas.class,
                parentColumns = "id",
                childColumns = "fakultasId",
                onDelete = ForeignKey.CASCADE // Jurusan akan terhapus jika fakultas terkait dihapus
        ),
        indices = @Index("fakultasId") // Index untuk mempercepat query
)
public class Jurusan {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String namaJurusan;
    public int jumlahMahasiswa;
    public String akreditasi;

    @ColumnInfo(name = "fakultasId")
    public int fakultasId;

    public Jurusan(String namaJurusan, int jumlahMahasiswa, String akreditasi, int fakultasId) {
        this.namaJurusan = namaJurusan;
        this.jumlahMahasiswa = jumlahMahasiswa;
        this.akreditasi = akreditasi;
        this.fakultasId = fakultasId;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public String getNama() { return namaJurusan; }
    public int getJumlahMahasiswa() { return jumlahMahasiswa; }
    public String getAkreditasi() { return akreditasi; }
    public int getFakultas() { return fakultasId; }
    public void kurangiJumlah() {
        if (jumlahMahasiswa > 0) {
            jumlahMahasiswa--;
        }
    }
}
