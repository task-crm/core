package ru.sop.core.impl.model;

import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sop.core.impl.enums.FieldTypeEnum;

/**
 * BO - это бизнес объект, на его основе построено взаимодействие в системе, он зависит от сущности {@link Entity},
 * которая его настраивает. У каждой сущности есть набор полей {@link  EntityField} по факту эти поля описывают BO
 * (имя поля, валидации, тип поля {@link FieldTypeEnum}). Имена этих полей являются именами полей в data(данные)
 * и references(ссылки)
 */
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
public class BO {
    UUID id;
    String name;
    /**
     * идентификатор сущности, к которой принадлежит этот бизнес объект
     */
    UUID entityId;
    boolean system;
    @Builder.Default
    Map<String, Object> data = Map.of();
    @Builder.Default
    Map<String, Object> references = Map.of();
    Audit audit;

    public BO of(Map<String, Object> data, Map<String, Object> references) {
        return this.toBuilder()
            .data(data)
            .references(references)
            .build();
    }
}
