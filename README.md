TP5DPBO2425C2
janji:
Saya Nisrina Safinatunnajah dengan NIM 2410093 mengerjakan Tugas Praktikum 5 dalam mata kuliah DPBO untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

Desain program, penjelasan alur, dan dokumentasi:

Dalam program ini terdapat tiga kelas utama, yaitu Product, ProductMenu, dan Database. Kelas Product berfungsi sebagai kelas model yang menyimpan data setiap produk. Atribut yang dimiliki antara lain id, nama, harga, kategori, dan satu atribut tambahan yaitu rating. Atribut rating menggunakan komponen JSlider pada form untuk memilih nilai antara 1 sampai 5. Kelas ini memiliki konstruktor, serta metode getter dan setter untuk setiap atribut yang digunakan dalam proses manipulasi data.

Kelas ProductMenu merupakan kelas utama yang mengatur tampilan antarmuka dan seluruh logika program. Program ini dibangun menggunakan Java Swing, dengan komponen utama berupa JTextField untuk input id, nama, dan harga, JComboBox untuk memilih kategori, JSlider untuk memberikan nilai rating, JLabel untuk menampilkan nilai rating yang sedang dipilih, JTable untuk menampilkan daftar produk, serta tombol Add/Update, Delete, dan Cancel untuk melakukan operasi CRUD.

Berbeda dengan versi sebelumnya yang masih menggunakan ArrayList sebagai tempat penyimpanan data, pada tugas kali ini seluruh proses Create, Read, Update, dan Delete sudah terhubung langsung dengan database MySQL melalui kelas Database. Kelas Database berfungsi untuk mengatur koneksi antara program dan database MySQL. Koneksi ini dilakukan menggunakan JDBC dengan alamat localhost dan nama database product_db.

Alur program dimulai ketika program dijalankan dan koneksi ke database berhasil dibuat. Data dari tabel product dalam database langsung ditampilkan di tabel pada form. Pengguna dapat menambahkan data produk baru dengan mengisi seluruh kolom pada form, yaitu id, nama, harga, kategori, dan rating, lalu menekan tombol Add. Sebelum data disimpan, program akan melakukan validasi untuk memastikan tidak ada kolom yang kosong dan id produk belum pernah digunakan. Jika semua validasi terpenuhi, data akan disimpan ke dalam database dan tabel langsung diperbarui.

Untuk mengubah data, pengguna cukup memilih salah satu baris pada tabel. Setelah baris diklik, seluruh nilai dari produk tersebut otomatis muncul di form, dan tombol Add berubah menjadi Update. Pengguna dapat mengganti nilai-nilai yang diinginkan lalu menekan tombol Update untuk menyimpan perubahan ke database. Proses Update ini juga dilengkapi dengan validasi untuk memastikan semua kolom sudah terisi dengan benar.

Fitur Delete digunakan untuk menghapus data produk yang dipilih dari database. Sebelum proses penghapusan dilakukan, program akan menampilkan jendela konfirmasi menggunakan JOptionPane agar pengguna tidak salah menghapus data. Jika pengguna menekan tombol “Yes”, maka data akan dihapus dari database dan tabel di layar langsung diperbarui. Selain itu, terdapat tombol Cancel yang berfungsi untuk mengosongkan seluruh form sehingga pengguna dapat menambahkan data baru tanpa harus menutup program terlebih dahulu.

Dokumentasi:

