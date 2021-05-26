package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    @Override
    public void delete(String uuid) {
        LOG.info("Delete "+ uuid);
        SK searchKey = getExistedElement(uuid);
        deleteFrom(searchKey);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save "+ resume);
        SK searchKey = getNotExistedElement(resume.getUuid());
        saveTo(resume, searchKey);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update "+ resume);
        SK searchKey = getExistedElement(resume.getUuid());
        updateTo(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get "+ uuid);
        SK searchKey = getExistedElement(uuid);
        return getFrom(searchKey);
    }

    private SK getExistedElement(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedElement(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = getAll();
        Collections.sort(list);
        return list;
    }

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public abstract List<Resume> getAll();

    public abstract Resume getFrom(SK index);

    public abstract void updateTo(SK index, Resume resume);

    public abstract SK findSearchKey(String uuid);

    abstract void deleteFrom(SK index);

    abstract void saveTo(Resume resume, SK index);

    abstract boolean isExist(SK searchKey);
}