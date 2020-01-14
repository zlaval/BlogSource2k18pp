package com.zlrx.designpattern.observable;

public class ChangeSalay implements Runnable {

    private Subject subject;

    public ChangeSalay(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                int rnd = (int) (Math.random() * 10000);
                ((Company) subject).updateMaxSalary(rnd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
