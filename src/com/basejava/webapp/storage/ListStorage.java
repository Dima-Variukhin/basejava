package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public class ListStorage extends AbstractStorage {
    @Override
    void deleteFromArray(String uuid, int index) {
        resumes.remove(index);
    }

    @Override
    void saveToArray(Resume resume, int index) {
        resumes.add(resume);
    }

    @Override
    public int findIndex(String uuid) {
        return resumes.indexOf(new Resume(uuid));
    }
}
