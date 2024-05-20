package com.astrallinear.astrallinear.Kartu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KartuTest {
    
    @Test // test 1
    public void TestKonstruktor() {
        
        KartuHewan my_sapi = new KartuHewan("sapi", 10, "herbivora", new KartuProduk("susu", 100, 4));
        
        assertTrue(my_sapi.getNama() == "sapi");
        assertTrue(my_sapi.getJenis() == "herbivora");
        assertTrue(my_sapi.getBerat() == 0);
        
    }
    @Test // test 2
    public void TestPanenMakhluk() throws Exception {

        KartuHewan my_sapi = new KartuHewan("sapi", 10, "herbivora", new KartuProduk("susu", 100, 4));

        // belum siap panen
        assertTrue(my_sapi.isSiapPanen() == false);
        
        KartuProduk my_labu = new KartuProduk("labu", 500, 100);
        my_sapi.beriMakan(my_labu);
        
        // harusnya sudah siap panen
        assertTrue(my_sapi.isSiapPanen() == true);
        
        // panen
        KartuProduk my_produk = my_sapi.panen();
        assertTrue(my_produk.getNama() == "susu");
        assertTrue(my_produk.getBerat() == 4);
        assertTrue(my_produk.getHarga() == 100);
        
    }
    @Test // test 3
    public void TestBeriMakanHewan() {
        
        KartuHewan my_sapi = new KartuHewan("sapi", 10, "herbivora", new KartuProduk("susu", 100, 4));

        // test kasih makan herbivora
        try {
            my_sapi.beriMakan(new KartuProduk("telur", 50, 2));
            assertTrue(false); // harusnya kalau dia bener, dia nge throw exception sebelum sampai sini.
        } catch (SalahJenisMakananException e) {
        }
        
        
        // test kasih makan karnivora
        KartuHewan my_hiu = new KartuHewan("hiu_darat", 20, "karnivora", new KartuProduk("sirip_hiu", 500, 12));

        try {
            my_hiu.beriMakan(new KartuProduk("telur", 50, 2));
        } catch (SalahJenisMakananException e) {
            assertTrue(false); // karena harusnya bener dikasih makan telur, dia karnivora dan telur produk hewani
        }

        // test kasih makan omnivora
        KartuHewan my_beruang = new KartuHewan("beruang", 25, "omnivora", new KartuProduk("daging_beruang", 500, 12));

        try {
            my_beruang.beriMakan(new KartuProduk("gado-gado", 999, 999));
            assertTrue(false);
        } catch (SalahJenisMakananException e) {
            assertTrue(e.getMessage() == "Makanan tidak ditemukan.");
        }

        // yay selesai!!!

    }
}
