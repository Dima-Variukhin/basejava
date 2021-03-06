package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static Resume createResume(String fullName, String uuid) {
        Resume resume = new Resume(uuid, fullName);
        List<String> listAchievements = new ArrayList<>();
        List<String> listQualifications = new ArrayList<>();
        Organization.Position courseraOrg = new Organization.Position(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), "Coursera", "\"Functional Programming Principles in Scala\" by Martin Odersky");
        Organization.Position luxoftOrg = new Organization.Position(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), "Luxoft", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        Organization.Position siementsOrg = new Organization.Position(LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), "Siemens AG", "3 месяца обучения мобильным IN сетям (Берлин)");
        Organization.Position alcatelOrg = new Organization.Position(LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), "Alcatel", null);

        List<Organization.Position> courseraList = new ArrayList<>();
        courseraList.add(courseraOrg);
        List<Organization.Position> luxoftList = new ArrayList<>();
        luxoftList.add(luxoftOrg);
        List<Organization.Position> siemensList = new ArrayList<>();
        luxoftList.add(siementsOrg);
        List<Organization.Position> alcatelList = new ArrayList<>();
        luxoftList.add(alcatelOrg);

        Organization coursera = new Organization(courseraList, new Link("Coursera", "https://www.coursera.org/learn/progfun1"));
        Organization luxoft = new Organization(luxoftList, new Link("Luxoft", "https://www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html"));
        Organization siemens = new Organization(siemensList, new Link("Siemens AG", "https://new.siemens.com/ru/ru.html"));
        Organization alcatel = new Organization(alcatelList, new Link("Alcatel", null));


        listAchievements.add(" 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven." +
                " Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listAchievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio," +
                " DuoSecurity, Google Authenticator, Jira, Zendesk.");
        listAchievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS," +
                " LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей," +
                " интеграция CIFS/SMB java сервера.");
        listAchievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT)," +
                " Commet, HTML5, Highstock для алгоритмического трейдинга.");
        listAchievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish)." +
                " Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        listAchievements.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        listQualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        listQualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        listQualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        listQualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        listQualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        listQualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        listQualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT)," +
                " Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        listQualifications.add("Python: Django.");
        listQualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        listQualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        listQualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX," +
                " SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        listQualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        listQualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        listQualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        listQualifications.add("Родной русский, английский \"upper intermediate\"");

        resume.getSections().put(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        resume.getSections().put(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.getSections().put(SectionType.ACHIEVEMENTS, new ListSection(listAchievements));
        resume.getSections().put(SectionType.QUALIFICATIONS, new ListSection(listQualifications));
        resume.getSections().put(SectionType.EXPERIENCE,new OrganizationSection(coursera,luxoft,siemens,alcatel));
        resume.getSections().put(SectionType.EDUCATION, new OrganizationSection(coursera,luxoft,siemens,alcatel));

        resume.getContacts().put(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.getContacts().put(ContactType.GITHUB, "https://github.com/gkislin");
        resume.getContacts().put(ContactType.HOMEPAGE, "http://gkislin.ru/");
        resume.getContacts().put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin/");
        resume.getContacts().put(ContactType.PHONE, "+7(921) 855-0482");
        resume.getContacts().put(ContactType.SKYPE, "grigory.kislin");
        resume.getContacts().put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");

        return resume;
    }
}
