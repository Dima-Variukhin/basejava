package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;


public class ListStorage extends AbstractStorage<Integer> {
    protected List<Resume> resumes = new ArrayList<>();

    @Override
    void deleteFrom(Integer index) {
        resumes.remove(index.intValue());
    }

    @Override
    void saveTo(Resume resume, Integer index) {
        resumes.add(resume);
    }

    @Override
    public Resume getFrom(Integer index) {
        return resumes.get(index);
    }

    @Override
    public void updateTo(Integer index, Resume resume) {
        resumes.set(index, resume);
        System.out.println("Resume " + resumes.get(index).getUuid() + " is updated");
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
    public List<Resume> getAll() {
        return new ArrayList<>(resumes);
    }

    @Override
    boolean isExist(Integer searchKey) {
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
