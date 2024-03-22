package ru.sop.core.impl.repository;

import java.util.UUID;
import org.checkerframework.checker.nullness.qual.Nullable;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;

public interface EntityFieldRepository {

    @Nullable
    EntityField findOne(UUID id);

    EntityField getOne(UUID id);

    Page<EntityField> getPage(PageSelector selector);

    void create(EntityField field);

    void update(EntityField field);

    void deleteOne(UUID id);
}
