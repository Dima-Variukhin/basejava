package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    void deleteFromArray(String uuid) {
        int index = findIndex(uuid);
        System.arraycopy(storage, 1, storage, 0, storage.length - 1);
        storage[size - 1] = null;
        size--;
    }

    @Override
    void saveToArray(Resume resume) {
        int index = findIndex(resume.getUuid());
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    public int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
