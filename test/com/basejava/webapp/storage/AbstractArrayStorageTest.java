package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    Resume resume = new Resume();

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume);
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(1, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void clear() throws Exception {
        storage.clear();
        storage.get(resume.getUuid());
    }

    @Test
    public void update() throws Exception {
        Assert.assertEquals(resume.getUuid(), resume.getUuid());
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(1, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(resume.getUuid());
        Assert.assertNotEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(resume, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void arrayOverflow() {
        for (int i = 0; i < 9999; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                Assert.fail("Array is overflowed");
            }
        }
        storage.save(new Resume());
    }
}