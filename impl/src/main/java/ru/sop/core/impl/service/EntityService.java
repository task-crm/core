package ru.sop.core.impl.service;

import java.util.UUID;
import ru.sop.core.impl.metadata.Metadata;
import ru.sop.core.impl.model.entity.Entity;
import ru.sop.core.impl.model.page.Page;
import ru.sop.core.impl.model.page.PageSelector;

public interface EntityService {

    Entity getOne(UUID entityId);

    Page<Entity> getPage(PageSelector pageSelector);

    Entity create(Metadata metadata, Entity entity);

    Entity update(Metadata metadata, Entity entity);

    void deleteOne(UUID entityId);
}
