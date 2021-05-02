package com.basejava.webapp.exception;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new");
        System.out.println(r);
    }
}
