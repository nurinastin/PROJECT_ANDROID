package com.project.sinisa.sewa.model;

public class Barang_Model {
//    deklarasi string variabel untuk menampung data
    String foto, kode, nama, harga;
//    mengambil data dari variabel foto
    public String getFoto() {
        return foto;
    }
//    menampung data ke variabel foto
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
