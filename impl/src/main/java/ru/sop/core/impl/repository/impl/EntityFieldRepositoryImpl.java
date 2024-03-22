package ru.sop.core.impl.repository.impl;

import static core.tables.TEntityField.T_ENTITY_FIELD;
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
import ru.sop.core.impl.mapper.record.EntityFieldRecordMapper;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldRecord;
import ru.sop.core.impl.model.page.Filter;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;
import ru.sop.core.impl.model.page.Sort;
import ru.sop.core.impl.repository.EntityFieldRepository;

@Repository
@RequiredArgsConstructor
public class EntityFieldRepositoryImpl implements EntityFieldRepository {
    private final EntityFieldRecordMapper entityFieldRecordMapper;
    private final QueryBuildFactory queryBuildFactory;
    private final ObjectMapper objectMapper;
    private final JsonbHelper jsonbHelper;
    private final DSLContext dsl;

    @Override
    @Nullable
    public EntityField findOne(UUID id) {
        val result = dsl.selectFrom(T_ENTITY_FIELD).where(
            T_ENTITY_FIELD.ID.eq(id)
                .and(T_ENTITY_FIELD.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).fetchOneInto(EntityFieldRecord.class);
        return entityFieldRecordMapper.convert(result);
    }

    @Override
    public EntityField getOne(UUID id) {
        val result = findOne(id);
        if (result == null) {
            throw new BusinessException("entity.field.not-found");
        }
        return result;
    }

    @Override
    public Page<EntityField> getPage(PageSelector selector) {
        val condition = getCondition(selector.getFilters());
        val orderBy = getOrderBy(selector.getSorts());
        val offset = selector.getPaging().getOffset();
        val recordsOnPage = selector.getPaging().getRecordsOnPage();
        val table = T_ENTITY_FIELD.as(BASE_ALIAS);

        val res = queryBuildFactory.create(T_ENTITY_FIELD, EntityFieldRecord.class)
            .where(condition.and(table.TENANT_ID.eq(ThreadLocalStorage.getTenantId())))
            .orderBy(orderBy)
            .fetchPage(offset, recordsOnPage);

        return Page.<EntityField>builder()
            .data(entityFieldRecordMapper.convert(res.getData()))
            .paging(res.getPaging())
            .build();
    }

    @Override
    public void create(EntityField field) {
        dsl.insertInto(T_ENTITY_FIELD)
            .set(T_ENTITY_FIELD.ID, field.getId())
            .set(T_ENTITY_FIELD.TENANT_ID, ThreadLocalStorage.getTenantId())
            .set(T_ENTITY_FIELD.ENTITY_ID, field.getEntityId())
            .set(T_ENTITY_FIELD.NAME, field.getName())
            .set(T_ENTITY_FIELD.LABEL, field.getLabel())
            .set(T_ENTITY_FIELD.DESCRIPTION, field.getDescription())
            .set(T_ENTITY_FIELD.TYPE, field.getType().name())
            .set(T_ENTITY_FIELD.SETTINGS, jsonbHelper.toJsonB(field.getSettings()))
            .set(T_ENTITY_FIELD.SYSTEM, field.isSystem())
            .set(T_ENTITY_FIELD.HIDDEN_FROM_USER, field.isHiddenFromUser())
            .set(T_ENTITY_FIELD.HIDDEN_FROM_UI, field.isHiddenFromUi())
            .set(T_ENTITY_FIELD.ARCHIVED, field.isArchived())
            .set(T_ENTITY_FIELD.READ_ONLY, field.isReadOnly())
            .set(T_ENTITY_FIELD.REQUIRED, field.isRequired())

            .set(T_ENTITY_FIELD.CREATED_DATE, field.getAudit().getCreatedDate())
            .set(T_ENTITY_FIELD.CREATE_BY, field.getAudit().getCreatedBy())
            .set(T_ENTITY_FIELD.UPDATE_DATE, field.getAudit().getUpdatedDate())
            .set(T_ENTITY_FIELD.UPDATED_BY, field.getAudit().getUpdatedBy())
            .execute();
    }

    @Override
    public void update(EntityField field) {
        dsl.update(T_ENTITY_FIELD)
            .set(T_ENTITY_FIELD.LABEL, field.getLabel())
            .set(T_ENTITY_FIELD.DESCRIPTION, field.getDescription())
            .set(T_ENTITY_FIELD.SETTINGS, jsonbHelper.toJsonB(field.getSettings()))
            .set(T_ENTITY_FIELD.SYSTEM, field.isSystem())
            .set(T_ENTITY_FIELD.HIDDEN_FROM_USER, field.isHiddenFromUser())
            .set(T_ENTITY_FIELD.HIDDEN_FROM_UI, field.isHiddenFromUi())
            .set(T_ENTITY_FIELD.ARCHIVED, field.isArchived())
            .set(T_ENTITY_FIELD.READ_ONLY, field.isReadOnly())
            .set(T_ENTITY_FIELD.REQUIRED, field.isRequired())

            .set(T_ENTITY_FIELD.UPDATE_DATE, field.getAudit().getUpdatedDate())
            .set(T_ENTITY_FIELD.UPDATED_BY, field.getAudit().getUpdatedBy())

            .where(
                T_ENTITY_FIELD.ID.eq(field.getId())
                    .and(T_ENTITY_FIELD.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
            )
            .execute();
    }

    @Override
    public void deleteOne(UUID id) {
        dsl.delete(T_ENTITY_FIELD).where(
            T_ENTITY_FIELD.ID.eq(id)
                .and(T_ENTITY_FIELD.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).execute();
    }

    private Condition getCondition(Set<Filter> filters) {
        return new SpecificationBuilder(T_ENTITY_FIELD.as(BASE_ALIAS), List.of(), objectMapper)
            .build(filters);
    }

    private List<OrderField<?>> getOrderBy(Set<Sort> sorts) {
        return new SortBuilder(T_ENTITY_FIELD.as(BASE_ALIAS), sorts).build();
    }
}
