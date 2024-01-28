package ru.sop.core.impl.metadata;

import io.vavr.Lazy;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.sop.core.impl.model.Entity;
import ru.sop.core.impl.model.EntityField;
import ru.sop.core.impl.service.EntityFieldService;
import ru.sop.core.impl.service.EntityService;

@Builder
@RequiredArgsConstructor
public class LazyMetadata implements Metadata {
    private final EntityFieldService entityFieldService;
    private final EntityService entityService;

    private final Lazy<Map<UUID, Entity>> entityById = Lazy.of(ConcurrentHashMap::new);
    private final Lazy<Map<UUID, Map<String, EntityField>>> fieldsByEntityId = Lazy.of(ConcurrentHashMap::new);

    @Override
    public Entity getEntityById(UUID entityId) {
        val entity = entityById.get().get(entityId);
        if (entity != null) {
            return entity;
        }
        return loadEntity(entityId);
    }

    @Override
    public Map<String, EntityField> getFieldByNameByEntityId(UUID entityId) {
        val fields = fieldsByEntityId.get().get(entityId);
        if (fields != null) {
            return fields;
        }
        return loadEntityFields(entityId);
    }

    private Entity loadEntity(UUID entityId) {
        val entity = entityService.getById(entityId);
        entityById.get().put(entityId, entity);
        return entity;
    }

    private Map<String, EntityField> loadEntityFields(UUID entityId) {
        val result = entityFieldService.getFieldsByNameByEntityId(entityId);
        fieldsByEntityId.get().put(entityId, result);
        return result;
    }
}
