package org.dfpl.lecture;

public class IncreaseThread extends Thread{

    @Override
    public void run() {
            for(int i = 0; i< 10000;i++){
                synchronized (App.obj){
                    App.cnt++;
                }
            }
        System.out.println("end!");
    }
}
