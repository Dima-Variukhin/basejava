package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("New Name");
        Field field = r.getClass().getDeclaredFields()[0];
        System.out.println(field.getName());
        Method method = Resume.class.getMethod("toString");
        System.out.println(method.invoke(r));
    }
}
