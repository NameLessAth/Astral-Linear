package com.astrallinear.astrallinear.Kartu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KartuTest {
    
    @Test // test 1
    public void TestKonstruktor() throws Exception {
        
        KartuHewan my_sapi = new KartuHewan("sapi");
        
        assertTrue(my_sapi.getNama() == "sapi");
        assertTrue(my_sapi.getJenis() == "herbivora");
        assertTrue(my_sapi.getBerat() == 0);
        
    }
    @Test // test 2
    public void TestPanenMakhluk() throws Exception {

        KartuHewan my_sapi = new KartuHewan("sapi");

        // belum siap panen
        assertTrue(my_sapi.isSiapPanen() == false);
        
        KartuProduk my_labu = new KartuProduk("labu");
        my_sapi.beriMakan(my_labu);
        
        // harusnya sudah siap panen
        System.out.println(my_sapi.getBerat());
        assertTrue(my_sapi.isSiapPanen() == true);
        
        // panen
        KartuProduk my_produk = my_sapi.panen();
        assertTrue(my_produk.getNama() == "susu");
        assertTrue(my_produk.getBerat() == 4);
        assertTrue(my_produk.getHarga() == 100);
        
    }
    @Test // test 3
    public void TestBeriMakanHewan() throws Exception {
        
        KartuHewan my_sapi = new KartuHewan("sapi");

        // test kasih makan herbivora
        try {
            my_sapi.beriMakan(new KartuProduk("telur"));
            assertTrue(false); // harusnya kalau dia bener, dia nge throw exception sebelum sampai sini.
        } catch (SalahJenisMakananException e) {
        }
        
        
        // test kasih makan karnivora
        KartuHewan my_hiu = new KartuHewan("hiu_darat");

        try {
            my_hiu.beriMakan(new KartuProduk("telur"));
        } catch (SalahJenisMakananException e) {
            assertTrue(false); // karena harusnya bener dikasih makan telur, dia karnivora dan telur produk hewani
        }

        // test kasih makan omnivora
        KartuHewan my_beruang = new KartuHewan("beruang");

        try {
            my_beruang.beriMakan(new KartuProduk("gado-gado"));
            assertTrue(false);
        } catch (SalahJenisMakananException e) {
            assertTrue(e.getMessage() == "Makanan tidak ditemukan.");
        } catch (NamaKartuTidakAdaException e) {
            assertTrue(true);
        }

        
    }

    @Test // Test 4
    public void TestExceptions() {
        
        try {
            KartuProduk produk = new KartuProduk("sirip_hui");
            assertTrue(false);
        } catch (NamaKartuTidakAdaException e) {
            assertTrue(e.getMessage().equals("Error konstruktor kartu produk dengan nama sirip_hui"));
        }

        try {
            KartuHewan hewan = new KartuHewan("belibis");
            assertTrue(false);
        } catch (NamaKartuTidakAdaException e) {
            assertTrue(e.getMessage().equals("Error konstruktor kartu hewan dengan nama belibis"));
        }
        
        try {
            KartuTanaman tanaman = new KartuTanaman("peashooter");
            assertTrue(false);
        } catch (NamaKartuTidakAdaException e) {
            assertTrue(e.getMessage().equals("Error konstruktor kartu tanaman dengan nama peashooter"));
        }


        try 
        {
            KartuProduk produk; 
            produk = new KartuProduk("sirip_hiu");
            produk = new KartuProduk("susu");
            produk = new KartuProduk("daging_domba");
            produk = new KartuProduk("daging_kuda");
            produk = new KartuProduk("telur");
            produk = new KartuProduk("daging_beruang");
            produk = new KartuProduk("jagung");
            produk = new KartuProduk("labu");
            produk = new KartuProduk("stroberi");
            
            KartuHewan hewan;
            hewan = new KartuHewan("hiu_darat");
            hewan = new KartuHewan("sapi");
            hewan = new KartuHewan("domba");
            hewan = new KartuHewan("kuda");
            hewan = new KartuHewan("ayam");
            hewan = new KartuHewan("beruang");

            KartuTanaman tanaman;
            tanaman = new KartuTanaman("biji_jagung");
            tanaman = new KartuTanaman("biji_stroberi");
            tanaman = new KartuTanaman("biji_labu");

        } catch (Exception e) { assertTrue(false) ; }

    }
    // yay selesai!!!
}
