package com.basejava.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Скайп"),
    EMAIL("Почта"),
    LINKEDIN("ЛинкедИн"),
    GITHUB("ГитХаб"),
    STACKOVERFLOW("СтакОверфлоу"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
