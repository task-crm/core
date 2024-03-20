package ru.sop.core.impl.jooq;

import static ru.sop.core.impl.jooq.JooqParamUtils.first;
import static ru.sop.core.impl.jooq.JooqParamUtils.getListWithAtLeastTwoParams;
import static ru.sop.core.impl.jooq.JooqParamUtils.toParams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.impl.DSL;
import ru.sop.core.impl.model.data.Filter;

@RequiredArgsConstructor
public class SpecificationBuilder {
    private final Table<?> table;
    private final List<String> ignoredFields;
    private final ObjectMapper objectMapper;

    private static <T> Condition getConditionByOperationType(Filter filter, Field<T> field) {
        return switch (filter.getOperation()) {
            case GT -> field.gt(first(filter.getValues()).cast(field));
            case GTE -> field.ge(first(filter.getValues()).cast(field));
            case LT -> field.lt(first(filter.getValues()).cast(field));
            case LTE -> field.le(first(filter.getValues()).cast(field));
            case EQUAL -> field.equal(first(filter.getValues()).cast(field));
            case NOT_EQUAL -> field.ne(first(filter.getValues()).cast(field));
            case CONTAINS -> field.likeIgnoreCase("%" + first(filter.getValues()).getValue() + "%");
            case BETWEEN -> {
                val params = getListWithAtLeastTwoParams(filter.getValues(), "between");
                yield field.between(params.get(0).cast(field), params.get(1).cast(field));
            }
            case IN -> field.in(
                toParams(filter.getValues()).stream()
                    .map(p -> p.cast(field))
                    .toList()
            );
            case NOT_IN -> field.notIn(toParams(filter.getValues()).stream()
                .map(p -> p.cast(field))
                .toList());
            case IS_EMPTY -> DSL.or(
                field.isNull(),
                field.eq(first(StringUtils.EMPTY).cast(field))
            );
            case IS_NOT_EMPTY -> DSL.and(
                field.isNotNull(),
                field.ne(first(StringUtils.EMPTY).cast(field))
            );
            case IS_NOT_NULL -> field.isNotNull();
            case IS_NULL -> field.isNull();
            case IS_NULL_OR_NOT_EQUAL -> DSL.or(
                field.isNull(),
                field.ne(first(filter.getValues()).cast(field))
            );
            case STARTS_WITH -> field.startsWith(first(filter.getValues()).cast(field));
            case ENDS_WITH -> field.endsWith(first(filter.getValues()).cast(field));
            case TRUE -> field.isTrue();
            case FALSE -> field.isFalse();
            default -> throw new IllegalStateException("Unexpected operation: " + filter.getOperation());
        };
    }

    public Condition build(Set<Filter> filters) {
        if (CollectionUtils.isEmpty(filters)) {
            return DSL.trueCondition();
        }

        return DSL.and(
            filters.stream()
                .map(this::build)
                .toList()
        );
    }

    public Condition build(Filter filter) {
        Objects.requireNonNull(filter.getOperation(), "operation is mandatory");
        if (filter.getOperation().isNested()) {
            return switch (filter.getOperation()) {
                case OR -> getOrCondition(filter);
                case AND -> getAndCondition(filter);
                case NOT -> getNotCondition(filter);
                default -> throw new IllegalArgumentException("Unexpected operation: " + filter.getOperation());
            };
        } else {
            return getCondition(filter);
        }
    }

    private Condition getOrCondition(Filter filter) {
        return DSL.or(
            filter.getValues().stream()
                .map(this::convertToFilter)
                .map(this::build)
                .toList()
        );
    }

    private Condition getAndCondition(Filter filter) {
        return DSL.and(
            filter.getValues().stream()
                .map(this::convertToFilter)
                .map(this::build)
                .toList()
        );
    }

    private Condition getNotCondition(Filter filter) {
        Objects.requireNonNull(filter.getValues(), "value is mandatory");
        val filterObject = filter.getValues().get(0);
        Objects.requireNonNull(filterObject, "value is mandatory");
        return DSL.not(build(convertToFilter(filterObject)));
    }

    private Filter convertToFilter(Object filter) {
        return objectMapper.convertValue(filter, Filter.class);
    }

    private Condition getCondition(Filter filter) {
        Objects.requireNonNull(filter.getField(), "field is mandatory");
        if (CollectionUtils.emptyIfNull(ignoredFields).contains(filter.getField())) {
            return DSL.trueCondition();
        }
        val dbFieldName = toSnakeCase(filter.getField());
        val field = table.field(dbFieldName);
        if (field == null) {
            return DSL.trueCondition();
        }
        val type = field.getDataType().getType();
        return getConditionByOperationType(filter, field.cast(type));
    }

    private String toSnakeCase(String field) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, StringUtils.trim(field));
    }
}
