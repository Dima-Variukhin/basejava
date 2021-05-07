package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.ArrayList;

public abstract class AbstractStorage implements Storage {
    protected ArrayList<Resume> resumes = new ArrayList<>();

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            deleteFromArray(uuid, index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            saveToArray(resume, index);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            resumes.set(index, resume);
            System.out.println("Resume " + resumes.get(index).getUuid() + " is updated");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return resumes.get(index);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return resumes.toArray(Resume[]::new);
    }

    public abstract int findIndex(String uuid);

    abstract void deleteFromArray(String uuid, int index);

    abstract void saveToArray(Resume resume, int index);
}