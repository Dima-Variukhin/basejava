package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    void saveTo(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveToArray(resume, index);
        size++;
    }

    @Override
    void deleteFrom(Integer index) {
        deleteFromArray(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void updateTo(Integer index, Resume resume) {
        storage[index] = resume;
        System.out.println("Resume " + storage[(int) index].getUuid() + " is updated");
    }

    @Override
    public Resume getFrom(Integer index) {
        return storage[index];
    }

    @Override
    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public boolean isExist(Integer index) {
        return index >= 0;
    }

    public abstract void saveToArray(Resume resume, int index);

    public abstract void deleteFromArray(int index);

    public abstract Integer findSearchKey(String uuid);
}
