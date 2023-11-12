package ru.sop.core.impl.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.sop.core.impl.constants.FieldTypeConstants;

/**
 * Типы полей для всех бизнес объектов
 */
@Getter
@RequiredArgsConstructor
public enum FieldTypeEnum {
    STRING(FieldTypeConstants.STRING, "varchar"),
    TEXTAREA(FieldTypeConstants.TEXTAREA, "text"),
    NUMBER(FieldTypeConstants.NUMBER, "double precision"),
    BOOLEAN(FieldTypeConstants.BOOLEAN, "bool"),
    DATETIME(FieldTypeConstants.DATETIME, "time stamp with time zone"),
    DATE(FieldTypeConstants.DATE, "date"),
    DURATION(FieldTypeConstants.DURATION, "interval"),
    JSONB(FieldTypeConstants.JSONB, "jsonb"),
    ARRAY(FieldTypeConstants.ARRAY, "varchar[]"),
    EMAIL(FieldTypeConstants.EMAIL, "varchar"),
    PHONE(FieldTypeConstants.PHONE, "varchar"),
    REFERENCE(FieldTypeConstants.REFERENCE, "uuid"),
    CASCADE_REFERENCE(FieldTypeConstants.CASCADE_REFERENCE, "uuid");

    private static final Map<String, FieldTypeEnum> ENUM_BY_TYPE = Arrays.stream(values())
        .collect(Collectors.toMap(FieldTypeEnum::getType, Function.identity()));

    private final String type;
    private final String dbType;

    public static FieldTypeEnum findFieldTypeByType(String type) {
        return ENUM_BY_TYPE.get(type);
    }

}
