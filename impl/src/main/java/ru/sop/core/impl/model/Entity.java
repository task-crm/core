package ru.sop.core.impl.model;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Сущность ограничивает и настраивает бизнес объект {@link BO}. У каждой сущности есть набор полей {@link EntityField}
 */
@Value
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
public class Entity {
    UUID id;
    String name;
    String label;
    String description;
    String icon;
    String color;
    EntitySettings settings;
    /**
     * Сущность создана системой для её обслуживания. Не может быть удалена/архивирована
     */
    boolean system;
    /**
     * Сущность заархивирована
     */
    boolean archived;

    Audit audit;

}
