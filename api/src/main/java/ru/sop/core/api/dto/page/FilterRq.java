package ru.sop.core.api.dto.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@JsonPropertyOrder(value = {
    "field",
    "secondField",
    "operation",
    "values"
})
@Schema(title = "Объект для фильтрации значений")
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterRq {
    private static final String FIELD_NAME_REGEX = "^[a-zA-Z0-9.$:^_]+";

    @Schema(title = "Название поля")
    @NotBlank
    String field;

    @Schema(title = "Пока нет реализации. Название поля для операций с двумя полями(OVERLAPS)")
    String secondField;

    @Schema(title = "Тип операции")
    @NotNull
    Operation operation;

    @Schema(title = "Значение поля")
    @NotNull
    @Valid
    @Size.List(@Size(min = 1, max = 30))
    List<@NotNull Object> values;
}
