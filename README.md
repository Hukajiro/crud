# CRUD Android App

## Deskripsi
Aplikasi CRUD (Create, Read, Update, Delete) ini dikembangkan menggunakan **Java** di **Android Studio**. Aplikasi ini memungkinkan pengguna untuk menambahkan, melihat, mengedit, dan menghapus data dari database lokal.

## Fitur Utama
- **Menambahkan Data**: Pengguna dapat menambahkan data baru ke dalam database.
- **Menampilkan Data**: Menampilkan daftar data yang telah disimpan.
- **Mengedit Data**: Memungkinkan pengguna untuk mengedit data yang sudah ada.
- **Menghapus Data**: Menghapus data dari database.
- **Penyimpanan Lokal**: Data disimpan secara lokal menggunakan SQLite atau Room.

## Teknologi yang Digunakan
- **Bahasa Pemrograman**: Java
- **IDE**: Android Studio
- **Database**: SQLite / Room
- **UI Framework**: XML dengan Material Design

## Cara Instalasi dan Menjalankan Aplikasi
1. Clone repositori ini:
   ```bash
   git clone https://github.com/username/repository-name.git
   ```
2. Buka project di **Android Studio**.
3. Pastikan dependencies telah diperbarui dengan melakukan **Sync Gradle**.
4. Jalankan aplikasi menggunakan emulator atau perangkat fisik.

## Struktur Folder
```
/app
├── src
│   ├── main
│   │   ├── java/com/example/crud
│   │   │   ├── activity  # Berisi Activity untuk CRUD
│   │   │   ├── database  # Berisi konfigurasi database
│   │   │   ├── model     # Berisi model data
│   │   │   ├── adapter   # Berisi adapter untuk RecyclerView
│   │   ├── res/layout    # Berisi file XML tampilan
│   ├── AndroidManifest.xml
```

## Dependensi yang Digunakan
Tambahkan dependensi berikut ke dalam `build.gradle (Module: app)` jika menggunakan Room:
```gradle
dependencies {
    implementation 'androidx.room:room-runtime:2.5.0'
    annotationProcessor 'androidx.room:room-compiler:2.5.0'
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'com.google.android.material:material:1.6.0'
}
```

## Cara Menggunakan
1. **Menambahkan Data**: Klik tombol tambah dan isi form yang tersedia.
2. **Melihat Data**: Data akan ditampilkan dalam RecyclerView.
3. **Mengedit Data**: Klik item data, lalu ubah informasi yang diperlukan.
4. **Menghapus Data**: Klik tombol hapus pada item yang ingin dihapus.

## Kontributor
- Nama Anda (@username)

## Lisensi
Aplikasi ini menggunakan lisensi **MIT**. Anda bebas menggunakan dan memodifikasi sesuai kebutuhan.

