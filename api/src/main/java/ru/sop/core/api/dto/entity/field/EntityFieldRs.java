package ru.sop.core.api.dto.entity.field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityFieldRs(
    UUID id,
    UUID entityId,
    String name,
    String label,
    String description,
    String type,
    EntityFieldSettingsDto settings,
    boolean system,
    boolean hiddenFromUser,
    boolean hiddenFromUi,

    @Schema(title = "Поле архивировано пользователем. Такие Поля исключаются из работы как будто их не существует, но должны" +
        " поддерживаться в актуальном состоянии (например, их должны обрабатывать миграции), так как пользователь может" +
        " вернуть Поле из архива.")
    boolean archived,

    @Schema(title = "Поле только для чтения. Его невозможно изменить")
    boolean readOnly,

    @Schema(title = "Поле обязательно должно быть инициализировано. Без этого поля не может существовать БО")
    boolean required
) {
}
