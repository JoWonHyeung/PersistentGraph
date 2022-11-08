package org.dfpl.lecture;

public class HelloThread extends Thread {

    @Override
    public void run() {
        System.out.println("hello");
        System.out.println("Thread!");
    }
}
