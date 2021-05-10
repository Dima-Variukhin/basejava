package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> resumes = new ArrayList<>();

    @Override
    void deleteFrom(String uuid, int index) {
        resumes.remove(index);
    }

    @Override
    void saveTo(Resume resume, int index) {
        resumes.add(resume);
    }

    @Override
    public Resume getFrom(int index) {
        return resumes.get(index);
    }

    @Override
    public void updateTo(int index, Resume resume) {
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
    public Resume[] getAll() {
        return resumes.toArray(Resume[]::new);
    }

    @Override
    public int findIndex(String uuid) {
        return resumes.indexOf(new Resume(uuid));
    }
}
