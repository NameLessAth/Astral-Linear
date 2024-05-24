package com.astrallinear.astrallinear.Beruang;

public class BearAttackRun extends Thread{
    public BearAttack brt;
    public TimerProcRun tpr;

    public BearAttackRun(TimerProcRun tpr){
        this.brt = new BearAttack();
        this.tpr = tpr;
    }

    @Override
    public void run() {
        try { this.brt.Attack(this.tpr); }
        catch (Exception e) { 
            System.out.println(e.getMessage());
        }    
    } 
}