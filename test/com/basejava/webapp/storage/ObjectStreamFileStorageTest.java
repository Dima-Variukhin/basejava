package com.basejava.webapp.storage;

import java.io.File;

public class ObjectStreamFileStorageTest extends FileStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new FileStorage(new File(STORAGE_DIR.toString()), new ObjectSerializer()));
    }
}