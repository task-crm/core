package ru.sop.core.api.dto.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Schema(title = "Информация об результате пагинации")
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingResultRs {
    public static final PagingResultRs EMPTY = PagingResultRs.builder()
        .currentPage(0L)
        .totalPageAmount(0L)
        .recordsOnPage(0L)
        .totalRecordsAmount(0L)
        .build();

    @Schema(title = "Номер ткущей страницы")
    Long currentPage;

    @Schema(title = "Общее количество страниц")
    Long totalPageAmount;

    @Schema(title = "Количество записей на странице")
    Long recordsOnPage;

    @Schema(title = "Общее количество записей")
    Long totalRecordsAmount;
}
