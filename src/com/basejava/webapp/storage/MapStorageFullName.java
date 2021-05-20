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
    public Resume getFrom(Object searchKey) {
        return mapResumesFullName.remove((((Resume) searchKey).getFullName()));
    }

    @Override
    public void updateTo(Object searchKey, Resume resume) {
        mapResumesFullName.put(((Resume) searchKey).getFullName(), resume);
    }

    @Override
    void deleteFrom(Object searchKey) {
        mapResumesFullName.remove(((Resume) searchKey).getFullName());
    }

    @Override
    void saveTo(Resume resume, Object searchKey) {
        mapResumesFullName.put(((Resume) searchKey).getFullName(), resume);
    }

    @Override
    boolean isExist(Object searchKey) {
        return mapResumesFullName.containsValue(((Resume) searchKey));
    }

    public Resume findSearchKey(String uuid) {
        return new Resume(uuid);
    }
}
