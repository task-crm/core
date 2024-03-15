package ru.sop.core.impl.repository;

import java.util.UUID;
import ru.sop.core.impl.model.data.Data;
import ru.sop.core.impl.model.data.DataResult;
import ru.sop.core.impl.model.entity.field.EntityField;

public interface EntityFieldRepository {
    void create(EntityField field);

    void update(EntityField field);

    void deleteById(UUID id);

    EntityField getById(UUID id);

    DataResult getData(UUID entityId, Data data);
}
