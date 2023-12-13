package ru.sop.core.impl.service;

import java.util.UUID;
import ru.sop.core.impl.model.Entity;

public interface EntityService {
    Entity getById(UUID entityId);
}
