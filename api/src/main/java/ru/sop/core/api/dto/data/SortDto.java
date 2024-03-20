package ru.sop.core.api.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Schema(title = "Объект для сортировки значений")
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortDto {

    @Schema(title = "Название поля")
    @NotBlank
    @Size(max = 128)
    String field;


    @Schema(title = "Тип сортировки")
    @Builder.Default
    SortType sortType = SortType.ASC;
}
