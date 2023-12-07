package ru.sop.core.impl.enums;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Системны поля бизнес объекта
 */
@Getter
@RequiredArgsConstructor
public enum BOFieldEnum {
    /*TODO
        1) Интернационализацию как делать? Токены или через таблицы?
        2) Подумать каким образом добавить SOURCE_AUTOMATION, OWNER, DIVISION, STATE атрибутами в таблице или ссылками
    */
    ID("id", "Идентификатор", UUID.class, FieldTypeEnum.STRING),
    NAME("name", "Наименование", String.class, FieldTypeEnum.STRING),
    DATA("data", "Данные", Map.class, FieldTypeEnum.JSONB),
    SYSTEM("system", "Системный объект", Boolean.class, FieldTypeEnum.BOOLEAN),
    CREATED_BY("createdBy", "Создал", OffsetDateTime.class, FieldTypeEnum.DATETIME),
    UPDATED_BY("updatedBy", "Обновил", OffsetDateTime.class, FieldTypeEnum.DATETIME),
    CREATED_DATE("createdDate", "Дата создания", OffsetDateTime.class, FieldTypeEnum.DATETIME),
    UPDATED_DATE("updateDate", "Дата обновления", OffsetDateTime.class, FieldTypeEnum.DATETIME);

    private static final Map<String, BOFieldEnum> ENUM_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(BOFieldEnum::getName, Function.identity()));

    private final String name;
    private final String description;
    private final Class<?> dataType;
    private final FieldTypeEnum fieldType;

    public static Optional<BOFieldEnum> findByName(String name) {
        return Optional.ofNullable(ENUM_BY_NAME.get(name));
    }
}
