package ru.sop.core.api.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Schema(title = "Общий ответ для получения данных")
@JsonPropertyOrder(value = {
    "data",
    "paging"
})
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataRs {

    @Schema(title = "Данные")
    Object data;

    @Schema(title = "Информация о пагинации")
    PagingRs paging;
}
