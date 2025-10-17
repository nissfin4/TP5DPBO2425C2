import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;



public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        // buat object window
        ProductMenu menu = new ProductMenu();
        // atur ukuran window
        menu.setSize(700, 600);
        // letakkan window di tengah layar
        menu.setLocationRelativeTo(null);
        // isi window
        menu.setContentPane(menu.mainPanel);
        // ubah warna background
        menu.mainPanel.setBackground(Color.WHITE);
        // tampilkan window
        menu.setVisible(true);
        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua produk

    private Database database;


    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;
    private JSlider ratingSlider;
    private JLabel ratingValueLabel;

    // constructor
    public ProductMenu() {



        // buat objek database
        database = new Database();
// isi tabel produk dari database
        productTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        // atur isi combo box kategori
        String[] kategoriData = { "???", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        ratingSlider.addChangeListener(e -> {
            ratingValueLabel.setText(String.valueOf(ratingSlider.getValue()));
        });

        // sembunyikan button delete
        deleteButton.setVisible(false);





        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex == -1){
                    insertData();
                } else{
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION
                );

                if(confirm == JOptionPane.YES_OPTION){
                    deleteData();//panggil fungsi hapus
                }
                // TODO: tambahkan konfirmasi sebelum menghapus data
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = productTable.getSelectedRow();
                // simpan value textfield dan combo box
                String curId = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curHarga = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = productTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curRating = productTable.getModel().getValueAt(selectedIndex, 5).toString();

                // ubah isi textfield dan combo box
                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                kategoriComboBox.setSelectedItem(curKategori);
                ratingSlider.setValue(Integer.parseInt(curRating));


                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }
    // Fungsi untuk menampilkan semua data dari database ke tabel GUI
    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = { "No", "ID Produk", "Nama", "Harga", "Kategori", "Rating"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);
        try{
            ResultSet resultSet = database.selectQuery("SELECT * FROM product");
            int i = 0;
            while (resultSet.next()){
                Object[] row = new Object[6];
                row[0] = i+1;
                row[1] = resultSet.getString("id");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("harga");
                row[4] = resultSet.getString("kategori");
                row[5] = resultSet.getString("rating");
                tmp.addRow(row);
                i++;

            }
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error menampilkan data: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }

        return tmp; // return juga harus diganti
    }

    // Fungsi untuk menambahkan data baru ke database
    public void insertData() {
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        String hargaText = hargaField.getText().trim();
        String kategori = kategoriComboBox.getSelectedItem().toString();
        int rating = ratingSlider.getValue();

        // validasi kosong
        if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty() || kategori.equals("???")) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double harga;
        try {
            harga = Double.parseDouble(hargaText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // cek id duplikat
            String cekSql = "SELECT id FROM product WHERE id = ?";
            PreparedStatement cekStmt = database.getConnection().prepareStatement(cekSql);
            cekStmt.setString(1, id);
            ResultSet rs = cekStmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "ID sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                cekStmt.close();
                return;
            }
            cekStmt.close();

            // insert
            String insertSql = "INSERT INTO product (id,nama,harga,kategori,rating) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = database.getConnection().prepareStatement(insertSql);
            insertStmt.setString(1, id);
            insertStmt.setString(2, nama);
            insertStmt.setDouble(3, harga);
            insertStmt.setString(4, kategori);
            insertStmt.setInt(5, rating);
            insertStmt.executeUpdate();
            insertStmt.close();

            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            productTable.setModel(setTable());
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi untuk mengupdate data yang sudah ada
    public void updateData() {
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        String hargaText = hargaField.getText().trim();
        String kategori = kategoriComboBox.getSelectedItem().toString();
        int rating = ratingSlider.getValue();

        if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty() || kategori.equals("???")) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double harga;
        try {
            harga = Double.parseDouble(hargaText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String sql = "UPDATE product SET nama=?, harga=?, kategori=?, rating=? WHERE id=?";
            PreparedStatement stmt = database.getConnection().prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setDouble(2, harga);
            stmt.setString(3, kategori);
            stmt.setInt(4, rating);
            stmt.setString(5, id);
            int updated = stmt.executeUpdate();
            stmt.close();

            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
                productTable.setModel(setTable());
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "ID tidak ditemukan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal update: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi untuk menghapus data berdasarkan ID
    public void deleteData() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            String sql = "DELETE FROM product WHERE id=?";
            PreparedStatement stmt = database.getConnection().prepareStatement(sql);
            stmt.setString(1, id);
            int deleted = stmt.executeUpdate();
            stmt.close();

            if (deleted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                productTable.setModel(setTable());
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "ID tidak ditemukan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void clearForm() {
        // kosongkan semua texfield dan combo box
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
        ratingSlider.setValue(0);
        ratingValueLabel.setText("0");

    }



}