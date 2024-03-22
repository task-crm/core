package ru.sop.core.impl.service;

import java.util.Map;
import java.util.UUID;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldGetCmd;
import ru.sop.core.impl.model.page.Page;

public interface EntityFieldService {

    Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId);

    EntityField getOne(UUID entityId, UUID fieldId);

    Page<EntityField> getPage(EntityFieldGetCmd cmd);

    EntityField create(Metadata metadata, EntityField field);

    EntityField update(Metadata metadata, EntityField field);

    void deleteOne(UUID entityId, UUID fieldId);
}
