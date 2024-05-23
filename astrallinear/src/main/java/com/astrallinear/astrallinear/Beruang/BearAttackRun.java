package com.astrallinear.astrallinear.Beruang;

class BearAttackRun extends Thread{
    BearAttack brt;
    TimerProcRun tpr;

    public BearAttackRun(TimerProcRun tpr){
        this.brt = new BearAttack();
        this.tpr = tpr;
    }

    @Override
    public void run(){
        while (true) {
            this.brt.Attack(this.tpr);    
        }  
    } 
}