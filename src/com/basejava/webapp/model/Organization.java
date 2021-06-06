package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private List<OrganizationList>organizationLists = new ArrayList<>();
    private Link homePage;

    public Organization(List<OrganizationList> organizationLists, Link homePage) {
        this.organizationLists = organizationLists;
        this.homePage = homePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!Objects.equals(organizationLists, that.organizationLists))
            return false;
        return Objects.equals(homePage, that.homePage);
    }

    @Override
    public int hashCode() {
        int result = organizationLists != null ? organizationLists.hashCode() : 0;
        result = 31 * result + (homePage != null ? homePage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationLists=" + organizationLists +
                ", homePage=" + homePage +
                '}';
    }
}
