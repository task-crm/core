package ru.sop.core.impl.repository.impl;

import static core.tables.TEntityField.T_ENTITY_FIELD;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;
import ru.sop.core.impl.helper.jooq.JsonbHelper;
import ru.sop.core.impl.model.data.Data;
import ru.sop.core.impl.model.data.DataResult;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.repository.EntityFieldRepository;

@Repository
@RequiredArgsConstructor
public class EntityFieldRepositoryImpl implements EntityFieldRepository {

    private final DSLContext dsl;
    private final JsonbHelper jsonbHelper;

    @Override
    public void create(EntityField field) {
        dsl.insertInto(T_ENTITY_FIELD)
            .set(T_ENTITY_FIELD.ID, field.getId())
            .set(T_ENTITY_FIELD.TENANT_ID, ThreadLocalStorage.getTenantId())
            .set(T_ENTITY_FIELD.ENTITY_ID, field.getEntityId())
            .set(T_ENTITY_FIELD.NAME, field.getName())
            .set(T_ENTITY_FIELD.LABEL, field.getLabel())
            .set(T_ENTITY_FIELD.DESCRIPTION, field.getDescription())
            .set(T_ENTITY_FIELD.TYPE, field.getType().getNumber().shortValue())
            .set(T_ENTITY_FIELD.SETTINGS, jsonbHelper.toJsonB(field.getSettings()))
            .set(T_ENTITY_FIELD.SYSTEM, field.isSystem())
            .set(T_ENTITY_FIELD.HIDDEN_FROM_USER, field.isHiddenFromUser())
            .set(T_ENTITY_FIELD.HIDDEN_FROM_UI, field.isHiddenFromUi())
            .set(T_ENTITY_FIELD.ARCHIVED, field.isArchived())
            .set(T_ENTITY_FIELD.READ_ONLY, field.isReadOnly())
            .set(T_ENTITY_FIELD.REQUIRED, field.isRequired())

            .set(T_ENTITY_FIELD.CREATED_DATE, field.getAudit().getCreatedDate())
            .set(T_ENTITY_FIELD.CREATE_BY, field.getAudit().getCreatedBy())
            .set(T_ENTITY_FIELD.UPDATE_DATE, field.getAudit().getUpdateDate())
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

            .set(T_ENTITY_FIELD.UPDATE_DATE, field.getAudit().getUpdateDate())
            .set(T_ENTITY_FIELD.UPDATED_BY, field.getAudit().getUpdatedBy())

            .where(
                T_ENTITY_FIELD.ID.eq(field.getId())
                    .and(T_ENTITY_FIELD.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
            )
            .execute();
    }

    @Override
    public void deleteById(UUID id) {
        dsl.delete(T_ENTITY_FIELD).where(
            T_ENTITY_FIELD.ID.eq(id)
                .and(T_ENTITY_FIELD.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).execute();
    }

    @Override
    public EntityField getById(UUID id) {
        return dsl.selectFrom(T_ENTITY_FIELD).where(
            T_ENTITY_FIELD.ID.eq(id)
                .and(T_ENTITY_FIELD.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).fetchOneInto(EntityField.class);
    }

    @Override
    public DataResult getData(UUID entityId, Data data) {
        return null;
    }
}
