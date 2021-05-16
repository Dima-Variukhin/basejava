package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void delete(String uuid) {
        Object index = getExistedElement(uuid);
        deleteFrom(index);
    }

    @Override
    public void save(Resume resume) {
        Object index = getNotExistedElement(resume.getUuid());
        saveTo(resume, index);
    }

    @Override
    public void update(Resume resume) {
        Object index = getExistedElement(resume.getUuid());
        updateTo(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object index = getExistedElement(uuid);
        return getFrom(index);
    }

    private Object getExistedElement(String uuid) {
        Object searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedElement(String uuid) {
        Object searchKey = findSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public abstract Resume getFrom(Object index);

    public abstract void updateTo(Object index, Resume resume);

    public abstract Object findSearchKey(String uuid);

    abstract void deleteFrom(Object index);

    abstract void saveTo(Resume resume, Object index);

    abstract boolean isExist(Object searchKey);
}