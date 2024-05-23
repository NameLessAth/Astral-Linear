package com.astrallinear.astrallinear.Beruang;

class TimerProc{
    public boolean timer = false;
    public boolean stop = false;
    public Integer durasi = 60000;

    synchronized void Timer(){  
        while(!timer){
            try {wait();}
            catch (Exception e) {}
        }
        long timeNow = System.currentTimeMillis(); long timeTemp;
        while((timeNow+durasi) > System.currentTimeMillis() && !stop){
            try{
                timeTemp = ((timeNow+durasi)-System.currentTimeMillis());
                System.out.print(timeTemp/1000 + "." + timeTemp);
                System.out.print('\r');
                wait(10);
            } catch (Exception e) {}
        } System.out.print("");
        timer = false;
        stop = false;
    }

    synchronized void ready(Integer durasiStart){
        durasi = durasiStart;
        timer = true;
        notify();
    }

    synchronized void Interrupt(){
        stop = true;
        notify();
    }
}

