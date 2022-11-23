package org.dfpl.lecture;

import java.util.concurrent.Semaphore;

public class main {

    public static int count = 0;
    public static Semaphore sem = new Semaphore(1);
    public static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        new IncreaseThread().start();
        new IncreaseThread().start();
        new IncreaseThread().start();
        new IncreaseThread().start();


        Thread.sleep(3000);
        System.out.println(count);
    }
}
