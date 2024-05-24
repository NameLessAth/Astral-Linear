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

    private static final Map<String, String> hewan_jenis_map = Map.ofEntries(
        Map.entry("hiu_darat", "karnivora"),
        Map.entry("sapi", "herbivora"),
        Map.entry("domba", "herbivora"),
        Map.entry("kuda", "herbivora"),
        Map.entry("ayam", "omnivora"),
        Map.entry("beruang", "omnivora")
    );
    private static final Map<String, Integer> hewan_beratPanen_map = Map.ofEntries(
        Map.entry("hiu_darat", 20),
        Map.entry("sapi", 10),
        Map.entry("domba", 12),
        Map.entry("kuda", 14),
        Map.entry("ayam", 5),
        Map.entry("beruang", 25)
    );
    private static final Map<String, String> hewan_produk_map = Map.ofEntries(
        Map.entry("hiu_darat", "sirip_hiu"),
        Map.entry("sapi", "susu"),
        Map.entry("domba", "daging_domba"),
        Map.entry("kuda", "daging_kuda"),
        Map.entry("ayam", "telur"),
        Map.entry("beruang", "daging_beruang")
    );

    public KartuHewan(String nama) throws NamaKartuTidakAdaException {
        super(nama, new KartuProduk(hewan_produk_map.get(nama)));
        if (hewan_produk_map.get(nama) == null) throw new NamaKartuTidakAdaException("Error konstruktor kartu hewan dengan nama " + nama);
        this.berat = 0;
        this.jenis = hewan_jenis_map.get(nama);
        this.beratPanen = hewan_beratPanen_map.get(nama);
    }

    public boolean isSiapPanen() {
        return berat >= beratPanen;
    }

    public void beriMakan(KartuProduk produk) throws SalahJenisMakananException {
        
        boolean herbivora = this.getJenis().equals("herbivora");
        boolean karnivora = this.getJenis().equals("karnivora");

        boolean produkHerbivora = makananHerbivora.contains(produk.getNama());
        boolean produkKarnivora = makananKarnivora.contains(produk.getNama());
        
        if ((herbivora && !produkHerbivora) || (karnivora && !produkKarnivora)) {
            throw new SalahJenisMakananException("Hey, ini bukan makanannya!");
        } else if (!produkHerbivora && !produkKarnivora) {
            throw new SalahJenisMakananException("Makanan tidak ditemukan.");
        } // harusnya ini gak mungkin terjadi

        berat += produk.getBerat();

    }

    public void accelerate() {
        this.berat += 8;
    }

    public void delay() {
        this.berat = (this.berat - 5 > 0 ? this.berat - 5 : 0);
    }

    public String getJenis() {
        return jenis;
    }

    public Integer getBerat() {
        return berat;
    }
     
    public void setBerat(Integer bobot){
        this.berat = bobot;
    }

    public String getPathToImg() {
        return "Hewan/" + this.getNama() + ".png";
    }

    public String getInfo() {
        return (
            "\n\tBerat: "                   + berat +
            "\n\tBerat threshold panen: "   + beratPanen +
            "\n\tJenis: "                   + getJenis() +
            "\n\tSiap panen: "              + (isSiapPanen() ? "ya!" : "tidak!") +
            "\n\tHasil panen: "             + drop.getNama()
        );
    }
}

class SalahJenisMakananException extends Exception {
    public SalahJenisMakananException(String s) {
        super(s);
    }
}