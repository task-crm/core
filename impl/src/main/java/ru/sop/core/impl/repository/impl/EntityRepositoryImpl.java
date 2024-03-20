package ru.sop.core.impl.repository.impl;

import static core.tables.TEntity.T_ENTITY;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;
import ru.sop.core.impl.helper.jooq.JsonbHelper;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.repository.EntityRepository;

@Repository
@RequiredArgsConstructor
public class EntityRepositoryImpl implements EntityRepository {
    private final DSLContext dsl;
    private final JsonbHelper jsonbHelper;

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
    public void deleteById(UUID id) {
        dsl.delete(T_ENTITY).where(
            T_ENTITY.ID.eq(id)
                .and(T_ENTITY.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).execute();
    }

    @Override
    public Entity getById(UUID id) {
        return dsl.selectFrom(T_ENTITY).where(
            T_ENTITY.ID.eq(id)
                .and(T_ENTITY.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).fetchOneInto(Entity.class);
    }
}
