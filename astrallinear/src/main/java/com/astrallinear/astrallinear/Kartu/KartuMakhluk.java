package com.astrallinear.astrallinear.Kartu;
import java.util.HashMap;

public abstract class KartuMakhluk extends Kartu {
    private HashMap<String, Integer> itemAktif;
    private KartuProduk drop;

    public KartuMakhluk(String nama, KartuProduk drop) {
        super(nama);
        this.drop = drop;
        this.itemAktif = new HashMap<>();
    }

    public HashMap<String, Integer> getItemAktif(){
        return this.itemAktif;
    }

    public void addItemAktif(KartuItem efek){
        if (!itemAktif.containsKey(efek.getNama())){
            itemAktif.put(efek.getNama(), 1);
        } else{
            itemAktif.put(efek.getNama(), itemAktif.get(efek.getNama())+1);
        }
    }

    public void removeItemAktif(KartuItem efek){
        if (itemAktif.containsKey(efek.getNama())){
            itemAktif.put(efek.getNama(), itemAktif.get(efek.getNama())-1);
            if (itemAktif.get(efek.getNama()) == 0) {
                itemAktif.remove(efek.getNama());
            }
        }
        // else di sini harusnya throw exception item aktif tidak ditemukan
    }

    public KartuProduk panen() throws BelumSiapPanenException {
        if (!isSiapPanen()) {
            throw new BelumSiapPanenException("Hey, makhluk ini belum bisa dipanen!");
        } else {
            return drop;
        }
    }

    public KartuProduk instant_harvest_panen() { return drop ; }

    public abstract boolean isSiapPanen();
    public abstract void accelerate();
    public abstract void delay();
}

class BelumSiapPanenException extends Exception {

    public BelumSiapPanenException(String s) {
        super(s);
    }

}