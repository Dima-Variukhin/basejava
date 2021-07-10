package com.basejava.webapp;

public class DeadLock {
    public static final Object lockA = new Object();
    public static final Object lockB = new Object();

    public static void main(String[] args) {
        deadLock();
    }

    public static void deadLock() {
        new Thread(() -> {
            synchronized (lockA) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+ " captured lock A");
                System.out.println(Thread.currentThread().getName()+ " Waiting for Thread-1 to release lock B");
                synchronized (lockB) {
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lockB) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+ " captured lock B");
                System.out.println(Thread.currentThread().getName()+ " Waiting for Thread-0 to release lock A");
                synchronized (lockA) {
                }
            }
        }).start();
    }
}