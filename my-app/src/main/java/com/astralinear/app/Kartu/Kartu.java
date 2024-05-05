package com.astralinear.app.Kartu;

public abstract class Kartu {
    private String nama;
    public Kartu(String s) {
        nama = s;
    }
    public String getNama() {
        return nama;
    }
}
