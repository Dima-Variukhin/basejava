package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void deleteFromArray(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    public void saveToArray(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    public Integer findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
