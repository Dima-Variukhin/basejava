package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapStorageResume extends AbstractStorage<Resume> {
    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public int size() {
        return resumeMap.size();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    public Resume getFrom(Resume resume) {
        return resume;
    }

    @Override
    public void updateTo(Resume resume, Resume r) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    void deleteFrom(Resume resume) {
        resumeMap.remove((resume.getUuid()));
    }

    @Override
    void saveTo(Resume r, Resume resume) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    public Resume findSearchKey(String uuid) {
        return resumeMap.get(uuid);
    }
}
