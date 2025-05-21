/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan;

/**
 *
 * @author Windows
 */
import java.sql.*;
import java.util.*;

public class BukuDAO {
    private Connection conn;

    public BukuDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/perpustakaan", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tambahBuku(Buku b) {
        String sql = "INSERT INTO buku (judul, genre, penulis, penerbit, lokasi, stok) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getJudul());
            ps.setString(2, b.getGenre());
            ps.setString(3, b.getPenulis());
            ps.setString(4, b.getPenerbit());
            ps.setString(5, b.getLokasi());
            ps.setInt(6, b.getStok());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Buku> getAllBuku() {
        List<Buku> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM buku")) {
            while (rs.next()) {
                list.add(new Buku(
                    rs.getInt("id"), rs.getString("judul"), rs.getString("genre"),
                    rs.getString("penulis"), rs.getString("penerbit"), rs.getString("lokasi"), rs.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateBuku(Buku b) {
        String sql = "UPDATE buku SET judul=?, genre=?, penulis=?, penerbit=?, lokasi=?, stok=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getJudul());
            ps.setString(2, b.getGenre());
            ps.setString(3, b.getPenulis());
            ps.setString(4, b.getPenerbit());
            ps.setString(5, b.getLokasi());
            ps.setInt(6, b.getStok());
            ps.setInt(7, b.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapusBuku(int id) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM buku WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Buku> searchBuku(String kolom, String keyword) {
        List<Buku> list = new ArrayList<>();
        String sql = "SELECT * FROM buku WHERE " + kolom + " LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Buku(
                    rs.getInt("id"), rs.getString("judul"), rs.getString("genre"),
                    rs.getString("penulis"), rs.getString("penerbit"), rs.getString("lokasi"), rs.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}


