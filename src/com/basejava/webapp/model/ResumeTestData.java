package com.basejava.webapp.model;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Dima");
        resume.getSections().put(SectionType.PERSONAL, new AbstractSection());
        resume.getSections().put(SectionType.OBJECTIVE, new AbstractSection());
        resume.getSections().put(SectionType.ACHIEVEMENTS, new AbstractSection());
        resume.getSections().put(SectionType.QUALIFICATIONS, new AbstractSection());
        resume.getSections().put(SectionType.EDUCATION, new AbstractSection());

        resume.getContacts().put(ContactType.EMAIL, "ee");
        resume.getContacts().put(ContactType.GITHUB, "gh");
        resume.getContacts().put(ContactType.HOMEPAGE, "hp");
        resume.getContacts().put(ContactType.LINKEDIN, "lin");
        resume.getContacts().put(ContactType.PHONE, "ph");
        resume.getContacts().put(ContactType.SKYPE, "SK");
        resume.getContacts().put(ContactType.STACKOVERFLOW, "ST");

        System.out.println(resume.getContacts());
        System.out.println(resume.getSections());
    }
}
