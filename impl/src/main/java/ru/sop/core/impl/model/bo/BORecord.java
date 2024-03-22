package ru.sop.core.impl.model.bo;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.model.entity.field.EntityField;

/**
 * BO - это бизнес объект, на его основе построено взаимодействие в системе, он зависит от сущности {@link Entity},
 * которая его настраивает. У каждой сущности есть набор полей {@link  EntityField} по факту эти поля описывают BO
 * (имя поля, валидации, тип поля {@link FieldTypeEnum}). Имена этих полей являются именами полей в data(данные)
 * и references(ссылки)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
public class BORecord {
    private UUID id;
    private String name;
    /**
     * идентификатор сущности, к которой принадлежит этот бизнес объект
     * private
     */
    private UUID entityId;
    /**
     * Объект создана системой для её обслуживания. Не может быть удален/архивирован
     */
    private boolean system;
    @Builder.Default
    private Map<String, Object> data = Collections.emptyMap();
    @Builder.Default
    private Map<String, Object> references = Collections.emptyMap();
    private OffsetDateTime createdDate;
    private UUID createdBy;
    private OffsetDateTime updatedDate;
    private UUID updatedBy;
}
