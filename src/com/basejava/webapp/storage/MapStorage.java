package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> mapResumes = new HashMap<>();

    @Override
    public Resume getFrom(int index) {
        return mapResumes.get(String.valueOf(index));
    }

    @Override
    public void updateTo(int index, Resume resume) {
        mapResumes.put(String.valueOf(index), mapResumes.get(String.valueOf(index)));
    }

    @Override
    void deleteFrom(int index) {
        mapResumes.remove(String.valueOf(index));
    }

    @Override
    void saveTo(Resume resume, int index) {
        mapResumes.put(resume.getUuid(), resume);
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

    public int findIndex(String uuid) {
        for (Map.Entry<String, Resume> entry : mapResumes.entrySet()) {
            if (uuid.equals(entry.getKey())) {
                return Integer.parseInt(entry.getKey());
            }
        }
        return -1;
    }
}
