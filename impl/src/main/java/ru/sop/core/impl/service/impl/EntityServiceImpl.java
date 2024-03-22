package ru.sop.core.impl.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.sop.core.impl.helper.AuditHelper;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;
import ru.sop.core.impl.repository.EntityRepository;
import ru.sop.core.impl.service.EntityService;

@Service
@RequiredArgsConstructor
public class EntityServiceImpl implements EntityService {
    private final EntityRepository entityRepository;
    private final AuditHelper auditHelper;

    @Override
    public Entity getOne(UUID entityId) {
        return entityRepository.getOne(entityId);
    }

    @Override
    public Page<Entity> getPage(PageSelector pageSelector) {
        return entityRepository.getPage(pageSelector);
    }

    @Override
    public Entity create(Metadata metadata, Entity entity) {
        val richEntity = enrichBeforeCreate(entity);
        entityRepository.create(richEntity);
        return richEntity;
    }

    @Override
    public Entity update(Metadata metadata, Entity entity) {
        val richEntity = enrichBeforeUpdate(entity);
        entityRepository.update(entity);
        return richEntity;
    }

    @Override
    public void deleteOne(UUID entityId) {
        entityRepository.deleteOne(entityId);
    }

    private Entity enrichBeforeCreate(Entity entity) {
        return entity.toBuilder()
            .id(entity.getId() == null ? UUID.randomUUID() : entity.getId())
            .audit(auditHelper.getForCreate())
            .build();
    }

    private Entity enrichBeforeUpdate(Entity entity) {
        return entity.toBuilder()
            .audit(auditHelper.getForUpdate(entity.getAudit()))
            .build();
    }
}
