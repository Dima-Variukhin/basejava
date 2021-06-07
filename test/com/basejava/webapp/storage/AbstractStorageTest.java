package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.model.ResumeTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "1";
    private static final String UUID_2 = "2";
    private static final String UUID_3 = "3";
    private static final String UUID_4 = "4";

    protected final Storage storage;

    Resume resume1 = ResumeTestData.createResume("Name1",UUID_1);
    Resume resume2 = ResumeTestData.createResume("Name2",UUID_2);
    Resume resume3 = ResumeTestData.createResume("Name3",UUID_3);
    Resume resume4 = ResumeTestData.createResume("Name4",UUID_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        storage.get(UUID_3);
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void save() {
        Resume resume = ResumeTestData.createResume("Name4", UUID_4);
        storage.save(resume);
        Assert.assertEquals(resume, storage.get(UUID_4));
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_3, "Name3"));
    }

    @Test
    public void update() {
        Resume resume = ResumeTestData.createResume("Name3", UUID_3);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void get() {
        Resume resume = ResumeTestData.createResume("Name3", UUID_3);
        Assert.assertEquals(resume, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        resumes.add(ResumeTestData.createResume("Name1", UUID_1));
        resumes.add(ResumeTestData.createResume("Name2", UUID_2));
        resumes.add(ResumeTestData.createResume("Name3", UUID_3));
        Assert.assertEquals(resumes, storage.getAllSorted());
    }

    public void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}