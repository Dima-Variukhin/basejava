package com.basejava.webapp.storage;


public class ObjectStreamPathStorageTest extends PathStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getPath(), new ObjectSerializer()));
    }
}