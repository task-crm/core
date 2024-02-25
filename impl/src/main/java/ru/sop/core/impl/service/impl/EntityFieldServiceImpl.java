package ru.sop.core.impl.service.impl;

import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.model.EntityField;
import ru.sop.core.impl.service.EntityFieldService;

@Service
public class EntityFieldServiceImpl implements EntityFieldService {
    @Override
    public Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId) {
        return null;
    }
}
