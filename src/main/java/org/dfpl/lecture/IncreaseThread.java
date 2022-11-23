package org.dfpl.lecture;

public class IncreaseThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            try {
                synchronized (main.obj) {
                    main.count++;
                }
            } catch (Exception e) {
            }
        }
    }
}
