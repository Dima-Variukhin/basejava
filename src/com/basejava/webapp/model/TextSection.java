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

        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return info.hashCode();
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "info='" + info + '\'' +
                '}';
    }
}
