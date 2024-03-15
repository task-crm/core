package ru.sop.core.api.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Schema(title = "Объект для пагинации значений")
@JsonPropertyOrder(value = {
    "pageNum",
    "itemsPerPage"
})
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingRq {
    public static final PagingRq UNPAGED = PagingRq.builder()
        .pageNum(0L)
        .itemsPerPage(Long.MAX_VALUE)
        .build();

    public static final PagingRq ONE_ITEM = PagingRq.builder()
        .pageNum(0L)
        .itemsPerPage(1L)
        .build();


    @Schema(title = "Номер запрашиваемой страницы")
    @NotNull
    @PositiveOrZero
    Long pageNum;

    @Schema(title = "Количество элементов на странице")
    @NotNull
    @PositiveOrZero
    Long itemsPerPage;
}
