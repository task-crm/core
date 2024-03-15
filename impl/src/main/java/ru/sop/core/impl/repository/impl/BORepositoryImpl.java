package ru.sop.core.impl.repository.impl;

import static core.tables.TBusinessObject.T_BUSINESS_OBJECT;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;
import ru.sop.core.impl.helper.jooq.JsonbHelper;
import ru.sop.core.impl.model.bo.BO;
import ru.sop.core.impl.repository.BORepository;

@Repository
@RequiredArgsConstructor
public class BORepositoryImpl implements BORepository {
    private final DSLContext dsl;
    private final JsonbHelper jsonbHelper;

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
            .set(T_BUSINESS_OBJECT.UPDATE_DATE, bo.getAudit().getUpdateDate())
            .set(T_BUSINESS_OBJECT.UPDATED_BY, bo.getAudit().getUpdatedBy())
            .execute();
    }

    @Override
    public void update(BO bo) {
        dsl.update(T_BUSINESS_OBJECT)
            .set(T_BUSINESS_OBJECT.NAME, bo.getName())
            .set(T_BUSINESS_OBJECT.SYSTEM, bo.isSystem())
            .set(T_BUSINESS_OBJECT.DATA, jsonbHelper.toJsonB(bo.getData()))

            .set(T_BUSINESS_OBJECT.UPDATE_DATE, bo.getAudit().getUpdateDate())
            .set(T_BUSINESS_OBJECT.UPDATED_BY, bo.getAudit().getUpdatedBy())

            .where(
                T_BUSINESS_OBJECT.ID.eq(bo.getId())
                    .and(T_BUSINESS_OBJECT.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
            )
            .execute();
    }

    @Override
    public void deleteById(UUID id) {
        dsl.delete(T_BUSINESS_OBJECT).where(
            T_BUSINESS_OBJECT.ID.eq(id)
                .and(T_BUSINESS_OBJECT.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).execute();
    }

    @Override
    public BO getById(UUID id) {
        return dsl.selectFrom(T_BUSINESS_OBJECT).where(
            T_BUSINESS_OBJECT.ID.eq(id)
                .and(T_BUSINESS_OBJECT.TENANT_ID.eq(ThreadLocalStorage.getTenantId()))
        ).fetchOneInto(BO.class);
    }
}
