package com.basejava.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    protected List<String> organizations;

    public OrganizationSection(String... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<String> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    public List<String> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations != null ? organizations.hashCode() : 0;
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}

