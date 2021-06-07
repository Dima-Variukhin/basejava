package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public List<Resume> getAll() {
        List<Resume> resumes = new ArrayList<>();
        File[] files = new File(directory.getAbsolutePath()).listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                resumes.add(new Resume(file.toString()));
            }
        }
        return resumes;
    }

    @Override
    public Resume getFrom(File file) {
        return doGet(file);
    }

    @Override
    public void updateTo(File file, Resume resume) {
        try {
            doDelete(file);
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    void deleteFrom(File file) {
        doDelete(file);
    }

    @Override
    void saveTo(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        try (PrintWriter pw = new PrintWriter(directory)
        ) {
            pw.print("");
        } catch (FileNotFoundException e) {
            throw new StorageException("IO error", e.toString());
        }
    }

    @Override
    public int size() {
        return (int) directory.length();
    }

    protected abstract Resume doGet(File file);

    protected abstract void doDelete(File file);

    protected abstract void doWrite(Resume resume, File file) throws IOException;

}
