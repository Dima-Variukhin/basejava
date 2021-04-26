package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    static final String R_PRESENT = " already exists";
    static final String R_NOT_PRESENT = " is not present";

    public void save(Resume resume) {
        // сделали инлайн метода так как он используется здесь только один раз
        if (findIndex(resume.getUuid()) == -1) {
            if (size < STORAGE_LIMIT) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("List is full");
            }
        } else {
            System.out.println(resume.getUuid() + R_PRESENT);
        }
    }

    public int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
