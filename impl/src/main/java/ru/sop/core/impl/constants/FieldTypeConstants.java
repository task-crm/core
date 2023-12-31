package ru.sop.core.impl.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class FieldTypeConstants {
    public static final String STRING = "STRING";
    public static final String TEXTAREA = "TEXTAREA";
    public static final String NUMBER = "NUMBER";
    public static final String BOOLEAN = "BOOLEAN";
    public static final String DATETIME = "DATETIME";
    public static final String DATE = "DATE";
    public static final String DURATION = "DURATION";
    public static final String JSONB = "JSONB";
    public static final String ARRAY = "ARRAY";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final String ONE_TO_ONE = "ONE_TO_ONE";
    public static final String CASCADE_ONE_TO_ONE = "CASCADE_ONE_TO_ONE";
    public static final String ONE_TO_MANY = "ONE_TO_MANY";
    public static final String MANY_TO_ONE = "MANY_TO_ONE";
    public static final String CASCADE_ONE_TO_MANY = "CASCADE_ONE_TO_MANY";
    public static final String CASCADE_MANY_TO_ONE = "CASCADE_MANY_TO_ONE";
    public static final String VIRTUAL_ONE_TO_MANY = "VIRTUAL_ONE_TO_MANY";
}
