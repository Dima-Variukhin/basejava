package com.basejava.webapp.storage;


import com.basejava.webapp.storage.serialization.ObjectSerializer;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new ObjectSerializer()));
    }
}