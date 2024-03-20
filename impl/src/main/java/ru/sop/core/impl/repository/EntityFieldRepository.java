package ru.sop.core.impl.repository;

import java.util.UUID;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jooq.Condition;
import ru.sop.core.impl.model.data.Page;
import ru.sop.core.impl.model.entity.field.EntityField;

public interface EntityFieldRepository {

    void create(EntityField field);

    void update(EntityField field);

    void deleteById(UUID id);

    @Nullable
    EntityField findOne(UUID id);

    EntityField getOne(UUID id);

    Page<EntityField> getPage(Condition condition);
}
