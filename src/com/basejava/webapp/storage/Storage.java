package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {
    void clear();

    int size();

    void delete(String uuid);

    Resume get(String uuid);

    Resume[] getAll();

    void save(Resume resume);

    void update(Resume resume);

    int findIndex(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
}
