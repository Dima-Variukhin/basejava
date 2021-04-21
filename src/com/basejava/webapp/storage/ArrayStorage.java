package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static final String R_PRESENT = " already exists";
    static final String R_NOT_PRESENT = " is not present";
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        //заполняем все size with null
        Arrays.fill(storage, 0, size, null);
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume " + storage[index].getUuid() + " is updated");
        } else {
            System.out.println(resume.getUuid() + R_NOT_PRESENT);
        }
    }

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index == -1) {
            if (size < storage.length) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("List is full");
            }
        } else {
            System.out.println(resume.getUuid() + R_PRESENT);
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println(uuid + R_NOT_PRESENT);
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
        int index = findIndex(uuid);
        if (index != -1) {
            //мы i-ому элементу присваиваем значение последнего
            storage[index] = storage[size - 1];
            //а последнего делаем null
            storage[size - 1] = null;
            //уменьшаем размер
            size--;
            return;
        }
        System.out.println(uuid + R_NOT_PRESENT);
    }

    public int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == storage[i].getUuid()) {
                return i;
            }
        }
        return -1;
    }
}
