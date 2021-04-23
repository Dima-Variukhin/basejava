package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            index = -index - 1;
        } else {
            System.out.println(resume.getUuid() + " is not present");
            return;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("List is full");
        }
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            index = -index - 1;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index <= 0) {
            index = -index - 1;
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println(uuid + " is not present");
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
