package ru.sop.core.impl.model.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Объект для пагинации записей
 */
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paging {
    public static final Paging UNPAGED = Paging.builder()
        .currentPage(0L)
        .recordsOnPage(Long.MAX_VALUE)
        .build();

    public static final Paging ONE_ITEM = Paging.builder()
        .currentPage(0L)
        .recordsOnPage(1L)
        .build();

    /**
     * Номер запрашиваемой страницы
     */
    Long currentPage;

    /**
     * Количество записей на странице
     */
    Long recordsOnPage;

    public long getOffset() {
        Objects.requireNonNull(currentPage, "pageNum is mandatory");
        Objects.requireNonNull(recordsOnPage, "itemsPerPage is mandatory");
        return currentPage * recordsOnPage;
    }
}
