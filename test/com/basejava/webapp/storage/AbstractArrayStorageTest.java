package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final int STORAGE_LIMIT = 10_000;
    private Storage storage;
    Resume resume = new Resume();

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
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

    @Test
    public void update() {
        Resume resume = new Resume(UUID_3);
        storage.update(resume);
        Assert.assertEquals(storage.get(UUID_3), resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        Assert.assertEquals(storage.get("uuid4"), resume);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_3));
    }

    @Test(expected = StorageException.class)
    public void arrayOverflow() {
        for (int i = 0; i < STORAGE_LIMIT - 3; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                Assert.fail("Array is overflowed");
            }
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(resume.getUuid());
        storage.get(resume.getUuid());
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {

        Assert.assertEquals(resume, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resumes, storage.getAll());
    }

    public void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}