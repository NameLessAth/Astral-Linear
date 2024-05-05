package com.mycompany.app;

public class IWantToTestThis 
{
    private int x;
    public IWantToTestThis() {}

    public void setx1() {
        x = 1;
    }
    public void setx2() {
        x = 1; // this is wrong.
    }
    public int getx() {
        return x;
    }
}
