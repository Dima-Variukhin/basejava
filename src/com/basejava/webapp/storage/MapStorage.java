package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> mapResumes = new HashMap<>();

    @Override
    public Resume getFrom(Object searchKey) {
        return mapResumes.get(((String) searchKey));
    }

    @Override
    public void updateTo(Object searchKey, Resume resume) {
        mapResumes.put((String) searchKey, resume);
    }

    @Override
    void deleteFrom(Object searchKey) {
        mapResumes.remove(((String) searchKey));
    }

    @Override
    void saveTo(Resume resume, Object searchKey) {
        mapResumes.put((String) searchKey, resume);
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
    public List<Resume> getAllSorted() {
        return new ArrayList<>(mapResumes.values());
    }

    @Override
    public boolean isExist(Object searchKey) {
        return mapResumes.containsKey(((Resume) searchKey).getFullName());
    }

    public String findSearchKey(String uuid) {
        return uuid;
    }
}
