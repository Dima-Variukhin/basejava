package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> mapResumes = new HashMap<>();

    @Override
    public Resume getFrom(Object index) {
        String element = (String) index;
        return mapResumes.get(element);
    }

    @Override
    public void updateTo(Object index, Resume resume) {
        mapResumes.put((String) index, resume);
    }

    @Override
    void deleteFrom(Object index) {
        String s = (String) index;
        mapResumes.remove(s);
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
        for (Map.Entry<String, Resume> entry : mapResumes.entrySet()){
            if (Objects.equals(searchKey,entry.getKey())){
                return true;
            }
        }
        return false;
    }

    public String findSearchKey(String uuid) {
       return uuid;
    }
}
