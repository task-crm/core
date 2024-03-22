package ru.sop.core.impl.jooq;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.collections4.CollectionUtils;
import org.jooq.OrderField;
import org.jooq.SortField;
import org.jooq.Table;
import ru.bestclick.exceptionlib.exception.BusinessException;
import ru.sop.core.api.dto.page.SortType;
import ru.sop.core.impl.model.page.Sort;

@RequiredArgsConstructor
public class SortBuilder {
    private final Table<?> table;
    private final Set<Sort> sorts;

    public List<OrderField<?>> build() {
        if (CollectionUtils.isEmpty(sorts)) {
            return Collections.emptyList();
        }
        return sorts.stream()
            .sorted(Comparator.comparing(Sort::getOrder, Comparator.nullsFirst(Comparator.naturalOrder())))
            .map(this::getSortField)
            .collect(Collectors.toList());
    }

    private SortField<?> getSortField(Sort sort) {
        val field = table.field(sort.getField());
        if (field == null) {
            throw new BusinessException("sort.builder.field.not_found");
        }
        return sort.getSortType() == SortType.ASC
            ? field.asc()
            : field.desc();
    }
}
