package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private LocalDate startDate;
    private LocalDate endDate;
    private Link homePage;
    private String title;
    private String description;

    public Organization(LocalDate startDate, LocalDate endDate, Link homePage, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.homePage = homePage;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!homePage.equals(that.homePage)) return false;
        if (!title.equals(that.title)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + homePage.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", homePage=" + homePage +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
