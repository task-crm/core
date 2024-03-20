package ru.sop.core.impl.service;

import java.util.Map;
import java.util.UUID;
import ru.sop.core.impl.model.data.Page;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldGetCmd;

public interface EntityFieldService {
    Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId);

    EntityField create(EntityField field);

    EntityField update(EntityField field);

    void deleteOne(UUID entityId, UUID fieldId);

    EntityField getOne(UUID entityId, UUID fieldId);

    Page<EntityField> getPage(EntityFieldGetCmd cmd);
}
