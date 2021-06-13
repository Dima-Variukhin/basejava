package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serialization.ObjectSerializer;

import java.io.File;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new FileStorage(new File(STORAGE_DIR.toString()), new ObjectSerializer()));
    }
}