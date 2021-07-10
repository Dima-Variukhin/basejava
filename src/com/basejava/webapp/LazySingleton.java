package com.basejava.webapp;

public class LazySingleton {
    int i;
    volatile private static LazySingleton INSTANCE = new LazySingleton();

    LazySingleton() {
    }

    private static class LazySingletonHolder {
        private static LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }
}



