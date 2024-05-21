package com.astrallinear.astrallinear.Beruang;

class TimerProc{
    public boolean timer = false;
    public boolean stop = false;

    synchronized void Timer(){  
        while(!timer){
            try {wait();}
            catch (Exception e) {}
        }
        long timeNow = System.currentTimeMillis(); long timeTemp;
        while((timeNow+5000) > System.currentTimeMillis() && !stop){
            try{
                timeTemp = ((timeNow+5000)-System.currentTimeMillis());
                System.out.print(timeTemp/1000 + "." + timeTemp);
                System.out.print('\r');
                wait(10);
            } catch (Exception e) {}
        } System.out.print("");
        timer = false;
        stop = false;
    }

    synchronized void ready(){
        timer = true;
        notify();
    }

    synchronized void Interrupt(){
        stop = true;
        notify();
    }
}

class TimerProcRun extends Thread {
    public TimerProc tpc;

    public TimerProcRun(){
        this.tpc = new TimerProc();
    }

    @Override
    public void run(){
        while(true) {
            this.tpc.Timer();
        }
    }
}
