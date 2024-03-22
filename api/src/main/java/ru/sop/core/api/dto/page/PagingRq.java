package ru.sop.core.api.dto.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Schema(title = "Объект для пагинации записей")
@JsonPropertyOrder(value = {
    "currentPage",
    "recordsOnPage"
})
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingRq {
    public static final PagingRq UNPAGED = PagingRq.builder()
        .currentPage(0L)
        .recordsOnPage(Long.MAX_VALUE)
        .build();

    public static final PagingRq ONE_ITEM = PagingRq.builder()
        .currentPage(0L)
        .recordsOnPage(1L)
        .build();


    @Schema(title = "Номер запрашиваемой страницы")
    @NotNull
    @PositiveOrZero
    Long currentPage;

    @Schema(title = "Количество записей на странице")
    @NotNull
    @PositiveOrZero
    Long recordsOnPage;
}
