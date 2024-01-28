package ru.sop.core.impl.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RestController;
import ru.sop.core.api.controller.BOController;
import ru.sop.core.api.dto.rq.BOCreateRq;
import ru.sop.core.api.dto.rq.BOUpdateRq;
import ru.sop.core.api.dto.rq.QueryRq;
import ru.sop.core.api.dto.rs.BORs;
import ru.sop.core.api.dto.rs.PageRs;
import ru.sop.core.impl.mapper.BOMapper;
import ru.sop.core.impl.mapper.PageMapper;
import ru.sop.core.impl.mapper.QueryMapper;
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
    private final QueryMapper queryMapper;
    private final PageMapper pageMapper;
    private final MetadataFactory metadataFactory;

    @Override
    public PageRs getPage(QueryRq rq) {
        val query = queryMapper.convert(rq);
        val page = boSearchService.getPage(query);
        return pageMapper.convert(page);
    }

    @Override
    public BORs getById(UUID id) {
        return boMapper.convert(boSearchService.getById(id));
    }

    @Override
    public BORs create(BOCreateRq rq, UUID entityId) {
        val cmd = boMapper.convert(rq, entityId, metadataFactory.create());
        val cmdResult = boChangeService.create(cmd);
        return boMapper.convert(cmdResult);
    }

    @Override
    public BORs patch(UUID id, BOUpdateRq rq, UUID entityId) {
        val cmd = boMapper.convert(id, rq, entityId, metadataFactory.create());
        val cmdResult = boChangeService.patch(cmd);
        return boMapper.convert(cmdResult);
    }

    @Override
    public void deleteById(UUID id) {
        boDeleteService.deleteById(id);
    }
}
