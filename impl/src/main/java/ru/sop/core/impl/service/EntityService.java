package ru.sop.core.impl.service;

import java.util.UUID;
import ru.sop.core.impl.model.entity.Entity;

public interface EntityService {

    Entity create(Entity field);

    Entity update(Entity field);

    void deleteOne(UUID entityId);

    Entity getOne(UUID entityId);
}
