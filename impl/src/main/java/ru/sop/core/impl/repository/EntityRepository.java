package ru.sop.core.impl.repository;

import java.util.UUID;
import org.checkerframework.checker.nullness.qual.Nullable;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;

public interface EntityRepository {

    @Nullable
    Entity findById(UUID id);

    Entity getOne(UUID id);

    Page<Entity> getPage(PageSelector selector);

    void create(Entity entity);

    void update(Entity entity);

    void deleteOne(UUID id);
}
