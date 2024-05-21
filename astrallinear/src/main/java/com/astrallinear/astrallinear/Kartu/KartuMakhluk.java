package com.astrallinear.astrallinear.Kartu;

public abstract class KartuMakhluk extends Kartu {
    
    private KartuProduk drop;
    public KartuMakhluk(String nama, KartuProduk drop) {
        super(nama);
        this.drop = drop;
    }

    public KartuProduk panen() throws BelumSiapPanenException {
        if (!isSiapPanen()) {
            throw new BelumSiapPanenException("Hey, makhluk ini belum bisa dipanen!");
        } else {
            return drop;
        }
    }

    public abstract boolean isSiapPanen();
    public abstract void accelerate();
    public abstract void delay();
}

class BelumSiapPanenException extends Exception {

    public BelumSiapPanenException(String s) {
        super(s);
    }

}