package ru.sop.core.impl.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
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
    STRING(FieldTypeConstants.STRING, "varchar", 0),
    TEXTAREA(FieldTypeConstants.TEXTAREA, "text", 1),
    NUMBER(FieldTypeConstants.NUMBER, "double precision", 2),
    BOOLEAN(FieldTypeConstants.BOOLEAN, "bool", 3),
    DATETIME(FieldTypeConstants.DATETIME, "time stamp with time zone", 4),
    DATE(FieldTypeConstants.DATE, "date", 5),
    DURATION(FieldTypeConstants.DURATION, "interval", 6),
    JSONB(FieldTypeConstants.JSONB, "jsonb", 7),
    ARRAY(FieldTypeConstants.ARRAY, "varchar[]", 8),
    EMAIL(FieldTypeConstants.EMAIL, "varchar", 9),
    PHONE(FieldTypeConstants.PHONE, "varchar", 10),
    /**
     * Односторонняя связь, source владелец
     */
    ONE_TO_ONE(FieldTypeConstants.ONE_TO_ONE, "ref", 11),
    /**
     * Односторонняя связь с каскадным удалением, source владелец
     */
    CASCADE_ONE_TO_ONE(FieldTypeConstants.CASCADE_ONE_TO_ONE, "ref", 12),
    /**
     * Двусторонняя связь работает в связке с MANY_TO_ONE! У двух связанных БО должно быть 2 поля:
     * 1) source с типом ONE_TO_MANY;
     * 2) destination с типом MANY_TO_ONE
     */
    ONE_TO_MANY(FieldTypeConstants.ONE_TO_MANY, "ref", 13),
    /**
     * Зависит от ONE_TO_MANY и не сохраняется в БД
     */
    MANY_TO_ONE(FieldTypeConstants.MANY_TO_ONE, "ref", 14),
    /**
     * Двусторонняя связь с каскадным удалением, работает в связке с CASCADE_MANY_TO_ONE! У двух связанных БО должно быть 2 поля:
     * 1) source с типом CASCADE_ONE_TO_MANY;
     * 2) destination с типом CASCADE_MANY_TO_ONE
     */
    CASCADE_ONE_TO_MANY(FieldTypeConstants.CASCADE_ONE_TO_MANY, "ref", 15),
    /**
     * Зависит от CASCADE_ONE_TO_MANY и не сохраняется в БД
     */
    CASCADE_MANY_TO_ONE(FieldTypeConstants.CASCADE_MANY_TO_ONE, "ref", 16),
    /**
     * TODO В разработке. Префикс VIRTUAL планируется использовать для полей, значение которого находится в другой
     *      таблице. Например, если чек-листы сделать отдельной таблицей с отдельным UI для асинхронного взаимодействия,
     *      то в БО будет поле checklists, которое будет заполнятся join-ом из t_checklist по id БО. Т.е. БО это
     *      source, а чек-листы destinations
     */
    VIRTUAL_ONE_TO_MANY(FieldTypeConstants.VIRTUAL_ONE_TO_MANY, "ref", 17);

    private static final Map<String, FieldTypeEnum> ENUM_BY_TYPE = Arrays.stream(values())
        .collect(Collectors.toMap(FieldTypeEnum::getType, Function.identity()));

    private static final Map<Integer, FieldTypeEnum> ENUM_BY_NUM = Arrays.stream(values())
        .collect(Collectors.toMap(FieldTypeEnum::getNumber, Function.identity()));

    private final String type;
    private final String dbType;
    private final int number;

    public static Optional<FieldTypeEnum> findFieldType(String type) {
        return Optional.ofNullable(ENUM_BY_TYPE.get(type));
    }

    @JsonCreator
    public static FieldTypeEnum getFieldType(Integer number) {
        return Optional.ofNullable(ENUM_BY_NUM.get(number))
            .orElseThrow(() -> new IllegalArgumentException(String.valueOf(number)));
    }

    @JsonValue
    public Integer getNumber() {
        return this.number;
    }

}
