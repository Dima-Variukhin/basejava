//package com.basejava.webapp.storage;
//
//import com.basejava.webapp.exception.StorageException;
//import com.basejava.webapp.model.Resume;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public abstract class AbstractPathStorage extends AbstractStorage<Path> {
//    protected Path directory;
//
//    protected AbstractPathStorage(String dir) {
//        directory = Paths.get(dir);
//        Objects.requireNonNull(directory, "directory must not be null");
//        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
//            throw new IllegalArgumentException(dir + " is not directory or is not writable");
//        }
//    }
//
//    @Override
//    public List<Resume> getAll() {
//        Path[] paths = directorylistPaths();
//        if (paths == null) {
//            throw new StorageException("Directory read error", null);
//        }
//        List<Resume> list = new ArrayList<>(paths.length);
//        for (Path Path : paths) {
//            list.add(getFrom(Path));
//        }
//        return list;
//    }
//
//    @Override
//    public Resume getFrom(Path path) {
//        try {
//            return doRead(new BufferedInputStream(new PathInputStream(path)));
//        } catch (IOException e) {
//            throw new StorageException("Path read error", path.toFile().getName(), e);
//        }
//    }
//
//    @Override
//    public void updateTo(Path path, Resume resume) {
//        try {
//            doWrite(resume, new BufferedOutputStream(new PathOutputStream(path)));
//        } catch (IOException e) {
//            throw new StorageException("Path write error", resume.getUuid(), e);
//        }
//    }
//
//    @Override
//    public Path findSearchKey(String uuid) {
//        return new Path(directory, uuid);
//    }
//
//    @Override
//    void deleteFrom(Path path) {
//        if (!path.delete()) {
//            throw new StorageException("Path delete error", path.getName());
//        }
//    }
//
//    @Override
//    void saveTo(Resume resume, Path path) {
//        try {
//            path.createNewFile();
//        } catch (IOException e) {
//            throw new StorageException("Couldn't create Path " + path.toFile().getAbsolutePath(), path.getName(), e);
//        }
//    }
//
//    @Override
//    boolean isExist(Path path) {
//        return path.exists();
//    }
//
//    @Override
//    public void clear() {
//        try {
//            Files.list(directory).forEach(this::deleteFrom);
//        } catch (IOException e) {
//            throw new StorageException("Path delete error", null);
//        }
//    }
//
//    @Override
//    public int size() {
//        String[] list = directory.list();
//        if (list == null) {
//            throw new StorageException("Directory read error", null);
//        }
//        return list.length;
//    }
//
//    protected abstract Resume doRead(InputStream is) throws IOException;
//
//    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;
//}
