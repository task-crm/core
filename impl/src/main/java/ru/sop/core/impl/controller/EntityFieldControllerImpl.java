package ru.sop.core.impl.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RestController;
import ru.sop.core.api.controller.EntityFieldController;
import ru.sop.core.api.dto.entity.field.EntityFieldCreateRq;
import ru.sop.core.api.dto.entity.field.EntityFieldRs;
import ru.sop.core.api.dto.entity.field.EntityFieldUpdateRq;
import ru.sop.core.api.dto.page.PageRs;
import ru.sop.core.api.dto.page.PageSelectorRq;
import ru.sop.core.impl.mapper.EntityFieldMapper;
import ru.sop.core.impl.mapper.PageMapper;
import ru.sop.core.impl.metadata.MetadataFactory;
import ru.sop.core.impl.service.EntityFieldService;

@RestController
@RequiredArgsConstructor
public class EntityFieldControllerImpl implements EntityFieldController {
    private final EntityFieldService entityFieldService;
    private final EntityFieldMapper entityFieldMapper;
    private final MetadataFactory metadataFactory;
    private final PageMapper pageMapper;

    @Override
    public EntityFieldRs getOne(UUID entityId, UUID fieldId) {
        return entityFieldMapper.convert(entityFieldService.getOne(entityId, fieldId));
    }

    @Override
    public PageRs getPage(UUID entityId, PageSelectorRq rq) {
        val result = entityFieldService.getPage(entityFieldMapper.convert(entityId, rq));
        return pageMapper.convert(result);
    }

    @Override
    public EntityFieldRs create(UUID entityId, EntityFieldCreateRq rq) {
        val field = entityFieldService.create(metadataFactory.create(), entityFieldMapper.convert(entityId, rq));
        return entityFieldMapper.convert(field);
    }

    @Override
    public EntityFieldRs update(UUID entityId, UUID fieldId, EntityFieldUpdateRq rq) {
        val field = entityFieldService.update(metadataFactory.create(), entityFieldMapper.convert(entityId, fieldId, rq));
        return entityFieldMapper.convert(field);
    }

    @Override
    public void deleteById(UUID entityId, UUID fieldId) {
        entityFieldService.deleteOne(entityId, fieldId);
    }
}
