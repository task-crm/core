package ru.sop.core.impl.service.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.service.EntityService;

@Service
public class EntityServiceImpl implements EntityService {

    @Override
    public Entity create(Entity field) {
        return null;
    }

    @Override
    public Entity update(Entity field) {
        return null;
    }

    @Override
    public void deleteOne(UUID entityId) {

    }

    @Override
    public Entity getOne(UUID entityId) {
        return null;
    }
}
