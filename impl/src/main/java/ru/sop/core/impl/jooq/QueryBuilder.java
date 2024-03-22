package ru.sop.core.impl.jooq;

import static core.Tables.T_BUSINESS_OBJECT;
import static ru.sop.core.impl.jooq.JooqConstants.BASE_ALIAS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.val;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.JoinType;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.SelectQuery;
import org.jooq.Table;
import org.jooq.TableRecord;
import org.jooq.impl.DSL;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PagingResult;

@RequiredArgsConstructor
public class QueryBuilder<R extends TableRecord<R>, T> {
    private final DSLContext context;
    private final UUID tenantId;
    private final List<Field<?>> select = new ArrayList<>();
    private final List<Condition> conditions = new ArrayList<>();
    private final List<Join> joins = new ArrayList<>();
    private final List<OrderField<?>> orderBy = new ArrayList<>();

    @Getter
    private final Table<R> baseTableAlias;

    @Getter
    private final Table<R> baseTable;

    @Getter
    @Nullable
    private final Class<T> objectClass;

    @Nullable
    private final RecordMapper<? super Record, T> mapper;

    public QueryBuilder<R, T> select(Field<?> field) {
        this.select.add(field);
        return this;
    }

    public QueryBuilder<R, T> select(List<Field<?>> fields) {
        this.select.addAll(fields);
        return this;
    }

    /**
     * Добавление к запросу left join
     *
     * @param joinedTable             название таблицы для join-a. Alias передавать нельзя!
     * @param joinedTableAlias        alias таблицы для join-a
     * @param baseTableAlias          alias для основной таблицы
     * @param baseTableJoinField      поле основной таблицы, на основании которого происходит join
     * @param joinedTableJoinField    поле таблицы join-a на основании которого происходит join
     * @param joinedTableSelectFields поля таблицы join-a которые попадут в результат выборки
     */
    public QueryBuilder<R, T> leftJoin(String joinedTable,
                                       String joinedTableAlias,
                                       String baseTableAlias,
                                       String baseTableJoinField,
                                       String joinedTableJoinField,
                                       List<Field<?>> joinedTableSelectFields) {
        val rightTable = DSL.table(joinedTable).as(joinedTableAlias);
        val condition = DSL.field(DSL.name(baseTableAlias, baseTableJoinField))
            .eq(DSL.field(DSL.name(joinedTableAlias, joinedTableJoinField)));
        val join = Join.builder()
            .joinedTable(rightTable)
            .condition(condition)
            .type(JoinType.LEFT_OUTER_JOIN)
            .joinedTableFields(joinedTableSelectFields)
            .build();
        this.joins.add(join);
        return this;
    }

    public QueryBuilder<R, T> leftJoin(String joinedTable,
                                       String joinedTableAlias,
                                       String baseTableJoinField,
                                       List<Field<?>> joinedTableSelectFields) {
        leftJoin(joinedTable, joinedTableAlias, BASE_ALIAS, baseTableJoinField, "id", joinedTableSelectFields);
        return this;
    }

    public QueryBuilder<R, T> leftJoinBo(String alias, String joinField) {
        List<Field<?>> fields = new ArrayList<>();
        fields.add(T_BUSINESS_OBJECT.as(alias).ID.as(String.format("%s.id", alias)));
        fields.add(T_BUSINESS_OBJECT.as(alias).NAME.as(String.format("%s.name", alias)));
        fields.add(T_BUSINESS_OBJECT.as(alias).ENTITY_ID.as(String.format("%s.entity_id", alias)));
        leftJoin(T_BUSINESS_OBJECT.getName(), alias, joinField, fields);
        return this;
    }

    public QueryBuilder<R, T> where(Condition condition) {
        conditions.add(condition);
        return this;
    }

    public QueryBuilder<R, T> where(Condition... conditions) {
        this.conditions.addAll(Arrays.asList(conditions));
        return this;
    }

    public QueryBuilder<R, T> orderBy(OrderField<?> orderField) {
        orderBy.add(orderField);
        return this;
    }

    public QueryBuilder<R, T> orderBy(Collection<? extends OrderField<?>> orderFields) {
        orderBy.addAll(orderFields);
        return this;
    }

    private SelectQuery<Record> createQueryWithoutSelect() {
        val query = context.selectQuery();
        query.addFrom(DSL.table(baseTable.getName()).as(BASE_ALIAS));
        joins.forEach(join -> query.addJoin(join.joinedTable, join.type, join.condition));
        query.addConditions(conditions);
        return query;
    }

    private SelectQuery<Record> createCountQuery() {
        val query = createQueryWithoutSelect();
        query.addSelect(DSL.count());
        return query;
    }

    private SelectQuery<Record> createQuery() {
        val query = createQueryWithoutSelect();
        query.addSelect(getBaseTableAlias().fields());
        joins.forEach(join -> query.addSelect(join.joinedTableFields));
        query.addSelect(select);
        query.addOrderBy(orderBy);
        return query;
    }

    @Nullable
    public T fetchOne() {
        return objectClass != null
            ? createQuery().fetchOneInto(objectClass)
            : createQuery().fetchOne(mapper);
    }

    public Optional<T> fetchOptional() {
        return objectClass != null
            ? createQuery().fetchOptionalInto(objectClass)
            : createQuery().fetchOptional(mapper);
    }

    public List<T> fetchAny() {
        return objectClass != null
            ? createQuery().fetchInto(objectClass)
            : createQuery().fetch(mapper);
    }

    public Page<T> fetchPage(long offset, long pageSize) {
        val results = fetchLimits(offset, pageSize);
        val totalNumRows = getTotalNumRows();
        val totalPages = totalNumRows > 0
            ? Math.round(Math.ceil((double) totalNumRows / pageSize))
            : 0L;
        return Page.<T>builder()
            .data(results)
            .paging(
                PagingResult.builder()
                    .totalRecordsAmount(totalNumRows)
                    .totalPageAmount(totalPages)
                    .recordsOnPage((long) results.size())
                    .currentPage(pageSize > 0 ? offset / pageSize : 0)
                    .build()
            )
            .build();
    }

    public List<T> fetchLimits(long offset, long limit) {
        val query = createQuery();
        query.addLimit(offset, limit);
        return objectClass != null
            ? query.fetchInto(objectClass)
            : query.fetch(mapper);
    }

    private Long getTotalNumRows() {
        val result = createCountQuery().fetchOne(DSL.count());
        if (result == null) {
            return 0L;
        }
        return result.longValue();
    }

    @Value
    @Builder(toBuilder = true)
    private static class Join {
        Table<?> joinedTable;
        Condition condition;
        JoinType type;
        List<Field<?>> joinedTableFields;
    }
}
