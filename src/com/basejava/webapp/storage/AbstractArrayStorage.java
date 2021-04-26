package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        //заполняем все size with null
        Arrays.fill(storage, 0, size, null);
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
        System.out.println(uuid + " is not present");
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("List is full");
        }
        if (findIndex(resume.getUuid()) < 0) {
            differPartOfSave(resume);
        } else {
            System.out.println(resume.getUuid() + " already exist");
        }
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume " + storage[index].getUuid() + " is updated");
        } else {
            System.out.println(resume.getUuid() + " is not present");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume" + uuid + " not exist");
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    abstract void differPartOfSave(Resume resume);

    public abstract int findIndex(String uuid);
}
