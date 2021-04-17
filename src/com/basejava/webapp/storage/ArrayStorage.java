package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final String R_PRESENT = "Resume already exists";
    static final String R_NOT_PRESENT = "Resume is not present";
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == resume) {
                storage[size] = resume;
                System.out.println("Resume is updated");
            } else {
                System.out.println(R_PRESENT);
            }
        }
    }

    public void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == r) {
                System.out.println(R_PRESENT);
                break;
            }
        }
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("List is full");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                return storage[i];
            } else {
                System.out.println(R_NOT_PRESENT);
            }
        }
        return null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) { // если uuid равен i-ому гэтuuid
                storage[i] = storage[size - 1]; //  мы i-ому элементу присваиваем значение последнего
                storage[size - 1] = null; // а последнего делаем null
                size--; // уменьшаем размер
                return;
            } else {
                System.out.println(R_NOT_PRESENT);
            }
        }
        System.out.println("Resume not exist");
    }
}
