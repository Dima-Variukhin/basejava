package com.basejava.webapp.storage.serialization;

import com.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {
    Resume doRead(InputStream is) throws IOException;

    void doWrite(Resume resume, OutputStream os) throws IOException;
}
