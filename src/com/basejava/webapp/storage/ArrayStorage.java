package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    void deleteFromArray(String uuid) {
        int index = findIndex(uuid);
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    @Override
    void saveToArray(Resume resume) {
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
