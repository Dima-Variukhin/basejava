package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    protected List<String> elements;

    public ListSection(List<String> sections) {
        Objects.requireNonNull(sections, "sections must not be null");
        this.elements = sections;
    }

    public List<String> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return elements.equals(that.elements);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "elements=" + elements +
                '}';
    }
}
