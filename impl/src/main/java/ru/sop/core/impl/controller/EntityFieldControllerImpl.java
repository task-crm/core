package ru.sop.core.impl.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RestController;
import ru.sop.core.api.controller.EntityFieldController;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.DataRs;
import ru.sop.core.api.dto.entity.field.EntityFieldCreateRq;
import ru.sop.core.api.dto.entity.field.EntityFieldRs;
import ru.sop.core.api.dto.entity.field.EntityFieldUpdateRq;
import ru.sop.core.impl.mapper.DataMapper;
import ru.sop.core.impl.mapper.EntityFieldMapper;
import ru.sop.core.impl.metadata.MetadataFactory;
import ru.sop.core.impl.service.EntityFieldService;

@RestController
@RequiredArgsConstructor
public class EntityFieldControllerImpl implements EntityFieldController {
    EntityFieldMapper entityFieldMapper;
    DataMapper dataMapper;
    EntityFieldService entityFieldService;
    MetadataFactory metadataFactory;

    @Override
    public EntityFieldRs create(UUID entityId, EntityFieldCreateRq rq) {
        val field = entityFieldService.create(entityFieldMapper.convert(entityId, rq));
        return entityFieldMapper.convert(field);
    }

    @Override
    public EntityFieldRs update(UUID entityId, UUID fieldId, EntityFieldUpdateRq rq) {
        val field = entityFieldService.update(entityFieldMapper.convert(entityId, fieldId, rq));
        return entityFieldMapper.convert(field);
    }

    @Override
    public void deleteById(UUID entityId, UUID fieldId) {
        entityFieldService.deleteOne(entityId, fieldId);
    }

    @Override
    public EntityFieldRs getById(UUID entityId, UUID fieldId) {
        return entityFieldMapper.convert(entityFieldService.getOne(entityId, fieldId));
    }

    @Override
    public DataRs getData(UUID entityId, DataRq rq) {
        val result = entityFieldService.getData(entityFieldMapper.convert(entityId, rq, metadataFactory.create()));
        return dataMapper.convert(result);
    }
}
