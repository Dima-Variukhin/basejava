package com.basejava.webapp.storage.serialization;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            for (int i = 0; i < dis.readInt(); i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            return resume;
        }
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            //section initialization
            Map<SectionType, AbstractSection> sections = resume.getSections();
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                AbstractSection value = entry.getValue();
                dos.writeUTF(sectionType.name());
                //TextSection initialization
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) value).getInfo());
                        break;
                    //ListSection initialization
                    case ACHIEVEMENTS:
                    case QUALIFICATIONS:
                        Collection collection = ((ListSection) value).getElements();
                        dos.writeInt(collection.size());
                        for (Object element : collection) {
                            dos.writeUTF((String) element);
                        }
                        break;
                    //OrganizationSection initialization
                    case EDUCATION:
                    case EXPERIENCE:
                        Collection collection2 = ((OrganizationSection) value).getOrganizations();
                        dos.writeInt(collection2.size());
                        //Link initialization
                        for (Object element : collection2) {
                            dos.writeUTF(((Link) element).getName());
                            dos.writeUTF(((Link) element).getUrl());
//Trying to initialize Organization, but too much troubles happened.
                            Collection collection3 = (Collection) element;
                            dos.writeInt(collection3.size());
                            for (Object item : collection3) {
                                dos.writeUTF(((Organization.Position) item).getDescription());
                                dos.writeUTF(((Organization.Position) item).getTitle());

                                Collection collection4 = (Collection) item;
                                dos.writeInt(collection4.size());
                                for (Object localDate : collection4) {
                                    LocalDate endDate = ((Organization.Position) localDate).getEndDate();
                                    LocalDate startDate = ((Organization.Position) localDate).getStartDate();
                                    dos.writeInt(startDate.getYear());
                                    dos.writeInt(startDate.getMonthValue());
                                    dos.writeInt(endDate.getYear());
                                    dos.writeInt(endDate.getMonthValue());
                                }
                            }

                        }
                }
            }
        }
    }
}

