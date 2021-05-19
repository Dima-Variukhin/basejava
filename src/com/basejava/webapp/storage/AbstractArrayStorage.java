package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        //заполняем все size with null
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    void saveTo(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveToArray(resume, (Integer) index);
        size++;
    }

    @Override
    void deleteFrom(Object index) {
        deleteFromArray((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void updateTo(Object index, Resume resume) {
        storage[(int) index] = resume;
        System.out.println("Resume " + storage[(int) index].getUuid() + " is updated");
    }

    @Override
    public Resume getFrom(Object index) {
        return storage[(int) index];
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOfRange(storage, 0, size)));
    }

    public boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public abstract void saveToArray(Resume resume, int index);

    public abstract void deleteFromArray(int index);

    public abstract Integer findSearchKey(String uuid);
}
