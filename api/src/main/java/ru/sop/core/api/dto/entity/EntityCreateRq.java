package ru.sop.core.api.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityCreateRq(

    @NotBlank
    String name,

    @NotBlank
    String label,
    String description,
    String icon,
    String color,
    EntitySettingsDto settings,
    @Schema(title = "Сущность создана системой для её обслуживания. Не может быть удалена/архивирована")
    boolean system,
    boolean archived
) {
}
