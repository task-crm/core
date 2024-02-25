package ru.sop.core.impl.enums;

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
    /**
     * Односторонняя связь, source владелец
     */
    ONE_TO_ONE(FieldTypeConstants.ONE_TO_ONE, "ref"),
    /**
     * Односторонняя связь с каскадным удалением, source владелец
     */
    CASCADE_ONE_TO_ONE(FieldTypeConstants.CASCADE_ONE_TO_ONE, "ref"),
    /**
     * Двусторонняя связь работает в связке с MANY_TO_ONE! У двух связанных БО должно быть 2 поля:
     * 1) source с типом ONE_TO_MANY;
     * 2) destination с типом MANY_TO_ONE
     */
    ONE_TO_MANY(FieldTypeConstants.ONE_TO_MANY, "ref"),
    /**
     * Зависит от ONE_TO_MANY и не сохраняется в БД
     */
    MANY_TO_ONE(FieldTypeConstants.MANY_TO_ONE, "ref"),
    /**
     * Двусторонняя связь с каскадным удалением, работает в связке с CASCADE_MANY_TO_ONE! У двух связанных БО должно быть 2 поля:
     * 1) source с типом CASCADE_ONE_TO_MANY;
     * 2) destination с типом CASCADE_MANY_TO_ONE
     */
    CASCADE_ONE_TO_MANY(FieldTypeConstants.CASCADE_ONE_TO_MANY, "ref"),
    /**
     * Зависит от CASCADE_ONE_TO_MANY и не сохраняется в БД
     */
    CASCADE_MANY_TO_ONE(FieldTypeConstants.CASCADE_MANY_TO_ONE, "ref"),
    /**
     * TODO В разработке. Префикс VIRTUAL планируется использовать для полей, значение которого находится в другой
     *      таблице. Например, если чек-листы сделать отдельной таблицей с отдельным UI для асинхронного взаимодействия,
     *      то в БО будет поле checklists, которое будет заполнятся join-ом из t_checklist по id БО. Т.е. БО это
     *      source, а чек-листы destinations
     */
    VIRTUAL_ONE_TO_MANY(FieldTypeConstants.VIRTUAL_ONE_TO_MANY, "ref");

    private static final Map<String, FieldTypeEnum> ENUM_BY_TYPE = Arrays.stream(values())
        .collect(Collectors.toMap(FieldTypeEnum::getType, Function.identity()));

    private final String type;
    private final String dbType;

    public static Optional<FieldTypeEnum> findByType(String type) {
        return Optional.ofNullable(ENUM_BY_TYPE.get(type));
    }

}
