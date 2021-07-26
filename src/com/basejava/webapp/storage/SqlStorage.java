package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.executeSql("DELETE FROM resume");
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count (*) FROM resume", code -> {
            ResultSet rs = code.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid=?", code -> {
            code.setString(1, uuid);
            if (code.executeUpdate() == 0) {
                throw new NotExistStorageException(null);
            }
            return code.executeUpdate();
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", code -> {
            code.setString(1, r.getUuid());
            code.setString(2, r.getFullName());
            return code.executeUpdate();
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", code -> {
            code.setString(1, r.getFullName());
            code.setString(2, r.getUuid());
            if (code.executeUpdate() == 0) {
                throw new NotExistStorageException(null);
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume WHERE uuid =?", code -> {
            code.setString(1, uuid);
            ResultSet rs = code.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name,uuid", code -> {
                    ResultSet rs = code.executeQuery();
                    List<Resume> resumes = new ArrayList<>();
                    while (rs.next()) {
                        resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
                    }
                    return resumes;
                }
        );
    }
}
