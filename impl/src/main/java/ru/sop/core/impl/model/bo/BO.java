package ru.sop.core.impl.model.bo;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.model.Audit;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.model.entity.field.EntityField;

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
    /**
     * Объект создана системой для её обслуживания. Не может быть удален/архивирован
     */
    boolean system;
    @Builder.Default
    Map<String, Object> data = Collections.emptyMap();
    @Builder.Default
    Map<String, Object> references = Collections.emptyMap();
    Audit audit;

    public BO of(Map<String, Object> data, Map<String, Object> references) {
        return this.toBuilder()
            .data(data)
            .references(references)
            .build();
    }
}
