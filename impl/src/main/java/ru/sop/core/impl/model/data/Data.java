package ru.sop.core.impl.model.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collections;
import java.util.Set;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.sop.core.api.dto.data.SortDto;

@Value
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    /**
     * Объекты для фильтрации
     */
    @Builder.Default
    Set<Filter> filters = Collections.emptySet();

    /**
     * Объекты для сортировки
     */
    @Builder.Default
    Set<SortDto> sorts = Collections.emptySet();

    /**
     * Объекты для пагинации
     */
    @Builder.Default
    Paging paging = Paging.ONE_ITEM;
}
