package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> mapResumes = new HashMap<>();

    @Override
    public Resume getFrom(Object index) {
        return mapResumes.get(((String) index));
    }

    @Override
    public void updateTo(Object index, Resume resume) {
        mapResumes.put((String) index, resume);
    }

    @Override
    void deleteFrom(Object index) {
        mapResumes.remove(((String) index));
    }

    @Override
    void saveTo(Resume resume, Object index) {
        mapResumes.put((String) index, resume);
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
    public Resume[] getAll() {
        return mapResumes.values().toArray(Resume[]::new);
    }

    @Override
    public boolean isExist(Object searchKey){
        return mapResumes.containsKey(searchKey.toString());
    }

    public String findSearchKey(String uuid) {
       return uuid;
    }
}
