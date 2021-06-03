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
        Objects.requireNonNull(homePage, "homePage must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(description, "description must not be null");
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

        if (!Objects.equals(startDate, that.startDate)) return false;
        if (!Objects.equals(endDate, that.endDate)) return false;
        if (!Objects.equals(homePage, that.homePage)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (homePage != null ? homePage.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
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
