package com.astrallinear.astrallinear.Toko;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.astrallinear.astrallinear.Kartu.KartuProduk;

public class TokoTest {

    @Test //test1
    public void testSingleton() throws Exception{
        Toko toko = Toko.getToko();
        Toko toko2 = Toko.getToko();
        toko.sellProduct(new KartuProduk("susu"));
        assertTrue(toko == toko2);
    }

    @Test //test2
    public void testSellProduct()throws Exception{
        Toko toko = Toko.getToko();
        assertTrue(toko.sellProduct(new KartuProduk("susu")) == 100);
    }
    
    @Test //test3
    public void testBuyProductByName()throws Exception{
        Toko toko = Toko.getToko();
        toko.sellProduct(new KartuProduk("susu"));
            KartuProduk buyResult = toko.buyProductByName("susu");
            assertTrue(buyResult.getNama() == "susu");
            assertTrue(buyResult.getBerat() == 4); 
            assertTrue(buyResult.getHarga() == 100);
    }
}
