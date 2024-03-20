package ru.sop.core.impl.service;

import java.util.UUID;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.PageRs;
import ru.sop.core.impl.model.bo.BO;

public interface BOSearchService {

    PageRs getData(UUID entityId, DataRq rq);

    BO getById(UUID id);
}
