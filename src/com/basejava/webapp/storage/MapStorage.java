package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> mapResumes = new HashMap<>();

    @Override
    public Resume getFrom(String searchKey) {
        return mapResumes.get(searchKey);
    }

    @Override
    public void updateTo(String searchKey, Resume resume) {
        mapResumes.put(searchKey, resume);
    }

    @Override
    void deleteFrom(String searchKey) {
        mapResumes.remove(searchKey);
    }

    @Override
    void saveTo(Resume resume, String searchKey) {
        mapResumes.put(searchKey, resume);
    }

    @Override
    public void clear() {
        mapResumes.clear();
    }

    @Override
    public int size() {
        return mapResumes.size();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(mapResumes.values());
    }

    @Override
    public boolean isExist(String searchKey) {
        return mapResumes.containsKey(searchKey);
    }

    public String findSearchKey(String uuid) {
        return uuid;
    }
}
