package com.basejava.webapp.storage.serialization;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) value).getInfo());

                    //ListSection initialization
                    case ACHIEVEMENTS, QUALIFICATIONS -> {
                        List<String> list1 = new ArrayList<>(((ListSection) value).getElements());
                        dos.writeInt(list1.size());
                        for (String element : list1) {
                            dos.writeUTF(element);
                        }
                    }
                    //OrganizationSection initialization
                    case EDUCATION, EXPERIENCE -> {
                        Collection<String> collection1 = ((OrganizationSection) value).getOrganizations();
                        dos.writeInt(collection1.size());
                        //Link initialization
                        for (Object element : collection1) {
                            dos.writeUTF(((Link) element).getUrl());
                            dos.writeUTF(((Link) element).getUrl());
//Trying to initialize Organization, but too much troubles happened.
                            Collection<String> collection2 = ((OrganizationSection) value).getOrganizations();
                            dos.writeInt(collection2.size());
                            for (Object item : collection2) {
                                dos.writeUTF(((Organization.Position) item).getDescription());
                                dos.writeUTF(((Organization.Position) item).getTitle());
                                //Initialization DATE
                                List<String> list = ((OrganizationSection) value).getOrganizations();
                                for (Object o : list) {
                                    List<Organization.Position> list2 = ((Organization) o).getPositions();
                                    dos.writeInt(list2.size());
                                    for (Organization.Position localDate : list2) {
                                        LocalDate endDate = localDate.getEndDate();
                                        LocalDate startDate = localDate.getStartDate();
                                        writeLocalDate(dos, startDate);
                                        writeLocalDate(dos, endDate);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonthValue());
    }
}

