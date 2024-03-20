package ru.sop.core.impl.model.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Информация об пагинации
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
        .recordOnPage(0L)
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
    Long recordOnPage;

    /**
     * Общее количество записей
     */
    Long totalRecordsAmount;
}
