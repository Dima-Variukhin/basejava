package com.basejava.webapp.storage.serialization;import com.basejava.webapp.model.*;import java.io.*;import java.time.LocalDate;import java.util.*;public class DataStreamSerializer implements SerializationStrategy {    @Override    public Resume doRead(InputStream is) throws IOException {        try (DataInputStream dis = new DataInputStream(is)) {            String uuid = dis.readUTF();            String fullName = dis.readUTF();            Resume resume = new Resume(uuid, fullName);            int size = dis.readInt();            for (int i = 0; i < size; i++) {                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());            }            int sectionsSize = dis.readInt();            for (int n = 0; n < sectionsSize; n++) {                SectionType sectionType = SectionType.valueOf(dis.readUTF());                switch (sectionType) {                    case PERSONAL, OBJECTIVE -> resume.addSection(sectionType, new TextSection(dis.readUTF()));                    case ACHIEVEMENTS, QUALIFICATIONS -> {                        List<String> strings = new ArrayList<>();                        int stringsSize = dis.readInt();                        for (int i = 0; i < stringsSize; i++) {                            strings.add(dis.readUTF());                        }                        resume.addSection(sectionType, new ListSection(strings));                    }                    case EDUCATION, EXPERIENCE -> {                        List<Organization> organizationList = new ArrayList<>();                        int orgListSize = dis.readInt();                        for (int i = 0; i < orgListSize; i++) {                            List<Organization.Position> positions = new ArrayList<>();                            String name = dis.readUTF();                            String url = dis.readUTF();                            String URL = url.equals("") ? null : url;                            int positionsSize = dis.readInt();                            for (int j = 0; j < positionsSize; j++) {                                LocalDate startLd = readLocalDate(dis);                                LocalDate finishLd = readLocalDate(dis);                                String title = dis.readUTF();                                String description = dis.readUTF();                                String desc = description.equals("") ? null : description;                                positions.add(new Organization.Position(startLd, finishLd, title, desc));                            }                            organizationList.add(new Organization(positions, new Link(name, URL)));                        }                        resume.addSection(sectionType, new OrganizationSection(organizationList));                    }                }            }            return resume;        }    }    @Override    public void doWrite(Resume resume, OutputStream os) throws IOException {        try (DataOutputStream dos = new DataOutputStream(os)) {            dos.writeUTF(resume.getUuid());            dos.writeUTF(resume.getFullName());            Map<ContactType, String> contacts = resume.getContacts();            writeWithException(contacts.entrySet(), dos, entry -> {                dos.writeUTF(entry.getKey().name());                dos.writeUTF(entry.getValue());            });            //section initialization            writeWithException(resume.getSections().entrySet(), dos, entry -> {                SectionType sectionType = entry.getKey();                AbstractSection section = entry.getValue();                dos.writeUTF(sectionType.name());                //TextSection initialization                switch (sectionType) {                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getInfo());                    case ACHIEVEMENTS, QUALIFICATIONS -> writeWithException(((ListSection) section).getElements(), dos, dos::writeUTF);                    case EDUCATION, EXPERIENCE -> writeWithException(((OrganizationSection) section).getOrganizations(), dos, org -> {                        Link link = new Link(org.getHomePage().getName(), org.getHomePage().getUrl());                        dos.writeUTF(link.getName());                        String url = link.getUrl() == null ? "" : link.getUrl();                        dos.writeUTF(url);                        writeWithException(org.getPositions(), dos, position -> {                            writeLocalDate(dos, position.getStartDate());                            writeLocalDate(dos, position.getEndDate());                            dos.writeUTF(position.getTitle());                            String desc = position.getDescription() == null ? "" : position.getDescription();                            dos.writeUTF(desc);                        });                    });                }            });        }    }    private LocalDate readLocalDate(DataInputStream dis) throws IOException {        return LocalDate.of(dis.readInt(), dis.readInt(), 1);    }    public void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {        dos.writeInt(localDate.getYear());        dos.writeInt(localDate.getMonthValue());    }    private interface Writer<T> {        void write(T t) throws IOException;    }    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, Writer<T> writer) throws IOException {        dos.writeInt(collection.size());        for (T element : collection) {            writer.write(element);        }    }}