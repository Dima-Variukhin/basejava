package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {
    void clear();

    int size();

    void delete(String uuid);

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    List<Resume> getAllSorted();
}
