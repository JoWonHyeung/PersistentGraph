package org.dfpl.lecture;

import java.util.concurrent.Semaphore;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int cnt = 0;
    public static Semaphore sem = new Semaphore(1);
    public static Object obj = new Object();
    public static void main(String[] args) throws Exception {
        new IncreaseThread().start();
        new IncreaseThread().start();
        new IncreaseThread().start();
        new IncreaseThread().start();

        Thread.sleep(3000);

        System.out.println(cnt);
    }
}
