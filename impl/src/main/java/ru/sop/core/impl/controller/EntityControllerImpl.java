package ru.sop.core.impl.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RestController;
import ru.sop.core.api.controller.EntityController;
import ru.sop.core.api.dto.entity.EntityCreateRq;
import ru.sop.core.api.dto.entity.EntityRs;
import ru.sop.core.api.dto.entity.EntityUpdateRq;
import ru.sop.core.api.dto.page.PageRs;
import ru.sop.core.api.dto.page.PageSelectorRq;
import ru.sop.core.impl.mapper.EntityMapper;
import ru.sop.core.impl.mapper.PageMapper;
import ru.sop.core.impl.metadata.MetadataFactory;
import ru.sop.core.impl.service.EntityService;

@RestController
@RequiredArgsConstructor
public class EntityControllerImpl implements EntityController {
    private final MetadataFactory metadataFactory;
    private final EntityService entityService;
    private final EntityMapper entityMapper;
    private final PageMapper pageMapper;

    @Override
    public EntityRs getById(UUID id) {
        return entityMapper.convert(entityService.getOne(id));
    }

    @Override
    public PageRs getPage(PageSelectorRq rq) {
        return pageMapper.convert(entityService.getPage(pageMapper.convert(rq)));
    }

    @Override
    public EntityRs create(EntityCreateRq rq) {
        val entity = entityService.create(metadataFactory.create(), entityMapper.convert(rq));
        return entityMapper.convert(entity);
    }

    @Override
    public EntityRs update(UUID entityId, EntityUpdateRq rq) {
        val field = entityService.update(metadataFactory.create(), entityMapper.convert(entityId, rq));
        return entityMapper.convert(field);
    }

    @Override
    public void deleteById(UUID id) {
        entityService.deleteOne(id);
    }
}
