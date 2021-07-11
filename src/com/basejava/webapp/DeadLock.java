package com.basejava.webapp;

public class DeadLock {
    public static final Object lockA = new Object();
    public static final Object lockB = new Object();

    public static void main(String[] args) {
        deadLockThread(lockA, lockB, "A", "1", "B");
        deadLockThread(lockB, lockA, "B", "0", "A");
    }

    private static void deadLockThread(Object lock1, Object lock2, String lockName, String threadNumber, String releaseLock) {
        new Thread(() -> {
            synchronized (lock2) {
                threadSleep();
                outPut(lockName, threadNumber, releaseLock);
                synchronized (lock1) {
                    System.out.println("It'll never out of here");
                }
            }
        }).start();
    }

    private static void outPut(String lockName, String threadNumber, String releaseLock) {
        System.out.println(Thread.currentThread().getName() + " captured lock " + lockName);
        System.out.println(Thread.currentThread().getName() + " Waiting for Thread-" + threadNumber + " to release lock " + releaseLock);
    }

    private static void threadSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}