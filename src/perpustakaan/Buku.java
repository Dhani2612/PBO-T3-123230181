/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan;

/**
 *
 * @author Windows
 */
public class Buku {
    private int id;
    private String judul, genre, penulis, penerbit, lokasi;
    private int stok;

    public Buku(int id, String judul, String genre, String penulis, String penerbit, String lokasi, int stok) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.lokasi = lokasi;
        this.stok = stok;
    }

    public int getId() { return id; }
    public String getJudul() { return judul; }
    public String getGenre() { return genre; }
    public String getPenulis() { return penulis; }
    public String getPenerbit() { return penerbit; }
    public String getLokasi() { return lokasi; }
    public int getStok() { return stok; }

    public void setId(int id) { this.id = id; }
    public void setJudul(String judul) { this.judul = judul; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPenulis(String penulis) { this.penulis = penulis; }
    public void setPenerbit(String penerbit) { this.penerbit = penerbit; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }
    public void setStok(int stok) { this.stok = stok; }
}
