package ru.sop.core.impl.model.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collections;
import java.util.Set;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Объект для выборки данных постранично {@link Page}
 */
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageSelector {
    public static final PageSelector EMPTY = PageSelector.builder().build();

    /**
     * Объекты для фильтрации
     */
    @Builder.Default
    Set<Filter> filters = Collections.emptySet();

    /**
     * Объекты для сортировки
     */
    @Builder.Default
    Set<Sort> sorts = Collections.emptySet();

    /**
     * Объекты для пагинации
     */
    @Builder.Default
    Paging paging = Paging.ONE_ITEM;
}
