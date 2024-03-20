package ru.sop.core.impl.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RestController;
import ru.sop.core.api.controller.BOController;
import ru.sop.core.api.dto.bo.BOCreateRq;
import ru.sop.core.api.dto.bo.BORs;
import ru.sop.core.api.dto.bo.BOUpdateRq;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.PageRs;
import ru.sop.core.impl.mapper.BOMapper;
import ru.sop.core.impl.metadata.MetadataFactory;
import ru.sop.core.impl.service.BOChangeService;
import ru.sop.core.impl.service.BODeleteService;
import ru.sop.core.impl.service.BOSearchService;

@RestController
@RequiredArgsConstructor
public class BOControllerImpl implements BOController {
    private final BOSearchService boSearchService;
    private final BOChangeService boChangeService;
    private final BODeleteService boDeleteService;
    private final BOMapper boMapper;
    private final MetadataFactory metadataFactory;

    //TODO переписать на команду
    @Override
    public PageRs getData(UUID entityId, DataRq rq) {
        return boSearchService.getData(entityId, rq);
    }

    @Override
    public BORs getById(UUID entityId, UUID boId) {
        return boMapper.convert(boSearchService.getById(boId));
    }

    @Override
    public BORs create(UUID entityId, BOCreateRq rq) {
        val cmd = boMapper.convert(rq, entityId, metadataFactory.create());
        val cmdResult = boChangeService.create(cmd);
        return boMapper.convert(cmdResult);
    }

    @Override
    public BORs patch(UUID entityId, UUID boId, BOUpdateRq rq) {
        val cmd = boMapper.convert(boId, rq, entityId, metadataFactory.create());
        val cmdResult = boChangeService.patch(cmd);
        return boMapper.convert(cmdResult);
    }

    @Override
    public void deleteById(UUID entityId, UUID id) {
        boDeleteService.deleteById(id);
    }
}
