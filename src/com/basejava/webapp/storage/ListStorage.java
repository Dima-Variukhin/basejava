package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;


public class ListStorage extends AbstractStorage {
    protected List<Resume> resumes = new ArrayList<>();

    @Override
    void deleteFrom(Object index) {
        resumes.remove(((Integer) index).intValue());
    }

    @Override
    void saveTo(Resume resume, Object index) {
        resumes.add(resume);
    }

    @Override
    public Resume getFrom(Object index) {
        return resumes.get((Integer) index);
    }

    @Override
    public void updateTo(Object index, Resume resume) {
        resumes.set((Integer) index, resume);
        System.out.println("Resume " + resumes.get((Integer) index).getUuid() + " is updated");
    }

    @Override
    public void clear() {
        resumes.clear();
    }

    @Override
    public int size() {
        return resumes.size();
    }

    @Override
    public List<Resume> getAllSorted() {
        return resumes;
    }

    @Override
    boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public Integer findSearchKey(String uuid) {
        for (Resume r : resumes) {
            if (Objects.equals(r.getUuid(), uuid)) {
                return resumes.indexOf(r);
            }
        }
        return null;
    }
}
