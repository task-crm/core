package ru.sop.core.api.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityRs(
    UUID id,
    String name,
    String label,
    String description,
    String icon,
    String color,
    EntitySettingsDto settings,

    boolean system,
    boolean archived
) {
}
