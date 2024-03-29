package ru.sop.core.impl.model.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.sop.core.api.dto.page.SortType;

/**
 * Объект для сортировки записей
 */
@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sort {

    /**
     * Название поля
     */
    String field;

    /**
     * Тип сортировки
     */
    @Builder.Default
    SortType sortType = SortType.ASC;

    /**
     * Порядок сортировки по колонкам
     */
    Double order;
}
