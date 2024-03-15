package ru.sop.core.api.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Schema(title = "Общий запрос на получение данных")
@JsonPropertyOrder(value = {
    "filters",
    "sort",
    "paging"
})
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataRq {

    @Schema(title = "Объекты для фильтрации")
    @Valid
    @Builder.Default
    Set<FilterDto> filters = Collections.emptySet();

    @Schema(title = "Объекты для сортировки")
    @Valid
    @Builder.Default
    Set<SortDto> sorts = Collections.emptySet();

    @Schema(title = "Объекты для пагинации")
    @Valid
    @NotNull
    PagingRq paging;
}
