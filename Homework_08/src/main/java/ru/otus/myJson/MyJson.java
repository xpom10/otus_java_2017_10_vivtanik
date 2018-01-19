package ru.otus.myJson;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class MyJson {

    private final static String OBJECT = "OBJECT";

    public String serializeJSON(Object jsonObject) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        if (jsonObject == null) {
            return builder.append(("null,")).toString();
        } else if (serializeFieldValue(jsonObject).equals(OBJECT)) {
            builder.append("{");
            Field[]fields = jsonObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (builder.toString().endsWith("}")) {
                    builder.append(",\"").append(field.getName()).append("\":");
                } else {
                    builder.append("\"").append(field.getName()).append("\":");
                }
                builder.append(serializeJSON(field.get(jsonObject)));
            }
            if (builder.toString().endsWith(",")) {
                builder.deleteCharAt(builder.length() - 1).append("}");
            } else builder.append("}");
        } else builder.append(serializeFieldValue(jsonObject));

        return builder.toString();
    }

    private String serializeFieldValue(Object object) throws IllegalAccessException {
        Class  cl = object.getClass();
        if (cl.equals(String.class) || cl.equals(char.class) || cl.equals(Character.class))
            return serializeString(object);
        if (cl.isPrimitive() || Number.class.isInstance(object) || Boolean.class.isInstance(object))
            return serializeNumberOrBoolean(object);
        if (cl.isArray())
            return serializeArray(object);
        if (Collection.class.isInstance(object))
            return serializeCollection(object);
        else return OBJECT;
    }

    private String serializeString(Object jsonObject) {
        return "\"" + jsonObject.toString() + "\",";
    }

    private String serializeNumberOrBoolean(Object jsonObject) {
        return jsonObject.toString() + ",";
    }

    private String serializeArray(Object jsonObject) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < Array.getLength(jsonObject); i++) {
            builder.append(serializeFieldValue(Array.get(jsonObject, i)));
        }
        builder.deleteCharAt(builder.length() - 1).append("],");
        return builder.toString();
    }

    private String serializeCollection(Object jsonObject) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        List list = (List) (jsonObject);
        for (Object element : list) {
            builder.append(serializeFieldValue(element));
        }
        return builder.deleteCharAt(builder.length() - 1).append("],").toString();
    }
}
