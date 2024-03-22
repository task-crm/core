package ru.sop.core.impl.repository.impl;

import static core.tables.TEntity.T_ENTITY;
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
import ru.sop.core.impl.mapper.record.EntityRecordMapper;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.model.entity.EntityRecord;
import ru.sop.core.impl.model.page.Filter;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;
import ru.sop.core.impl.model.page.Sort;
import ru.sop.core.impl.repository.EntityRepository;

@Repository
@RequiredArgsConstructor
public class EntityRepositoryImpl implements EntityRepository {
    private final DSLContext dsl;
    private final JsonbHelper jsonbHelper;
    private final EntityRecordMapper entityRecordMapper;
    private final ObjectMapper objectMapper;
    private final QueryBuildFactory queryBuildFactory;

    @Override
    @Nullable
    public Entity findById(UUID id) {
        val result = dsl.selectFrom(T_ENTITY).where(
            T_ENTITY.ID.eq(id)
                .and(T_ENTITY.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).fetchOneInto(EntityRecord.class);
        return entityRecordMapper.convert(result);
    }

    @Override
    public Entity getOne(UUID id) {
        val result = findById(id);
        if (result == null) {
            throw new BusinessException("entity.not_found");
        }
        return result;
    }

    @Override
    public Page<Entity> getPage(PageSelector selector) {
        val condition = getCondition(selector.getFilters());
        val orderBy = getOrderBy(selector.getSorts());
        val offset = selector.getPaging().getOffset();
        val recordsOnPage = selector.getPaging().getRecordsOnPage();
        val table = T_ENTITY.as(BASE_ALIAS);

        val res = queryBuildFactory.create(T_ENTITY, EntityRecord.class)
            .where(condition.and(table.TENANT_ID.eq(ThreadLocalStorage.getTenantId())))
            .orderBy(orderBy)
            .fetchPage(offset, recordsOnPage);

        return Page.<Entity>builder()
            .data(entityRecordMapper.convert(res.getData()))
            .paging(res.getPaging())
            .build();
    }

    @Override
    public void create(Entity entity) {
        dsl.insertInto(T_ENTITY)
            .set(T_ENTITY.ID, entity.getId())
            .set(T_ENTITY.TENANT_ID, ThreadLocalStorage.getTenantId())
            .set(T_ENTITY.NAME, entity.getName())
            .set(T_ENTITY.LABEL, entity.getLabel())
            .set(T_ENTITY.DESCRIPTION, entity.getDescription())
            .set(T_ENTITY.ICON, entity.getIcon())
            .set(T_ENTITY.COLOR, entity.getColor())
            .set(T_ENTITY.SETTINGS, jsonbHelper.toJsonB(entity.getSettings()))
            .set(T_ENTITY.SYSTEM, entity.isSystem())
            .set(T_ENTITY.ARCHIVED, entity.isArchived())

            .set(T_ENTITY.CREATED_DATE, entity.getAudit().getCreatedDate())
            .set(T_ENTITY.CREATE_BY, entity.getAudit().getCreatedBy())
            .set(T_ENTITY.UPDATE_DATE, entity.getAudit().getUpdatedDate())
            .set(T_ENTITY.UPDATED_BY, entity.getAudit().getUpdatedBy())
            .execute();
    }

    @Override
    public void update(Entity entity) {
        dsl.update(T_ENTITY)
            .set(T_ENTITY.LABEL, entity.getLabel())
            .set(T_ENTITY.DESCRIPTION, entity.getDescription())
            .set(T_ENTITY.ICON, entity.getIcon())
            .set(T_ENTITY.COLOR, entity.getColor())
            .set(T_ENTITY.SETTINGS, jsonbHelper.toJsonB(entity.getSettings()))
            .set(T_ENTITY.SYSTEM, entity.isSystem())
            .set(T_ENTITY.ARCHIVED, entity.isArchived())

            .set(T_ENTITY.UPDATE_DATE, entity.getAudit().getUpdatedDate())
            .set(T_ENTITY.UPDATED_BY, entity.getAudit().getUpdatedBy())

            .where(
                T_ENTITY.ID.eq(entity.getId())
                    .and(T_ENTITY.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
            )
            .execute();
    }

    @Override
    public void deleteOne(UUID id) {
        dsl.delete(T_ENTITY).where(
            T_ENTITY.ID.eq(id)
                .and(T_ENTITY.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).execute();
    }

    private Condition getCondition(Set<Filter> filters) {
        return new SpecificationBuilder(T_ENTITY.as(BASE_ALIAS), List.of(), objectMapper)
            .build(filters);
    }

    private List<OrderField<?>> getOrderBy(Set<Sort> sorts) {
        return new SortBuilder(T_ENTITY.as(BASE_ALIAS), sorts).build();
    }
}
