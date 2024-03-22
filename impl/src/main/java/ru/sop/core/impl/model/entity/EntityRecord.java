package ru.sop.core.impl.model.entity;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.entity.field.EntityField;

/**
 * Сущность ограничивает и настраивает бизнес объект {@link BO}. У каждой сущности есть набор полей {@link EntityField}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class EntityRecord {
    private UUID id;
    private String name;
    private String label;
    private String description;
    private String icon;
    private String color;
    private EntitySettings settings;
    /**
     * Сущность создана системой для её обслуживания. Не может быть удалена/архивирована
     */
    private boolean system;
    /**
     * Сущность заархивирована
     */
    private boolean archived;

    private OffsetDateTime createdDate;
    private UUID createdBy;
    private OffsetDateTime updatedDate;
    private UUID updatedBy;
}
