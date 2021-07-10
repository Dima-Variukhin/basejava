package com.basejava.webapp;

public class DeadLock {
    public static final Object lockA = new Object();
    public static final Object lockB = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
    //Realization of deadLock
    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lockA) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("method 1 action 1");
                synchronized (lockB) {
                    System.out.println("method 1 action 2");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (lockB) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("method 2 action 1");
                synchronized (lockA) {
                    System.out.println("method 2 action 2");
                }
            }
        }
    }
}
