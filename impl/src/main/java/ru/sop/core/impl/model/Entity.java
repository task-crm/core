package ru.sop.core.impl.model;

import java.util.UUID;

/**
 * Сущность ограничивает и настраивает бизнес объект {@link BO}. У каждой сущности есть набор полей {@link EntityField}
 */
public record Entity(
    UUID id,
    String name,
    String description,
    String icon,
    String color,
    EntitySettings settings,
    /**
     * Сущность создана системой(разработчиком)
     */
    boolean system,
    /**
     * Сущность заархивирована
     */
    boolean archived,
    
    Audit audit
) {
}
