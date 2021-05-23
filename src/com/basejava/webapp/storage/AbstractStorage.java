package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
    @Override
    public void delete(String uuid) {
        Object searchKey = getExistedElement(uuid);
        deleteFrom(searchKey);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistedElement(resume.getUuid());
        saveTo(resume, searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistedElement(resume.getUuid());
        updateTo(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistedElement(uuid);
        return getFrom(searchKey);
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