package ru.sop.core.impl.repository;

import java.util.UUID;
import ru.sop.core.impl.model.entity.Entity;

public interface EntityRepository {
    void create(Entity entity);

    void update(Entity entity);

    void deleteById(UUID id);

    Entity getById(UUID id);
}
