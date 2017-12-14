package ru.otus.myJson;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class MyJson {


    public String serializeJSON(Object jsonObject) throws IllegalAccessException {
        if (jsonObject == null) {
            return "null";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("{");
        Field[] objectFields = jsonObject.getClass().getDeclaredFields();
        for (Field field : objectFields) {
            field.setAccessible(true);
            switch (serializeFieldValue(field.get(jsonObject))) {
                case "STRING": builder.append(serializeString(field,jsonObject));
                    break;
                case "NUMBER": builder.append(serializeNumber(field,jsonObject));
                    break;
                case "ARRAY": builder.append(serializeArray(field,jsonObject));
                    break;
                case "COLLECTION": builder.append(serializeCollection(field,jsonObject));
                    break;
                case "OBJECT": builder.append(serializeObject(field,jsonObject));
                    break;
                default: break;
            }
        }

        builder.deleteCharAt(builder.length() - 1).append("}");
        return builder.toString();
    }

    private String serializeFieldValue(Object field) {

        Class  cl = field.getClass();
        if (cl.equals(String.class) || cl.equals(char.class) || cl.equals(Character.class))
            return "STRING";
        if (cl.isPrimitive() || Number.class.isInstance(field))
            return "NUMBER";
        if (cl.isArray())
            return "ARRAY";
        if (Collection.class.isInstance(field))
            return "COLLECTION";
        else return "OBJECT";
    }

    private String serializeString(Field field, Object jsonObject) throws IllegalAccessException {
        return "\"" + field.getName() + "\":\"" + field.get(jsonObject) + "\",";
    }

    private String serializeNumber(Field field, Object jsonObject) throws IllegalAccessException {
        return "\"" + field.getName() + "\":" + field.get(jsonObject) + ",";
    }

    private String serializeArray(Field field, Object jsonObject) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append("\"").append(field.getName()).append("\":[");
        int a = Array.getLength(field.get(jsonObject));
        for (int i = 0; i < a; i++) {
            Object element = Array.get(field.get(jsonObject), i);
            switch (serializeFieldValue(Array.get(field.get(jsonObject), i))) {
                case "STRING": builder.append("\"").append(element).append("\",");
                    break;
                case "NUMBER": builder.append(element).append(",");
                    break;
                case "ARRAY": builder.append(serializeArray(field,jsonObject));
                    break;
                case "COLLECTION":
                    break;
                case "OBJECT": builder.append(serializeObject(field,jsonObject));
                    break;
                default: break;
            }
        }
        builder.deleteCharAt(builder.length() - 1).append("],");
        return builder.toString();
    }

    private String serializeCollection(Field field, Object jsonObject) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append("\"").append(field.getName()).append("\":[");
        List list = (List) field.get(jsonObject);
        for (Object element : list) {
            switch (serializeFieldValue(element)) {
                case "STRING":
                    builder.append("\"").append(element).append("\",");
                    break;
                case "NUMBER":
                    builder.append(element).append(",");
                    break;
                case "ARRAY":
                    builder.append(serializeArray(field, jsonObject));
                    break;
                case "COLLECTION":
                    builder.append(serializeCollection(field,jsonObject));
                    break;
                case "OBJECT":
                    builder.append(serializeObject(field, jsonObject));
                    break;
                default:
                    break;
            }
        }
        builder.deleteCharAt(builder.length() - 1).append("],");
        return builder.toString();
    }

    private String serializeObject(Field field, Object jsonObject) throws IllegalAccessException {
        String builder = "\"" + field.getName() + "\":" +
                serializeJSON(field.get(jsonObject)) +
                ",";
        return builder;
    }
}
