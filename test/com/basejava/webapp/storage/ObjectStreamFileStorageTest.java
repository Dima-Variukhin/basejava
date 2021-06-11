package com.basejava.webapp.storage;

import java.io.File;

public class ObjectStreamFileStorageTest extends AbstractFileStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage(new File(STORAGE_DIR.toString()), new ObjectSerializer()));
    }
}