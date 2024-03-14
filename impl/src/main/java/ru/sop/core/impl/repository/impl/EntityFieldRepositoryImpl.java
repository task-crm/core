package ru.sop.core.impl.repository.impl;

import static core.tables.TEntityField.T_ENTITY_FIELD;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.bestclick.exceptionlib.config.ThreadLocalStorage;
import ru.sop.core.impl.helper.jooq.JsonbHelper;
import ru.sop.core.impl.model.EntityField;
import ru.sop.core.impl.repository.EntityFieldRepository;

@Repository
@RequiredArgsConstructor
public class EntityFieldRepositoryImpl implements EntityFieldRepository {

    private final DSLContext dsl;
    private final JsonbHelper jsonbHelper;

    @Override
    public EntityField create(EntityField field) {

        dsl.insertInto(T_ENTITY_FIELD)
            .set(T_ENTITY_FIELD.ID, field.getId())
            .set(T_ENTITY_FIELD.TENANT_ID, ThreadLocalStorage.getTenantId())
            .set(T_ENTITY_FIELD.ENTITY_ID, field.getEntityId())
            .set(T_ENTITY_FIELD.NAME, field.getName())
            .set(T_ENTITY_FIELD.LABEL, field.getLabel())
            .set(T_ENTITY_FIELD.DESCRIPTION, field.getDescription())
            .set(T_ENTITY_FIELD.TYPE, field.getType().)
            .set(T_ENTITY_FIELD.SETTINGS, jsonbHelper.toJsonB(field.getSettings()))
            .execute();
        return field;
    }

    @Override
    public EntityField update(EntityField field) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public EntityField getById(UUID id) {
        return null;
    }
}
