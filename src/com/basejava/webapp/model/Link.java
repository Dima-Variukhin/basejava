package com.basejava.webapp.model;

import java.util.Objects;

public class Link {
 private String name;
 private String URL;

    public Link(String name, String URL) {
        this.name = name;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!Objects.equals(name, link.name)) return false;
        return Objects.equals(URL, link.URL);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (URL != null ? URL.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", URL='" + URL + '\'' +
                '}';
    }
}
