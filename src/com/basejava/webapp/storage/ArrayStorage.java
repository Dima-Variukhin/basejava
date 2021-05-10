package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    void deleteFrom(String uuid, int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    void saveTo(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    public int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
