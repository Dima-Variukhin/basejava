package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void arrayOverflow() {
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT - 3; i++) {
            try {
                storage.save(new Resume("Name" + i));
            } catch (StorageException e) {
                Assert.fail("Array is overflowed");
            }
        }
        storage.save(new Resume("Overflow"));
    }
}