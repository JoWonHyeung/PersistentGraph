package org.dfpl.lecture;

public class HelloThread extends Thread{

    @Override
    public void run(){
        System.out.println("Hello");
        System.out.println(" Thread");
    }
}
