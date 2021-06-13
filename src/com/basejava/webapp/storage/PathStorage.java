package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.serialization.SerializationStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private SerializationStrategy serializationStrategy;

    protected PathStorage(String dir, SerializationStrategy serializationStrategy) {
        directory = Paths.get(dir);
        this.serializationStrategy = serializationStrategy;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public List<Resume> getAll() {
        return catchStorageException("Path read error").map(this::getFrom).collect(Collectors.toList());
    }

    @Override
    public Resume getFrom(Path path) {
        try {
            return serializationStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    public void updateTo(Path path, Resume resume) {
        try {
            serializationStrategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    public Path findSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    void deleteFrom(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString());
        }
    }

    @Override
    void saveTo(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + path.getFileName().toString(), e.getMessage());
        }
        updateTo(path, resume);
    }

    @Override
    boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public void clear() {
        catchStorageException("Path delete error").forEach(this::deleteFrom);
    }

    @Override
    public int size() {
        return (int) catchStorageException("Path size error").count();
    }

    private Stream<Path> catchStorageException(String text) {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException(text, null);
        }
    }
}
