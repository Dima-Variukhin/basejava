package com.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;

    private Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public void addSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;

        Boolean ok = Objects.equals(fullName, resume.fullName);
        Boolean ok2 = Objects.equals(contacts, resume.contacts);
        Boolean ok3 = Objects.equals(sections, resume.sections);

        Boolean ok4 = Objects.equals(sections.get(SectionType.ACHIEVEMENTS), resume.sections.get(SectionType.ACHIEVEMENTS));
        Boolean ok5 = Objects.equals(sections.get(SectionType.QUALIFICATIONS), resume.sections.get(SectionType.QUALIFICATIONS));

        Boolean ok6 = Objects.equals(sections.get(SectionType.EXPERIENCE), resume.sections.get(SectionType.EXPERIENCE));
        Boolean ok7 = Objects.equals(sections.get(SectionType.EDUCATION), resume.sections.get(SectionType.EDUCATION));

        Boolean ok8 = Objects.equals(sections.get(SectionType.PERSONAL), resume.sections.get(SectionType.PERSONAL));
        Boolean ok9 = Objects.equals(sections.get(SectionType.OBJECTIVE), resume.sections.get(SectionType.OBJECTIVE));
        System.out.println(ok);
        System.out.println(ok2);
        System.out.println(ok3);
        System.out.println(ok4);
        System.out.println(ok5);
        System.out.println(ok6);
        System.out.println(ok7);
        System.out.println(ok8);
        System.out.println(ok9);
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName) && Objects.equals(sections, resume.sections) && Objects.equals(contacts, resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, sections, contacts);
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
