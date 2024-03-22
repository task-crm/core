package ru.sop.core.impl.repository.impl;

import static core.tables.TBusinessObject.T_BUSINESS_OBJECT;
import static ru.sop.core.impl.jooq.JooqConstants.BASE_ALIAS;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.OrderField;
import org.springframework.stereotype.Repository;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;
import ru.bestclick.exceptionlib.exception.BusinessException;
import ru.sop.core.impl.helper.jooq.JsonbHelper;
import ru.sop.core.impl.jooq.QueryBuildFactory;
import ru.sop.core.impl.jooq.SortBuilder;
import ru.sop.core.impl.jooq.SpecificationBuilder;
import ru.sop.core.impl.mapper.record.BORecordMapper;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.model.bo.BORecord;
import ru.sop.core.impl.model.page.Filter;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;
import ru.sop.core.impl.model.page.Sort;
import ru.sop.core.impl.repository.BORepository;

@Repository
@RequiredArgsConstructor
public class BORepositoryImpl implements BORepository {
    private final QueryBuildFactory queryBuildFactory;
    private final BORecordMapper boRecordMapper;
    private final ObjectMapper objectMapper;
    private final JsonbHelper jsonbHelper;
    private final DSLContext dsl;

    @Override
    @Nullable
    public BO findOne(UUID id) {
        val result = dsl.selectFrom(T_BUSINESS_OBJECT).where(
            T_BUSINESS_OBJECT.ID.eq(id)
                .and(T_BUSINESS_OBJECT.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).fetchOneInto(BORecord.class);
        return boRecordMapper.convert(result);
    }

    @Override
    public BO getOne(UUID id) {
        val result = findOne(id);
        if (result == null) {
            throw new BusinessException("bo.not-found");
        }
        return result;
    }

    @Override
    public Page<BO> getPage(PageSelector selector) {
        val condition = getCondition(selector.getFilters());
        val orderBy = getOrderBy(selector.getSorts());
        val offset = selector.getPaging().getOffset();
        val recordsOnPage = selector.getPaging().getRecordsOnPage();
        val table = T_BUSINESS_OBJECT.as(BASE_ALIAS);

        val result = queryBuildFactory.create(T_BUSINESS_OBJECT, BORecord.class)
            .where(condition.and(table.ID.eq(ThreadLocalStorage.getTenantId())))
            .orderBy(orderBy)
            .fetchPage(offset, recordsOnPage);

        return Page.<BO>builder()
            .data(boRecordMapper.convert(result.getData()))
            .paging(result.getPaging())
            .build();
    }

    @Override
    public void create(BO bo) {
        dsl.insertInto(T_BUSINESS_OBJECT)
            .set(T_BUSINESS_OBJECT.ID, bo.getId())
            .set(T_BUSINESS_OBJECT.TENANT_ID, ThreadLocalStorage.getTenantId())
            .set(T_BUSINESS_OBJECT.NAME, bo.getName())
            .set(T_BUSINESS_OBJECT.ENTITY_ID, bo.getEntityId())
            .set(T_BUSINESS_OBJECT.SYSTEM, bo.isSystem())
            .set(T_BUSINESS_OBJECT.DATA, jsonbHelper.toJsonB(bo.getData()))

            .set(T_BUSINESS_OBJECT.CREATED_DATE, bo.getAudit().getCreatedDate())
            .set(T_BUSINESS_OBJECT.CREATE_BY, bo.getAudit().getCreatedBy())
            .set(T_BUSINESS_OBJECT.UPDATE_DATE, bo.getAudit().getUpdatedDate())
            .set(T_BUSINESS_OBJECT.UPDATED_BY, bo.getAudit().getUpdatedBy())
            .execute();
    }

    @Override
    public void update(BO bo) {
        dsl.update(T_BUSINESS_OBJECT)
            .set(T_BUSINESS_OBJECT.NAME, bo.getName())
            .set(T_BUSINESS_OBJECT.SYSTEM, bo.isSystem())
            .set(T_BUSINESS_OBJECT.DATA, jsonbHelper.toJsonB(bo.getData()))

            .set(T_BUSINESS_OBJECT.UPDATE_DATE, bo.getAudit().getUpdatedDate())
            .set(T_BUSINESS_OBJECT.UPDATED_BY, bo.getAudit().getUpdatedBy())

            .where(
                T_BUSINESS_OBJECT.ID.eq(bo.getId())
                    .and(T_BUSINESS_OBJECT.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
            )
            .execute();
    }

    @Override
    public void deleteOne(UUID id) {
        dsl.delete(T_BUSINESS_OBJECT).where(
            T_BUSINESS_OBJECT.ID.eq(id)
                .and(T_BUSINESS_OBJECT.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).execute();
    }

    private Condition getCondition(Set<Filter> filters) {
        return new SpecificationBuilder(T_BUSINESS_OBJECT.as(BASE_ALIAS), List.of(), objectMapper)
            .build(filters);
    }

    private List<OrderField<?>> getOrderBy(Set<Sort> sorts) {
        return new SortBuilder(T_BUSINESS_OBJECT.as(BASE_ALIAS), sorts).build();
    }

}
