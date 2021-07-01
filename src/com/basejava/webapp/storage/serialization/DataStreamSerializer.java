package com.basejava.webapp.storage.serialization;import com.basejava.webapp.model.*;import java.io.*;import java.time.LocalDate;import java.util.ArrayList;import java.util.List;import java.util.Map;public class DataStreamSerializer implements SerializationStrategy {    @Override    public Resume doRead(InputStream is) throws IOException {        try (DataInputStream dis = new DataInputStream(is)) {            String uuid = dis.readUTF();            String fullName = dis.readUTF();            Resume resume = new Resume(uuid, fullName);            int size = dis.readInt();            for (int i = 0; i < size; i++) {                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());            }            int sectionsSize = dis.readInt();            for (int n = 0; n < sectionsSize; n++) {                SectionType sectionType = SectionType.valueOf(dis.readUTF());                switch (sectionType) {                    case PERSONAL, OBJECTIVE -> resume.addSection(sectionType, new TextSection(dis.readUTF()));                    case ACHIEVEMENTS, QUALIFICATIONS -> {                        List<String> strings = new ArrayList<>();                        int stringsSize = dis.readInt();                        for (int i = 0; i < stringsSize; i++) {                            strings.add(dis.readUTF());                        }                        resume.addSection(sectionType, new ListSection(strings));                    }                    case EDUCATION, EXPERIENCE -> {                        List<Organization.Position> position = new ArrayList<>();                        Link link = new Link();                        int orgListSize = dis.readInt();                        for (int i = 0; i < orgListSize; i++) {                            link = new Link(dis.readUTF(), dis.readUTF());                            int positionsSize = dis.readInt();                            for (int j = 0; j < positionsSize; j++) {                                position.add(new Organization.Position(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()));                            }                        }                        resume.addSection(sectionType, new OrganizationSection(new Organization(position, link)));                    }                }            }            return resume;        }    }    @Override    public void doWrite(Resume resume, OutputStream os) throws IOException {        try (DataOutputStream dos = new DataOutputStream(os)) {            dos.writeUTF(resume.getUuid());            dos.writeUTF(resume.getFullName());            Map<ContactType, String> contacts = resume.getContacts();            dos.writeInt(contacts.size());            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {                dos.writeUTF(entry.getKey().name());                dos.writeUTF(entry.getValue());            }            //section initialization            Map<SectionType, AbstractSection> sections = resume.getSections();            int sectionsSize = sections.entrySet().size();            dos.writeInt(sectionsSize);            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {                SectionType sectionType = entry.getKey();                AbstractSection section = entry.getValue();                dos.writeUTF(sectionType.name());                //TextSection initialization                switch (sectionType) {                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getInfo());                    //ListSection initialization                    case ACHIEVEMENTS, QUALIFICATIONS -> {                        List<String> listEls = (((ListSection) section).getElements());                        dos.writeInt(listEls.size());                        for (String element : listEls) {                            dos.writeUTF(element);                        }                    }                    //OrganizationSection initialization                    case EDUCATION, EXPERIENCE -> {                        List<Organization> orgList = ((OrganizationSection) section).getOrganizations();                        dos.writeInt(orgList.size());                        for (Organization org : orgList) {                            dos.writeUTF(org.getHomePage().getName());                            dos.writeUTF(org.getHomePage().getUrl());                            dos.writeInt(org.getPositions().size());                            for (Organization.Position position : org.getPositions()) {                                writeLocalDate(dos, position.getStartDate());                                writeLocalDate(dos, position.getEndDate());                                dos.writeUTF(position.getTitle());                                dos.writeUTF(position.getDescription());                            }                        }                    }                    default -> throw new IllegalStateException();                }            }        }    }    private LocalDate readLocalDate(DataInputStream dis) throws IOException {        return LocalDate.of(dis.readInt(), dis.readInt(), 1);    }    public void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {        dos.writeInt(localDate.getYear());        dos.writeInt(localDate.getMonthValue());    }}