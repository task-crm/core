package ru.sop.core.impl.model.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Информация об результате пагинации
 */
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingResult {
    public static final PagingResult EMPTY = PagingResult.builder()
        .currentPage(0L)
        .totalPageAmount(0L)
        .recordsOnPage(0L)
        .totalRecordsAmount(0L)
        .build();

    /**
     * Номер текущей страницы
     */
    Long currentPage;

    /**
     * Общее количество страниц
     */
    Long totalPageAmount;

    /**
     * Количество записей на странице
     */
    Long recordsOnPage;

    /**
     * Общее количество записей
     */
    Long totalRecordsAmount;
}
