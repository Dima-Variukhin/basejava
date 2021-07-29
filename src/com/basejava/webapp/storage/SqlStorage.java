package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.executeSQL("DELETE FROM resume");
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

        sqlHelper.execute("DELETE FROM contact where resume_uuid=?", code -> {
            code.setString(1, uuid);
            code.execute();
            return code.executeUpdate();
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContacts(r, conn);
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContacts(r);
            insertContacts(r, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r " +
                " LEFT JOIN contact c " +
                "    on r.uuid = c.resume_uuid" +
                " WHERE r.uuid =? ", code -> {
            code.setString(1, uuid);
            ResultSet rs = code.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            do {
                String value = rs.getString("value");
                ContactType contactType = ContactType.valueOf(rs.getString("type"));
                r.addContact(contactType, value);
            } while (rs.next());
            return r;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume left join contact c on resume.uuid = c.resume_uuid ORDER BY full_name,uuid", code -> {
                    ResultSet rs = code.executeQuery();
                    Map<String, Resume> resumeMap = new HashMap<>();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        Resume resume = resumeMap.get(uuid);
                        if (resume == null) {
                            resume = new Resume(uuid, rs.getString("full_name"));
                            resumeMap.put(uuid, resume);
                        }
                        String value = rs.getString("value");
                        if (value != null) {
                            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
                        }
                    }
                    return new ArrayList<>(resumeMap.values());
                }
        );
    }

    private void deleteContacts(Resume r) {
        sqlHelper.execute("DELETE FROM contact where resume_uuid=?", code -> {
            code.setString(1, r.getUuid());
            code.execute();
            return code.executeUpdate();
        });
    }

    private void insertContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
