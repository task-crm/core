package ru.sop.core.api.dto.entity.field;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record EntityFieldCreateRq(
    @NotBlank
    String name,
    @NotBlank
    String label,
    String description,
    @NotBlank
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
