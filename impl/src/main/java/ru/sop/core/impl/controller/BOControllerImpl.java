package ru.sop.core.impl.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RestController;
import ru.sop.core.api.controller.BOController;
import ru.sop.core.api.dto.bo.BOCreateRq;
import ru.sop.core.api.dto.bo.BORs;
import ru.sop.core.api.dto.bo.BOUpdateRq;
import ru.sop.core.api.dto.page.PageRs;
import ru.sop.core.api.dto.page.PageSelectorRq;
import ru.sop.core.impl.mapper.BOMapper;
import ru.sop.core.impl.mapper.PageMapper;
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
    private final MetadataFactory metadataFactory;
    private final PageMapper pageMapper;
    private final BOMapper boMapper;

    @Override
    public PageRs getPage(UUID entityId, PageSelectorRq rq) {
        val result = boSearchService.getPage(metadataFactory.create(), entityId, pageMapper.convert(rq));
        return pageMapper.convert(result);
    }

    @Override
    public BORs getOne(UUID entityId, UUID boId) {
        return boMapper.convert(boSearchService.getOne(boId));
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
        boDeleteService.deleteOne(id);
    }
}
