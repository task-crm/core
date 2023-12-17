package ru.sop.core.impl.service;

import java.util.Map;
import java.util.UUID;
import ru.sop.core.impl.model.EntityField;

public interface EntityFieldService {
    Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId);
}
