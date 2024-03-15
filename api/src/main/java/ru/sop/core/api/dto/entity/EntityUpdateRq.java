package ru.sop.core.api.dto.entity;

import java.util.UUID;

public record EntityUpdateRq(
    UUID id,
    String label,
    String description,
    String icon,
    String color,
    EntitySettingsDto settings,
    boolean system,
    boolean archived
) {
}
