package com.basejava.webapp.storage;


public class ObjectStreamPathStorageTest extends AbstractPathStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.getPath(), new ObjectSerializer()));
    }
}