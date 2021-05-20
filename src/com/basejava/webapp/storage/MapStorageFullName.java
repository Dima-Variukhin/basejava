package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorageFullName extends AbstractStorage {
    private Map<String, Resume> mapResumesFullName = new HashMap<>();

    @Override
    public void clear() {
        mapResumesFullName.clear();
    }

    @Override
    public int size() {
        return mapResumesFullName.size();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(mapResumesFullName.values());
    }

    @Override
    public Resume getFrom(Object searchFullName) {
        return mapResumesFullName.remove((((Resume) searchFullName).getFullName()));
    }

    @Override
    public void updateTo(Object searchFullName, Resume resume) {
        mapResumesFullName.put(((Resume) searchFullName).getFullName(), resume);
    }

    @Override
    void deleteFrom(Object searchFullName) {
        mapResumesFullName.remove(((Resume) searchFullName).getFullName());
    }

    @Override
    void saveTo(Resume resume, Object searchFullName) {
        mapResumesFullName.put(((Resume) searchFullName).getFullName(), resume);
    }

    @Override
    boolean isExist(Object searchFullName) {
        return mapResumesFullName.containsValue(((Resume) searchFullName));
    }

    public Resume findSearchKey(String uuid) {
        return new Resume(uuid);
    }
}
