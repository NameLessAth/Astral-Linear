package com.astrallinear.astrallinear.Kartu;

public abstract class Kartu {
    private String nama;
    public Kartu(String s) {
        nama = s;
    }
    public String getNama() {
        return nama;
    }

    public abstract String getPathToImg();
    public abstract String getInfo();
}

class NamaKartuTidakAdaException extends Exception {
    public NamaKartuTidakAdaException(String s) {
        super(s);
    }
}