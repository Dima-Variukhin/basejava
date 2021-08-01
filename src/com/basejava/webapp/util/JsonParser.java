package com.basejava.webapp.util;

import com.basejava.webapp.model.Section;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;

public class JsonParser {
    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter<>())
            .create();

    public static <T> T doRead(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void doWrite(T object, Writer writer) {
        GSON.toJson(object, writer);
    }

    public static String doWrite(Section section, Class<Section> sectionClass) {
        return GSON.toJson(section, sectionClass);
    }

    public static Section doRead(String content, Class<Section> sectionClass) {
        return GSON.fromJson(content, sectionClass);
    }
}
