package com.astrallinear.astrallinear.Toko;

import java.util.HashMap;

import com.astrallinear.astrallinear.Kartu.KartuProduk;



public class Toko {
    private static Toko toko = null;
    private static HashMap<String, Integer> isiToko;

    public static Toko getToko(){
        if(toko == null)
            toko = new Toko();
        return toko;
    }

    public void resetToko (){
        isiToko = new HashMap<String, Integer>();
    }

    private Toko(){
        isiToko = new HashMap<String, Integer>();
    }

    public HashMap<String, Integer> getIsiToko(){
        return new HashMap<String, Integer>(isiToko);
    }

    public KartuProduk buyProductByName(String productName) throws Exception{
        if(!isiToko.containsKey(productName)){
            throw new noItemOnStoreException();
        }
        else{
            isiToko.put(productName, isiToko.get(productName) - 1);
            if(isiToko.get(productName) == 0)
                isiToko.remove(productName);
            return new KartuProduk(productName);
        }
    }

    // return money from selling
    public Integer sellProduct(KartuProduk productCard) throws Exception{
        String productName = productCard.getNama();
        if(!isiToko.containsKey(productName)){
            isiToko.put(productName, 1);
        }
        else{
            isiToko.put(productName, isiToko.get(productName) + 1);
        }
        return productCard.getHarga();
    }
}

class noItemOnStoreException extends Exception{
    public noItemOnStoreException(){
        super("Tidak ada item dengan nama ini di toko");
    }
}
