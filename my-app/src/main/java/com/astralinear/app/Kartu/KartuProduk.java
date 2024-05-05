package com.astralinear.app.Kartu;

public class KartuProduk extends Kartu {
    
    private Integer harga;
    private Integer berat;
    
    public KartuProduk(String nama, Integer harga, Integer berat) {
        super(nama);
        this.harga = harga;
        this.berat = berat;
    }

    public Integer getBerat() {
        return berat;
    }
    public Integer getHarga() {
        return harga;
    }
}
