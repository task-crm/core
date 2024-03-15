package ru.sop.core.api.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Schema(title = "Информация об пагинации")
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingRs {
    public static final PagingRs EMPTY = PagingRs.builder()
        .currentPage(0L)
        .totalPageAmount(0L)
        .recordOnPage(0L)
        .totalRecordsAmount(0L)
        .build();

    @Schema(title = "Номер ткущей страницы")
    Long currentPage;

    @Schema(title = "Общее количество страниц")
    Long totalPageAmount;

    @Schema(title = "Количество записей на странице")
    Long recordOnPage;

    @Schema(title = "Общее количество записей")
    Long totalRecordsAmount;
}
