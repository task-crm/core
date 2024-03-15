package ru.sop.core.impl.service;

import java.util.UUID;
import ru.sop.core.api.dto.data.DataRq;
import ru.sop.core.api.dto.data.DataRs;
import ru.sop.core.impl.model.bo.BO;

public interface BOSearchService {

    DataRs getData(UUID entityId, DataRq rq);

    BO getById(UUID id);
}
