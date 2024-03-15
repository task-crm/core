package ru.sop.core.impl.service.impl;

import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.model.data.DataResult;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldGetCmd;
import ru.sop.core.impl.repository.EntityFieldRepository;
import ru.sop.core.impl.service.EntityFieldService;

@Service
@RequiredArgsConstructor
public class EntityFieldServiceImpl implements EntityFieldService {
    private final EntityFieldRepository entityFieldRepository;


    @Override
    public Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId) {
        return null;
    }

    @Override
    public EntityField create(EntityField field) {
        return null;
    }

    @Override
    public EntityField update(EntityField field) {
        return null;
    }

    @Override
    public void deleteOne(UUID entityId, UUID fieldId) {

    }

    @Override
    public EntityField getOne(UUID entityId, UUID fieldId) {
        return null;
    }

    @Override
    public DataResult getData(EntityFieldGetCmd cmd) {
        return entityFieldRepository.getData(cmd.getEntityId(), cmd.getData());
    }
}
