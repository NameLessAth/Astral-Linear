package com.astrallinear.astrallinear.Kartu;

import java.util.*;

public class KartuHewan extends KartuMakhluk {

    private Integer berat;
    private Integer beratPanen;
    private String jenis;

    public static final Set<String> makananHerbivora = new HashSet<>(Arrays.asList(
        "jagung",
        "labu",
        "stroberi"
    ));
    public static final Set<String> makananKarnivora = new HashSet<>(Arrays.asList(
        "sirip_hiu",
        "susu",
        "telur",
        "daging_kuda",
        "daging_domba",
        "daging_beruang"
    ));


    public KartuHewan(String nama, Integer beratPanen, String jenis, KartuProduk drop) {
        super(nama, drop);
        this.berat = 0;
        this.jenis = jenis;
        this.beratPanen = beratPanen;
    }

    public boolean isSiapPanen() {
        return berat > beratPanen;
    }

    public void beriMakan(KartuProduk produk) throws SalahJenisMakananException {
        
        boolean herbivora = this.getJenis() == "herbivora";
        boolean karnivora = this.getJenis() == "karnivora";

        boolean produkHerbivora = makananHerbivora.contains(produk.getNama());
        boolean produkKarnivora = makananKarnivora.contains(produk.getNama());
        
        if ((herbivora && !produkHerbivora) || (karnivora && !produkKarnivora)) {
            throw new SalahJenisMakananException("Hey, ini bukan makanannya!");
        } else if (!produkHerbivora && !produkKarnivora) {
            throw new SalahJenisMakananException("Makanan tidak ditemukan.");
        }

        berat += produk.getBerat();

    }

    public String getJenis() {
        return jenis;
    }

    public Integer getBerat() {
        return berat;
    }
}

class SalahJenisMakananException extends Exception {
    public SalahJenisMakananException(String s) {
        super(s);
    }
}