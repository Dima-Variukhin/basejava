package com.basejava.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    protected String info;

    public TextSection(String info) {
        Objects.requireNonNull(info, "info must not be null");
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return info != null ? info.hashCode() : 0;
    }

    @Override
    public String toString() {
        return info;
    }
}
