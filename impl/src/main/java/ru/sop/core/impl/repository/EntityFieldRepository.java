package ru.sop.core.impl.repository;

import java.util.UUID;
import ru.sop.core.impl.model.EntityField;

public interface EntityFieldRepository {
    EntityField create(EntityField field);

    EntityField update(EntityField field);

    void deleteById(UUID id);

    EntityField getById(UUID id);
}
