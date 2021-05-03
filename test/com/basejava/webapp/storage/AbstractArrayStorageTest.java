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
        if (storage == null) {
            assertSize(0);
        }
    }

    @Test
    public void update() {
        storage.update(storage.get(UUID_3));
        Assert.assertEquals(storage.get(UUID_3), storage.get(UUID_3));
    }

    @Test
    public void getAll() {
        assertSize(3);
    }

    @Test
    public void save() {
        storage.save(new Resume());
        assertSize(4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(resume.getUuid());
        storage.get(resume.getUuid());
        assertSize(2);
    }

    @Test
    public void get() {
        Assert.assertEquals(resume, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void alreadyExist() {
        storage.save(new Resume(UUID_3));
    }

    @Test(expected = StorageException.class)
    public void arrayOverflow() {
        for (int i = 0; i < 9997; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                Assert.fail("Array is overflowed");
            }
        }
        storage.save(new Resume());
    }

    public void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}