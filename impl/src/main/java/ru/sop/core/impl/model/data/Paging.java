package ru.sop.core.impl.model.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Объект для пагинации значений
 */
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paging {
    public static final Paging UNPAGED = Paging.builder()
        .pageNum(0L)
        .itemsPerPage(Long.MAX_VALUE)
        .build();

    public static final Paging ONE_ITEM = Paging.builder()
        .pageNum(0L)
        .itemsPerPage(1L)
        .build();

    /**
     * Номер запрашиваемой страницы
     */
    Long pageNum;

    /**
     * Количество элементов на странице
     */
    Long itemsPerPage;
}
