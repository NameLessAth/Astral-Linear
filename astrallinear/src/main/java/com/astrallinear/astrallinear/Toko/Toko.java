package com.astrallinear.astrallinear.Toko;

import java.util.HashMap;

public class Toko {
    HashMap<String, Integer> isiToko;

    public Toko(){
        isiToko = new HashMap<String, Integer>();
    }

    public HashMap<String, Integer> getIsiToko(){
        return new HashMap<String, Integer>(isiToko);
    }

    public void buyProductByName(String productName) throws Exception{
        if(!isiToko.containsKey(productName)){
            throw new noItemOnStoreException();
        }
        else{
            isiToko.put(productName, isiToko.get(productName) - 1);
            if(isiToko.get(productName) == 0)
                isiToko.remove(productName);
        }
    }

    public void sellProductByName(String productName) throws Exception{
        if(!isiToko.containsKey(productName)){
            isiToko.put(productName, 1);
        }
        else{
            isiToko.put(productName, isiToko.get(productName) + 1);
        }
    }
}

class noItemOnStoreException extends Exception{
    public noItemOnStoreException(){
        super("Tidak ada item dengan nama ini di toko");
    }
}
