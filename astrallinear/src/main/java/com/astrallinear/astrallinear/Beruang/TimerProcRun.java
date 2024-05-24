package com.astrallinear.astrallinear.Beruang;

public class TimerProcRun extends Thread {
    public TimerProc tpc;

    public TimerProcRun(){
        this.tpc = new TimerProc();
    }

    @Override
    public void run(){
        this.tpc.Timer();
    }
}
