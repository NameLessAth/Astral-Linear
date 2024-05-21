package com.astrallinear.astrallinear.Kartu;

import java.util.Map;

public class KartuProduk extends Kartu {
    
    private Integer harga;
    private Integer berat;

    private static Map<String, Integer> produk_harga_map = Map.ofEntries(
        Map.entry("sirip_hiu", 500),
        Map.entry("susu", 100),
        Map.entry("daging_domba", 120),
        Map.entry("daging_kuda", 150),
        Map.entry("telur", 50),
        Map.entry("daging_beruang", 500),
        Map.entry("jagung", 150),
        Map.entry("labu", 500),
        Map.entry("stroberi", 350)
    );

    private static Map<String, Integer> produk_berat_map = Map.ofEntries(
        Map.entry("sirip_hiu", 12),
        Map.entry("susu", 4),
        Map.entry("daging_domba", 6),
        Map.entry("daging_kuda", 8),
        Map.entry("telur", 2),
        Map.entry("daging_beruang", 12),
        Map.entry("jagung", 3),
        Map.entry("labu", 10),
        Map.entry("stroberi", 5)
    );

    
    public KartuProduk(String nama) throws NamaKartuTidakAdaException {
        super(nama);
        if (nama == null) return ; // this will be handled by KartuTanaman/Hewan
        if (produk_berat_map.get(nama) == null) throw new NamaKartuTidakAdaException("Error konstruktor kartu produk dengan nama " + nama);

        harga = produk_harga_map.get(nama);
        berat = produk_berat_map.get(nama);
    }

    public Integer getBerat() {
        return berat;
    }
    public Integer getHarga() {
        return harga;
    }
}