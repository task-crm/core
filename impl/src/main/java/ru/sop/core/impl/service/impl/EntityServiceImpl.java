package ru.sop.core.impl.service.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.model.Entity;
import ru.sop.core.impl.service.EntityService;

@Service
public class EntityServiceImpl implements EntityService {
    @Override
    public Entity getById(UUID entityId) {
        return null;
    }
}
