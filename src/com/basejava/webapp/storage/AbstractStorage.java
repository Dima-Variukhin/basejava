package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected ListStorage listStorage;

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            deleteFrom(uuid, index);
            return;
        }
        throw new NotExistStorageException(uuid);
    }


    @Override
    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            saveTo(resume, index);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            listStorage.resumes.set(index, resume);
            System.out.println("Resume " + listStorage.resumes.get(index).getUuid() + " is updated");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return listStorage.resumes.get(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public abstract int findIndex(String uuid);

    abstract void deleteFrom(String uuid, int index);

    abstract void saveTo(Resume resume, int index);

}