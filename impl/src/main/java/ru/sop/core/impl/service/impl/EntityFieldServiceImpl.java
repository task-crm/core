package ru.sop.core.impl.service.impl;

import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.helper.AuditHelper;
import ru.sop.core.impl.model.data.Page;
import ru.sop.core.impl.model.entity.field.EntityField;
import ru.sop.core.impl.model.entity.field.EntityFieldGetCmd;
import ru.sop.core.impl.repository.EntityFieldRepository;
import ru.sop.core.impl.service.EntityFieldService;
import ru.sop.core.impl.transitionallayer.EntityFieldTransitionalLayer;

@Service
@RequiredArgsConstructor
public class EntityFieldServiceImpl implements EntityFieldService {
    private final EntityFieldRepository entityFieldRepository;
    private final EntityFieldTransitionalLayer entityFieldTransitionalLayer;
    private final AuditHelper auditHelper;


    @Override
    public Map<String, EntityField> getFieldsByNameByEntityId(UUID entityId) {
        return Map.of();
    }

    @Override
    public EntityField create(EntityField field) {
        var richField = enrichBeforeCreate(field);
        entityFieldRepository.create(richField);
        return richField;
    }

    @Override
    public EntityField update(EntityField field) {
        var richField = enrichBeforeUpdate(field);
        entityFieldRepository.update(richField);
        return richField;
    }

    @Override
    public void deleteOne(UUID entityId, UUID fieldId) {
        entityFieldRepository.deleteById(fieldId);
    }

    @Override
    public EntityField getOne(UUID entityId, UUID fieldId) {
        return entityFieldRepository.getOne(fieldId);
    }

    @Override
    public Page<EntityField> getPage(EntityFieldGetCmd cmd) {
        return entityFieldTransitionalLayer.getPage(cmd);
    }

    private EntityField enrichBeforeCreate(EntityField field) {
        return field.toBuilder()
            .id(field.getId() == null ? UUID.randomUUID() : field.getId())
            .audit(auditHelper.getForCreate())
            .build();
    }

    private EntityField enrichBeforeUpdate(EntityField field) {
        return field.toBuilder()
            .audit(auditHelper.getForUpdate(field.getAudit()))
            .build();
    }
}
