/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan;

/**
 *
 * @author Windows
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormPerpustakaan extends JFrame {
    private JTextField tfId, tfJudul, tfGenre, tfPenulis, tfPenerbit, tfLokasi, tfStok, tfCari;
    private JTable table;
    private DefaultTableModel tableModel;
    private BukuDAO dao = new BukuDAO();

    public FormPerpustakaan() {
        setTitle("Perpustakaan Digital");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        JPanel panelKiri = new JPanel(new GridLayout(10, 1, 5, 5));
        tfId = new JTextField(); tfId.setEditable(false);
        tfJudul = new JTextField(); tfGenre = new JTextField();
        tfPenulis = new JTextField(); tfPenerbit = new JTextField();
        tfLokasi = new JTextField(); tfStok = new JTextField();

        panelKiri.add(new JLabel("ID Buku")); panelKiri.add(tfId);
        panelKiri.add(new JLabel("Judul")); panelKiri.add(tfJudul);
        panelKiri.add(new JLabel("Genre")); panelKiri.add(tfGenre);
        panelKiri.add(new JLabel("Penulis")); panelKiri.add(tfPenulis);
        panelKiri.add(new JLabel("Penerbit")); panelKiri.add(tfPenerbit);
        panelKiri.add(new JLabel("Lokasi")); panelKiri.add(tfLokasi);
        panelKiri.add(new JLabel("Stok")); panelKiri.add(tfStok);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUbah = new JButton("Ubah");
        JButton btnHapus = new JButton("Hapus");
        JButton btnTampil = new JButton("Tampilkan Semua");

        panelKiri.add(btnTambah); panelKiri.add(btnUbah);
        panelKiri.add(btnHapus); panelKiri.add(btnTampil);

        add(panelKiri);
        
        JPanel panelKanan = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel();
        JComboBox<String> cbKategori = new JComboBox<>(new String[]{"judul", "genre", "penulis", "penerbit"});
        tfCari = new JTextField(15);
        JButton btnCari = new JButton("Cari");
        searchPanel.add(new JLabel("Cari berdasarkan:"));
        searchPanel.add(cbKategori); searchPanel.add(tfCari); searchPanel.add(btnCari);

        tableModel = new DefaultTableModel(new String[]{"ID", "Judul", "Genre", "Penulis", "Penerbit", "Lokasi", "Stok"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        panelKanan.add(searchPanel, BorderLayout.NORTH);
        panelKanan.add(scrollPane, BorderLayout.CENTER);

        add(panelKanan);

        // === ACTION LISTENER ===
        btnTambah.addActionListener(e -> {
            try {
                Buku b = new Buku(0, tfJudul.getText(), tfGenre.getText(), tfPenulis.getText(), tfPenerbit.getText(), tfLokasi.getText(), Integer.parseInt(tfStok.getText()));
                dao.tambahBuku(b);
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
                loadTabel(); clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input tidak valid!");
            }
        });

        btnUbah.addActionListener(e -> {
            try {
                Buku b = new Buku(Integer.parseInt(tfId.getText()), tfJudul.getText(), tfGenre.getText(), tfPenulis.getText(), tfPenerbit.getText(), tfLokasi.getText(), Integer.parseInt(tfStok.getText()));
                dao.updateBuku(b);
                JOptionPane.showMessageDialog(this, "Data berhasil diubah");
                loadTabel(); clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Gagal mengubah data!");
            }
        });

        btnHapus.addActionListener(e -> {
            try {
                dao.hapusBuku(Integer.parseInt(tfId.getText()));
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                loadTabel(); clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data!");
            }
        });

        btnTampil.addActionListener(e -> loadTabel());

        btnCari.addActionListener(e -> {
            try {
                List<Buku> hasil = dao.searchBuku(cbKategori.getSelectedItem().toString(), tfCari.getText());
                tableModel.setRowCount(0);
                for (Buku b : hasil) {
                    tableModel.addRow(new Object[]{b.getId(), b.getJudul(), b.getGenre(), b.getPenulis(), b.getPenerbit(), b.getLokasi(), b.getStok()});
                }
                if (hasil.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tidak ditemukan");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saat mencari");
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                tfId.setText(table.getValueAt(row, 0).toString());
                tfJudul.setText(table.getValueAt(row, 1).toString());
                tfGenre.setText(table.getValueAt(row, 2).toString());
                tfPenulis.setText(table.getValueAt(row, 3).toString());
                tfPenerbit.setText(table.getValueAt(row, 4).toString());
                tfLokasi.setText(table.getValueAt(row, 5).toString());
                tfStok.setText(table.getValueAt(row, 6).toString());
            }
        });

        loadTabel();
        setVisible(true);
    }

    private void loadTabel() {
        tableModel.setRowCount(0);
        for (Buku b : dao.getAllBuku()) {
            tableModel.addRow(new Object[]{b.getId(), b.getJudul(), b.getGenre(), b.getPenulis(), b.getPenerbit(), b.getLokasi(), b.getStok()});
        }
    }

    private void clearForm() {
        tfId.setText(""); tfJudul.setText(""); tfGenre.setText(""); tfPenulis.setText("");
        tfPenerbit.setText(""); tfLokasi.setText(""); tfStok.setText("");
    }
}



