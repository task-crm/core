package ru.sop.core.impl.service;

import java.util.List;
import java.util.UUID;
import ru.sop.core.impl.model.EntityField;

public interface EntityFieldService {
    List<EntityField> getAllByEntityId(UUID entityId);
}
